package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;


import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;

import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.Album;
import example.chaoyueteam.com.pocketsofanimals.image.Animal;
import example.chaoyueteam.com.pocketsofanimals.speech.RandomStringGenerator;
import example.chaoyueteam.com.pocketsofanimals.speech.Text2Audio;
import example.chaoyueteam.com.pocketsofanimals.util.AlbumUtil;
import example.chaoyueteam.com.pocketsofanimals.util.BitmapUtil;

import static example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo.getAnimalBean;

public class ShowAnimalsActivity extends AppCompatActivity {

    public static final String PATH = Environment.getExternalStorageDirectory().toString() + "/AndroidMedia/new_picture/";
    Album album;
    Animal animal;
    AlbumUtil albumUtil;
    Bitmap bitmap1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animals);
        new Thread(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                String path_imag = intent.getStringExtra("path");
                String access_token = "24.69fa1f6175364ed5b13c0752a1b18b7a.2592000.1540636647.282335-14301873";
                try {

                    animal = getAnimalBean(path_imag,access_token);
                    //String path_new = "C:\\Users\\MSI-PC\\Desktop\\界面图片\\huge.jpg";
                    // 图像名称
                    String text = animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍");
                    long dateTaken = System.currentTimeMillis();

                    String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken).toString() + ".jpg";
                    String path_new = PATH+filename;
                    File file1 = new File(path_imag);
                    Uri uri = Uri.fromFile(file1);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    BitmapUtil bitmapUtil = new BitmapUtil();
                    bitmap1 = bitmapUtil.translate(bitmap,text);
                    bitmapUtil.saveBitmapFile(bitmap1,path_new);

                    Log.d("onCreate","成功");
                    animal = getAnimalBean(path_imag,access_token);
                    Text2Audio text2Audio = new Text2Audio();

                    String path_mp3 = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));
                    AlbumUtil albumUtil = new AlbumUtil();
                    album = albumUtil.setAlbuma(path_imag,path_mp3,
                            animal.getResult().get(0).getName(),
                            animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));

                    Log.d("onCreate","path:"+path_imag);
                    Log.d("onCreate","name:"+animal.getResult().get(0).getName());
                    Log.d("onCreate","介绍:"+animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
