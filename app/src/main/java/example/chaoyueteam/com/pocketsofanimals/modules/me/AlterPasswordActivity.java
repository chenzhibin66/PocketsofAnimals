package example.chaoyueteam.com.pocketsofanimals.modules.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.MyUser;
import example.chaoyueteam.com.pocketsofanimals.modules.MainActivity;

public class AlterPasswordActivity extends AppCompatActivity {
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.save_pw)
    TextView savePw;
    @BindView(R.id.old_pw)
    TextView oldPw;
    @BindView(R.id.new_pw)
    TextView newPw;
    private String TAG = "AlterPasswordActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alter_password);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.im_back, R.id.save_pw, R.id.old_pw, R.id.new_pw})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                Intent intent = new Intent(AlterPasswordActivity.this, MainActivity.class);
                intent.putExtra("code", 3);
                startActivity(intent);
                finish();
                break;
            case R.id.save_pw:
                String oldpw = oldPw.getText().toString();
                String newpw = newPw.getText().toString();
                Log.d(TAG, "onViewClicked: " + oldpw + "\n" + newpw);
                BmobUser.updateCurrentUserPassword(oldpw, newpw, new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(AlterPasswordActivity.this, "修改密码成功啦，可以用新密码登录啦！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(AlterPasswordActivity.this, MainActivity.class);
                            intent.putExtra("code", 3);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(AlterPasswordActivity.this, "修改密码失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
