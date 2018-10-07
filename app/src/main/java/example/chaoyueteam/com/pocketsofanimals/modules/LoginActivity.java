package example.chaoyueteam.com.pocketsofanimals.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.MyUser;

public class LoginActivity extends AppCompatActivity {
    FileOutputStream mFileOutputStream = null;
    File file = null;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.username)
    EditText username;
    @BindView(R.id.password)
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
        //默认初始化
        Bmob.initialize(this, "e3f7e3dcd335515e9aa1040d7067bace");
        tvSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logIn(username.getText().toString(), password.getText().toString());
            }
        });
    }

    public void logIn(String username, String password) {
        BmobUser.loginByAccount(username, password, new LogInListener<MyUser>() {
            @Override
            public void done(MyUser user, BmobException e) {
                if (e == null) {
                    user = BmobUser.getCurrentUser(MyUser.class);
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "账号或密码输入错误，请重新输入！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
