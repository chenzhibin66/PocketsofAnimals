package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;
import cn.bmob.v3.listener.UploadFileListener;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.Album;
import example.chaoyueteam.com.pocketsofanimals.image.Animal;
import example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo;
import example.chaoyueteam.com.pocketsofanimals.image.plant;
import example.chaoyueteam.com.pocketsofanimals.speech.RandomStringGenerator;
import example.chaoyueteam.com.pocketsofanimals.speech.Text2Audio;
import example.chaoyueteam.com.pocketsofanimals.util.AlbumUtil;

import static example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo.getAnimalBean;

public class ShowAnimalsActivity extends AppCompatActivity {

    Animal animal;
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
                    Text2Audio text2Audio = new Text2Audio();
                    String text = animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍");
                    String path_mp3 = text2Audio.text2Audio(text,access_token,"1", RandomStringGenerator.getRandomStringByLength(60));
                    AlbumUtil albumUtil = new AlbumUtil();
                    Album album = albumUtil.setAlbuma(path_imag,path_mp3,
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
