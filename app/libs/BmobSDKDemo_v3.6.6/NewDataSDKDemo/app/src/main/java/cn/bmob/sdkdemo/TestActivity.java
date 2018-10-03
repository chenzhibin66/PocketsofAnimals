package cn.bmob.sdkdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FetchUserInfoListener;
import cn.bmob.v3.listener.SaveListener;

public class TestActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser!=null){
            fetchUserInfo();
        }else {
            login();
        }
    }

    /**
     * 更新本地用户信息
     * 注意：需要先登录，否则会报9024错误
     *
     * @see cn.bmob.v3.helper.ErrorCode#E9024S
     */
    private void fetchUserInfo() {
        BmobUser.fetchUserInfo(new FetchUserInfoListener<BmobUser>() {
            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if (e == null) {
                    Log.e("fetch 1",bmobUser.getUsername());
                    Toast.makeText(TestActivity.this, ""+"Newest UserInfo is " + bmobUser.getUsername(), Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("exception 1",e.getMessage());
                    Toast.makeText(TestActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        BmobUser.fetchUserJsonInfo(new FetchUserInfoListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e == null) {
                    Log.e("fetch",s);
                    Toast.makeText(TestActivity.this, ""+"Newest UserInfo is " + s, Toast.LENGTH_SHORT).show();
                } else {
                    Log.e("exception",e.getMessage());
                    Toast.makeText(TestActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });


    }


    private void login(){
        BmobUser bu2 = new BmobUser();
        bu2.setUsername("lucky");
        bu2.setPassword("123456");
        bu2.login(new SaveListener<BmobUser>() {

            @Override
            public void done(BmobUser bmobUser, BmobException e) {
                if(e==null){
                    Toast.makeText(TestActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                    //通过BmobUser user = BmobUser.getCurrentUser()获取登录成功后的本地用户信息
                    //如果是自定义用户对象MyUser，可通过MyUser user = BmobUser.getCurrentUser(MyUser.class)获取自定义用户信息
                }else{
                    Toast.makeText(TestActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
