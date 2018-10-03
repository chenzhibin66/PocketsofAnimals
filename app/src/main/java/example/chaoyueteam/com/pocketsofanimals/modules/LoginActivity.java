package example.chaoyueteam.com.pocketsofanimals.modules;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.chaoyueteam.com.pocketsofanimals.R;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.et_usertel)
    EditText etUsertel;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_password)
    TextView tvPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_sign)
    TextView tvSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login, R.id.tv_sign})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login:

                break;
            case R.id.tv_sign:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}
