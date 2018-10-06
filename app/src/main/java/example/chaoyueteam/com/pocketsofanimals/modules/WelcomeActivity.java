package example.chaoyueteam.com.pocketsofanimals.modules;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobUser;
import example.chaoyueteam.com.pocketsofanimals.R;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Bmob.initialize(this, "e3f7e3dcd335515e9aa1040d7067bace");


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                finish();
            }
        },3000);

        BmobUser bmobUser = BmobUser.getCurrentUser();
        if (bmobUser!=null){
            Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
        }else {
            Intent intent=new Intent(WelcomeActivity.this,LoginActivity.class);
            startActivity(intent);
        }
    }






}

