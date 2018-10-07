package example.chaoyueteam.com.pocketsofanimals.modules.me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
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

public class SignatureActivity extends AppCompatActivity {
    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.save_signature)
    TextView saveSignature;
    @BindView(R.id.alter_signature)
    EditText alterSignature;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_signature);
        ButterKnife.bind(this);
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        alterSignature.setText(myUser.getSignature());
    }

    @OnClick({R.id.im_back, R.id.save_signature})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                Intent intent = new Intent(SignatureActivity.this, MainActivity.class);
                intent.putExtra("code", 2);
                startActivity(intent);
                finish();
                break;
            case R.id.save_signature:
                String signature = alterSignature.getText().toString();
                MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
                myUser.setSignature(signature);
                myUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(SignatureActivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignatureActivity.this, MainActivity.class);
                            intent.putExtra("code", 2);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(SignatureActivity.this, "更新失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
