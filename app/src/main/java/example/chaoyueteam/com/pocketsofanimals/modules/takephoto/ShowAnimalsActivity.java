package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
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

import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.Album;
import example.chaoyueteam.com.pocketsofanimals.image.Animal;
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
                String path = intent.getStringExtra("path");
                String access_token = "24.69fa1f6175364ed5b13c0752a1b18b7a.2592000.1540636647.282335-14301873";
                try {
                    animal = getAnimalBean(path,access_token);
//                    //File file = new File(path);
//                    Text2Audio text2Audio = new Text2Audio();
                    String text = animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍");
//                    String audio_path = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));
//                    //File file1 = new File(audio_path);
//                    File file = new File(audio_path);
//                    final BmobFile bmobFile = new BmobFile(file);
//                    //final BmobFile bmobFile1 = new BmobFile(file1);
//                    bmobFile.uploadblock(new UploadFileListener() {
//                        @Override
//                        public void done(BmobException e) {
//                            if (e == null) {
//                                Album album = new Album();
//                                album.setMp3File(bmobFile);
////                                album.setAnimalImage(bmobFile);
////                                album.setAnimalName(animal.getResult().get(0).getName());
////                                album.setAnimalInformation(animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));
//                                album.save(new SaveListener<String>() {
//                                    @Override
//                                    public void done(String s, BmobException e) {
//                                        if(e==null){
//                                            Log.d("bmob", "成功");
//                                        }else{
//                                            Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
//                                        }
//                                    }
//                                });
//
//                            } else {
//                            }
//                        }
//                    });
                    //String path_new = "C:\\Users\\MSI-PC\\Desktop\\界面图片\\huge.jpg";
                    File file1 = new File(path);
                    Uri uri = Uri.fromFile(file1);
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    BitmapUtil bitmapUtil = new BitmapUtil();
                    bitmap1 = bitmapUtil.translate(bitmap,text);
                    long dateTaken = System.currentTimeMillis();
                    // 图像名称
                    String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken).toString() + ".jpg";
                    String path_new = PATH+filename;
                    bitmapUtil.saveBitmapFile(bitmap1,path_new);

                    Log.d("onCreate","成功");
                    //bitmapUtil.translate();

                    //albumUtil.setAlbum(animal.getResult().get(0).getName(),path_new,null,animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));

                    Log.d("onCreate","path:"+path);
                    Log.d("onCreate","name:"+animal.getResult().get(0).getName());
                    Log.d("onCreate","介绍:"+animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

}
