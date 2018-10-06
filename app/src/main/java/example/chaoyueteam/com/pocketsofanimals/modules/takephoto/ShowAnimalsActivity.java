package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.graphics.Bitmap;
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
import android.widget.Toast;
import java.io.File;
import java.io.IOException;
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


    public static final String PATH = Environment.getExternalStorageDirectory().toString() + "/AndroidMedia/New_animal_picture/";
    Album album;
    private MediaPlayer mediaPlayer = new MediaPlayer();
    Animal animal;
    AlbumUtil albumUtil;
    Bitmap bitmap1;

    String mp3_path;

    String path;
    Bitmap bitmap;

    String path_imag;
    String path_mp3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animals);
        final FloatingActionButton floatingActionButton = findViewById(R.id.fb);
        final ZoomImageView zoomImageView = findViewById(R.id.show_animals);
        Glide.with(getApplicationContext()).load(R.drawable.loading5).into(zoomImageView);

        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        Glide.with(getApplicationContext()).load(R.drawable.loading).into(zoomImageView);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                Intent intent = getIntent();

                path_imag= intent.getStringExtra("path");

                String access_token = "24.69fa1f6175364ed5b13c0752a1b18b7a.2592000.1540636647.282335-14301873";
                try {

                    animal = getAnimalBean(path_imag,access_token);
                    // 图像名称

                    File file1 = new File(path_imag);

                    //File file = new File(path);
                    Text2Audio text2Audio = new Text2Audio();
                    String text = animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍");
                    mp3_path = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));

                    long dateTaken = System.currentTimeMillis();

                    String filename = DateFormat.format("yyyy-MM-dd kk.mm.ss", dateTaken).toString() + ".jpg";
                    String path_new = PATH+filename;
                    Uri uri = Uri.fromFile(file1);
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                    BitmapUtil bitmapUtil = new BitmapUtil();
                    bitmap1 = bitmapUtil.translate(bitmap,text);
                    bitmapUtil.saveBitmapFile(bitmap1,path_new);

                    Log.d("onCreate","成功");
                    path_mp3 = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));

                    Log.d("onCreate","path:"+path_imag);
                    Log.d("onCreate","path_mp3:"+path_mp3);
                    Log.d("onCreate","path_new:"+path_new);
                    Log.d("onCreate","name:"+animal.getResult().get(0).getName());
                    Log.d("onCreate","介绍:"+animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));
                    AlbumUtil albumUtil = new AlbumUtil();
                    albumUtil.setAlbuma(path_imag,path_mp3,path_new,
                            animal.getResult().get(0).getName(),
                            animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));
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
            Toast.makeText(getApplicationContext(),"文件不存在:"+file,Toast.LENGTH_SHORT).show();
        }
    }


}
