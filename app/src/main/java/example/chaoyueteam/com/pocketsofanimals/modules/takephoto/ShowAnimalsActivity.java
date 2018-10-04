package example.chaoyueteam.com.pocketsofanimals.modules.takephoto;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.jpardogo.android.flabbylistview.lib.FlabbyListView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.modules.MainActivity;

public class ShowAnimalsActivity extends AppCompatActivity {

    @BindView(R.id.back_takephoto)
    ImageView backTakephoto;
    @BindView(R.id.delete_all_pic)
    ImageView deleteAllPic;
    @BindView(R.id.list)
    FlabbyListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animals);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.back_takephoto, R.id.delete_all_pic})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back_takephoto:
                Intent intent=new Intent(ShowAnimalsActivity.this,MainActivity.class);
                startActivity(intent);
                break;
            case R.id.delete_all_pic:
                break;
        }
    }
}
