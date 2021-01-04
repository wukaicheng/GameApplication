package cn.kaicity.app.gameapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.kaicity.app.gameapplication.R;
import cn.kaicity.app.gameapplication.http.RetrofitService;
import cn.kaicity.app.gameapplication.http.api.IRegister;
import cn.kaicity.app.gameapplication.http.bean.Result;
import cn.kaicity.app.gameapplication.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText usernameEdit;

    @BindView(R.id.password)
    EditText passwordEdit;

    @BindView(R.id.confirm_password)
    EditText confirmPasswordEdit;

    @BindView(R.id.phone)
    EditText phoneEdit;

    @BindView(R.id.register)
    Button registerButton;

    private final IRegister registerApi = RetrofitService.getInstance().create(IRegister.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        initToolbar("注册账号", v -> finish());
        initButton();
    }

    private void initButton() {

        registerButton.setOnClickListener(v -> {

            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            String confirmPassword = confirmPasswordEdit.getText().toString();
            String phone = phoneEdit.getText().toString();

            if (TextUtils.isEmpty(username)) {
                ToastUtil.showToast("请输入用户名");
                usernameEdit.setError("用户账号不能为空！");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                ToastUtil.showToast("请输入密码");
                passwordEdit.setError("密码不能为空！");
                return;
            }

            if (!password.equals(confirmPassword)) {
                ToastUtil.showToast("输入两次的密码不一样");
                confirmPasswordEdit.setError("输入两次的密码不一样！");
                return;
            }

            if (TextUtils.isEmpty(phone)) {
                ToastUtil.showToast("请输入手机号");
                phoneEdit.setError("手机号不能为空！");
                return;
            }

            doRegister(username, password, phone);
        });

    }

    private void doRegister(String username, String password, String phone) {

        Call<Result<String>> result = registerApi.register(username, password, phone);
        result.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if (response.body() == null) {
                    ToastUtil.showToast("注册失败");
                } else if (response.body().getStatus() != 200) {
                    ToastUtil.showToast("注册失败：" + response.body().getMsg());
                } else {
                    ToastUtil.showToast("注册成功");
                    Intent in = getIntent();
                    in.putExtra("username", username);
                    in.putExtra("password", password);
                    setResult(110, in);
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