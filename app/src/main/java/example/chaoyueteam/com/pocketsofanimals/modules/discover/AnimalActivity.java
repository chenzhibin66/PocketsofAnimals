package example.chaoyueteam.com.pocketsofanimals.modules.discover;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import example.chaoyueteam.com.pocketsofanimals.R;

public class AnimalActivity extends AppCompatActivity {
    private String TAG = "AnimalActivity";
    public static final String ANIMAL_NAME = "animal_name";
    public static final String ANIMAL_IMAGE_ID = "animal_image_id";
    public static final String ANIMAL_INTRODUCE = "animal_introduce";
    @BindView(R.id.appBar)
    AppBarLayout appBar;
    @BindView(R.id.animal_content_text)
    TextView animalContentText;
    @BindView(R.id.animal_image_view)
    ImageView animalImageView;
    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        String animalName = intent.getStringExtra(ANIMAL_NAME);
        String animalImageId = intent.getStringExtra(ANIMAL_IMAGE_ID);
        String animalIntroduce =intent.getStringExtra(ANIMAL_INTRODUCE);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        collapsingToolbar.setTitle(animalName);
        Glide.with(this).load(animalImageId).into(animalImageView);
        animalContentText.setText(animalIntroduce);
        Log.d(TAG, "shenmedongxi: " + animalIntroduce);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
