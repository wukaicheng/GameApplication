package cn.kaicity.app.gameapplication.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.kaicity.app.gameapplication.R;
import cn.kaicity.app.gameapplication.http.RetrofitService;
import cn.kaicity.app.gameapplication.http.api.IRetrieve;
import cn.kaicity.app.gameapplication.http.bean.Result;
import cn.kaicity.app.gameapplication.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPassActivity extends BaseActivity {

    @BindView(R.id.phone)
    EditText phoneEdit;

    @BindView(R.id.password)
    EditText passwordEdit;

    @BindView(R.id.findPass)
    Button findPassButton;

    @BindView(R.id.login)
    TextView loginTextView;

    private final IRetrieve retrieveApi = RetrofitService.getInstance().create(IRetrieve.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_pass);
        ButterKnife.bind(this);
        initToolbar("找回密码", v -> {
            finish();
        });

        initButton();
    }

    private void initButton() {

        loginTextView.setOnClickListener(v->finish());

        findPassButton.setOnClickListener(v -> {
            String phone = phoneEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            if (TextUtils.isEmpty(password)) {
                ToastUtil.showToast("请输入密码");
                passwordEdit.setError("密码不能为空！");
                return;
            }


            if (TextUtils.isEmpty(phone)) {
                ToastUtil.showToast("请输入手机号");
                phoneEdit.setError("手机号不能为空！");
                return;
            }

            doFindPass(phone,password);
        });
    }

    private void doFindPass(String phone, String password) {
        Call<Result<String>> result = retrieveApi.retrievePassword(phone, password);
        result.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if (response.body() == null) {
                    ToastUtil.showToast("注册失败");
                } else if (response.body().getStatus() != 200) {
                    ToastUtil.showToast("注册失败：" + response.body().getMsg());
                } else {
                    ToastUtil.showToast("密码修改成功，快去登录吧");
                    finish();
                }
            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                ToastUtil.showToast("网络访问失败" + t);
            }
        });

    }
}