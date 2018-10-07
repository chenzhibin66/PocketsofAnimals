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

public class NickActtivity extends AppCompatActivity {

    @BindView(R.id.im_back)
    ImageView imBack;
    @BindView(R.id.save_nick)
    TextView saveNick;
    @BindView(R.id.alter_nick)
    EditText alterNick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_nick);
        ButterKnife.bind(this);
        MyUser myUser = BmobUser.getCurrentUser(MyUser.class);
        alterNick.setText(myUser.getNick());
    }

    @OnClick({R.id.im_back, R.id.save_nick, R.id.alter_nick})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.im_back:
                Intent intent = new Intent(NickActtivity.this, MainActivity.class);
                intent.putExtra("code", 1);
                startActivity(intent);
                finish();
                break;
            case R.id.save_nick:
                String nick = alterNick.getText().toString();
                MyUser myUser =  BmobUser.getCurrentUser(MyUser.class);
                myUser.setNick(nick);
                myUser.update(new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Toast.makeText(NickActtivity.this, "更新成功！", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(NickActtivity.this, MainActivity.class);
                            intent.putExtra("code", 1);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(NickActtivity.this, "更新失败！" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                break;
        }
    }
}
