package example.chaoyueteam.com.pocketsofanimals.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.MyUser;

public class RegisterActivity extends AppCompatActivity {
    @BindView(R.id.return_login)
    ImageView returnLogin;
    private Button btnRegister;
    private TextView etUsertel, etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_register);
        ButterKnife.bind(this);
        btnRegister = (Button) findViewById(R.id.btn_register);
        etUsertel = (TextView) findViewById(R.id.et_usertel);
        etCode = (TextView) findViewById(R.id.et_code);
        //默认初始化
        Bmob.initialize(this, "e3f7e3dcd335515e9aa1040d7067bace");
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registered();
            }
        });

    }

    public void registered() {
        MyUser bu = new MyUser();
        bu.setUsername(etUsertel.getText().toString());
        bu.setPassword(etCode.getText().toString());
        bu.setNick(null);
        bu.setPicture(null);
        bu.setSignature(null);
        bu.setSex(null);
        // 非空验证
        if (TextUtils.isEmpty(etUsertel.getText().toString()) || TextUtils.isEmpty(etCode.getText().toString())) {
            Toast.makeText(RegisterActivity.this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
                if (e == null) {
                    Toast.makeText(RegisterActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Log.e("", e.toString());
                }
            }
        });
    }


    @OnClick(R.id.return_login)
    public void onViewClicked() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
