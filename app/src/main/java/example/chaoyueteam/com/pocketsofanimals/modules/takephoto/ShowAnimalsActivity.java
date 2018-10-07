package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Message;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import android.widget.Toast;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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


import static android.provider.MediaStore.Images.Media.insertImage;
import static example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo.getAnimalBean;
import static example.chaoyueteam.com.pocketsofanimals.modules.takephoto.TakePhotoActivity.IMAGE_URI;

public class ShowAnimalsActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ShowAnimalsActivity";
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
//        final FloatingActionButton floatingActionButton = findViewById(R.id.fb);
        final ZoomImageView zoomImageView = findViewById(R.id.show_animals);
        final ImageView imageView = findViewById(R.id.show_animals);
        Glide.with(getApplicationContext()).load(R.drawable.loading).into(zoomImageView);
        android.support.v7.widget.Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(ShowAnimalsActivity.this,TakePhotoActivity.class);
                startActivity(intent1);
                finish();
            }
        });

        //Glide.with(getApplicationContext()).load(R.drawable.loading5).into(zoomImageView);
        final ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                final Intent intent = getIntent();

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
                    Uri uri1 = insertImage(getContentResolver(), filename, dateTaken, PATH, filename, bitmap1, null);
                    bitmapUtil.saveBitmapFile(bitmap1,path_new);

                    Log.d("onCreate","成功");
                    path_mp3 = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));
                    Log.d("onCreate","path:"+path_imag);
                    Log.d("onCreate","path_mp3:"+path_mp3);
                    Log.d("onCreate","path_new:"+path_new);
                    Log.d("onCreate","name:"+animal.getResult().get(0).getName());
                    Log.d("onCreate","介绍:"+animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));
//                    texView.post(new Runnable() {
//                        @Override
//                        public void run() {
//                            }
//                    });
//                    AlbumUtil albumUtil = new AlbumUtil();
//                    albumUtil.setAlbuma(path_imag,path_mp3,path_new,
//                            animal.getResult().get(0).getName(),
//                            animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));
                } catch (Exception e) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent1 = new Intent(ShowAnimalsActivity.this,NullAnimal.class);
                            startActivity(intent1);
                            finish();
                        }
                    });
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        zoomImageView.setImageBitmap(bitmap1);
                        initMediaPlayer(mp3_path);
//
//                        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View v) {
//                                if(!mediaPlayer.isPlaying()){
//                                    mediaPlayer.start();
//                                }else {
//                                    mediaPlayer.pause();
//                                }
//                            }
//                        });

                    }
                });
            }
        });
        executorService.shutdown();
    }

    private Uri insertImage(ContentResolver cr, String name, long dateTaken,
                            String directory, String filename, Bitmap source, byte[] jpegData) {
        OutputStream outputStream = null;
        String filePath = directory + filename;
        try {
            File dir = new File(directory);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(directory, filename);
            if (file.createNewFile()) {
                outputStream = new FileOutputStream(file);
                if (source != null) {
                    source.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                } else {
                    outputStream.write(jpegData);
                }
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } catch (IOException e) {
            Log.e(TAG, e.getMessage());
            return null;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable t) {
                }
            }
        }
        ContentValues values = new ContentValues(7);
        values.put(MediaStore.Images.Media.TITLE, name);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, filename);
        values.put(MediaStore.Images.Media.DATE_TAKEN, dateTaken);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATA, filePath);
        return cr.insert(IMAGE_URI, values);
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


    @Override
    public void onClick(View v) {
                Intent intent2 = new Intent(ShowAnimalsActivity.this,TakePhotoActivity.class);
                startActivity(intent2);
                finish();
        };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.start_do:{
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();
                }else {
                    mediaPlayer.pause();
                }
            }
        }
        return true;
    }
}
