package example.chaoyueteam.com.pocketsofanimals.base;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.io.FileInputStream;

import cn.bmob.v3.Bmob;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.util.MyTestUtil;
import example.chaoyueteam.com.pocketsofanimals.util.UserUtil;

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
