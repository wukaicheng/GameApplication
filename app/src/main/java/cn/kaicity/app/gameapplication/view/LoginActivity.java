package cn.kaicity.app.gameapplication.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.kaicity.app.gameapplication.R;
import cn.kaicity.app.gameapplication.http.api.ILogin;
import cn.kaicity.app.gameapplication.http.bean.Result;
import cn.kaicity.app.gameapplication.http.RetrofitService;
import cn.kaicity.app.gameapplication.http.bean.User;
import cn.kaicity.app.gameapplication.util.ToastUtil;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @ProjectName: GameApplication
 * @Package: cn.kaicity.app.gameapplication
 * @ClassName: MainActivity
 * @Description: 登录界面
 * @Author: wukaicheng
 * @CreateDate: 2021/1/3 19:43
 * @UpdateUser: wukaicheng
 * @UpdateDate: 2021/1/3 19:43
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.username)
    EditText usernameEdit;

    @BindView(R.id.password)
    EditText passwordEdit;

    @BindView(R.id.login)
    Button loginButton;

    @BindView(R.id.register)
    TextView registerButton;

    @BindView(R.id.findPass)
    TextView findPassButton;

    private final ILogin loginApi = RetrofitService.getInstance().create(ILogin.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initToolbar("登录账号", v -> finish());

        initLoginButton();
    }

    private void initLoginButton() {
        registerButton.setOnClickListener(v -> {
            Intent in = new Intent(this, RegisterActivity.class);
            startActivityForResult(in, 108);
        });

        findPassButton.setOnClickListener(v -> {
            Intent in = new Intent(this, FindPassActivity.class);
            startActivityForResult(in, 108);
        });

        loginButton.setOnClickListener(v -> {
            String username = usernameEdit.getText().toString();
            String password = passwordEdit.getText().toString();

            if (TextUtils.isEmpty(username)) {
                ToastUtil.showToast("用户名不能为空");
                usernameEdit.setError("用户名不能为空");
            }

            if (TextUtils.isEmpty(password)) {
                ToastUtil.showToast("密码不能为空");
                passwordEdit.setError("密码不能为空");
            }

            doLogin(username, password);

        });

    }

    private void doLogin(String username, String password) {
        Call<Result<String>> result = loginApi.login(username, password);

        result.enqueue(new Callback<Result<String>>() {
            @Override
            public void onResponse(Call<Result<String>> call, Response<Result<String>> response) {
                if (response.body() == null) {
                    ToastUtil.showToast("登录失败");
                } else if (response.body().getStatus() != 200) {
                    ToastUtil.showToast("登录失败：" + response.body().getMsg());
                } else {
                    ToastUtil.showToast("登录成功");
                    Intent in = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(in);
                    finish();
                }


            }

            @Override
            public void onFailure(Call<Result<String>> call, Throwable t) {
                ToastUtil.showToast("网络访问失败" + t);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 108 && resultCode == 110 && data != null) {
            String username = data.getStringExtra("username");
            String password = data.getStringExtra("password");
            if (!TextUtils.isEmpty(username)) {
                usernameEdit.setText(username);
            }

            if (!TextUtils.isEmpty(password)) {
                passwordEdit.setText(password);
            }
        }
    }
}