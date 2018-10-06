package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.Album;
import example.chaoyueteam.com.pocketsofanimals.image.Animal;
import example.chaoyueteam.com.pocketsofanimals.speech.RandomStringGenerator;
import example.chaoyueteam.com.pocketsofanimals.speech.Text2Audio;
import example.chaoyueteam.com.pocketsofanimals.util.AlbumUtil;
import example.chaoyueteam.com.pocketsofanimals.util.BitmapUtil;
import example.chaoyueteam.com.pocketsofanimals.util.ZoomImageView;

import static example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo.getAnimalBean;

public class ShowAnimalsActivity extends AppCompatActivity {
    public static final String PATH = Environment.getExternalStorageDirectory().toString() + "/New_animal_picture/";
    Album album;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    Animal animal;
    AlbumUtil albumUtil;
    Bitmap bitmap1;
    String mp3_path;
    String path;
    Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animals);
        final FloatingActionButton floatingActionButton = findViewById(R.id.fb);
        final ZoomImageView zoomImageView = findViewById(R.id.show_animals);
        Glide.with(getApplicationContext()).load(R.drawable.loading5).into(zoomImageView);
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();
                path = intent.getStringExtra("path");
                String access_token = "24.69fa1f6175364ed5b13c0752a1b18b7a.2592000.1540636647.282335-14301873";
                try {
                    animal = getAnimalBean(path,access_token);
                    //File file = new File(path);
                    Text2Audio text2Audio = new Text2Audio();
                    String text = animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍");
                    mp3_path = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));

                    File file1 = new File(path);
                    Uri uri = Uri.fromFile(file1);
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
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
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent1 = new Intent(ShowAnimalsActivity.this,NullAnimal.class);
                            intent1.putExtra("paths",path);
                            startActivity(intent1);
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        zoomImageView.setImageBitmap(bitmap1);
                        initMediaPlayer(mp3_path);
                        floatingActionButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                if(!mediaPlayer.isPlaying()){
                                    mediaPlayer.start();
                                }else {
                                    mediaPlayer.pause();
                                }
                            }
                        });


                    }
                });
            }
        });
        executorService.shutdown();

    }
    private void initMediaPlayer(String paths){
        File file =new File(paths);
        try {
            mediaPlayer.setDataSource(paths);
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
