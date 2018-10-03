package cn.bmob.sdkdemo.autoupdate;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

import cn.bmob.sdkdemo.BaseActivity;
import cn.bmob.sdkdemo.R;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.BmobDialogButtonListener;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

public class ActAutoUpdate extends BaseActivity {
    private static final int REQUEST_READ_READ_EXTERNAL = 102;

    String[] arr = {"自动更新", "手动更新", "静默下载更新", "删除文件"};
    UpdateResponse ur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mListview = (ListView) findViewById(R.id.listview);
        mAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.tv_item, arr);
        mListview.setAdapter(mAdapter);

        mListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                update(position);
            }
        });

//		在你需要调用自动更新功能之前先进行初始化建表操作
//		此方法适合开发者调试自动更新功能时使用，一旦AppVersion表在后台创建成功，建议屏蔽或删除此方法，否则会造成生成多行记录。
        BmobUpdateAgent.initAppVersion();

        //利用如下方式计算apk的target_size大小：
        log("应用的target_size的大小 = " + new File("sdcard/BmobExample.apk").length());
        //允许在非wifi环境下检测应用更新
        BmobUpdateAgent.setUpdateOnlyWifi(false);
        //更新监听器
        BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                //V3.4.4版本开始，增加版本更新错误提示，可通过此方法获取到错误信息
                BmobException e = updateInfo.getException();
                if (e != null) {
                    Toast.makeText(ActAutoUpdate.this, "检测更新返回：" + e.getMessage() + "(" + e.getErrorCode() + ")", Toast.LENGTH_SHORT).show();
                } else {
                    ur = updateInfo;
                }
            }
        });
        BmobUpdateAgent.setDialogListener(new BmobDialogButtonListener() {

            @Override
            public void onClick(int status) {
                switch (status) {
                    case UpdateStatus.Update:
                        Toast.makeText(ActAutoUpdate.this, "点击了立即更新按钮", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.NotNow:
                        Toast.makeText(ActAutoUpdate.this, "点击了以后再说按钮", Toast.LENGTH_SHORT).show();
                        break;
                    case UpdateStatus.Close:
                        //只有在强制更新状态下才会在更新对话框的右上方出现close按钮,如果用户不点击”立即更新“按钮，这时候开发者可做些操作，比如直接退出应用等
                        Toast.makeText(ActAutoUpdate.this, "点击了对话框关闭按钮", Toast.LENGTH_SHORT).show();
                        break;

                    default:
                        break;
                }
            }
        });

    }

    private void testAutoUpdate(int pos) {
        switch (pos) {
            case 1:
                //自动更新
                BmobUpdateAgent.update(this);
                break;
            case 2:
                //手动更新
                BmobUpdateAgent.forceUpdate(this);
                break;
            case 3:
                //静默下载
                BmobUpdateAgent.silentUpdate(this);
                break;
            case 4:

                BmobUpdateAgent.deleteResponse(ur);
                break;
            default:
                break;
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_READ_READ_EXTERNAL:
                if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    //TODO
                    testAutoUpdate(mPosition + 1);
                }
                break;

            default:
                break;
        }
    }


    private int mPosition;
    public void update(int position) {

        mPosition =position;
        int permissionCheckR = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        int permissionCheckW = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permissionCheckR != PackageManager.PERMISSION_GRANTED||permissionCheckW != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_READ_READ_EXTERNAL);
        } else {
            //TODO
            testAutoUpdate(position + 1);
        }

    }

}
