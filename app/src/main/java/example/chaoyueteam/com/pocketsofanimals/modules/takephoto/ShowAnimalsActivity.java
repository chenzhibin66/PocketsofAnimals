package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.style.ImageSpan;
import android.util.Log;
import android.widget.Toast;

import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.Album;
import example.chaoyueteam.com.pocketsofanimals.image.Animal;
import example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo;
import example.chaoyueteam.com.pocketsofanimals.image.plant;

import static example.chaoyueteam.com.pocketsofanimals.image.AnimalDemo.getAnimalBean;

public class ShowAnimalsActivity extends AppCompatActivity {

    Album album;
    Animal animal;
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
                    animal.setNames(animal.getResult().get(0).getName());
                    animal.setDescription(animal.getResult().get(0).getBaike_info().substring(animal.getResult().get(0).getBaike_info().indexOf("description")).replace("description\"","介绍"));
                    Log.d("ShowAnimalActivity","animal: "+animal.getNames());
                    Log.d("ShowAnimalActivity",""+animal.getDescription());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
