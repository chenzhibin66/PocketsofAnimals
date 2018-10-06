package example.chaoyueteam.com.pocketsofanimals.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import cn.bmob.v3.Bmob;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /* requestWindowFeature(Window.FEATURE_NO_TITLE);*/
        setContentView(getLayoutId());
        initView(savedInstanceState);
        //默认初始化
        Bmob.initialize(this, "e3f7e3dcd335515e9aa1040d7067bace");


    }

    protected abstract void initView(Bundle savedInstanceState);
    protected abstract int getLayoutId();

}
