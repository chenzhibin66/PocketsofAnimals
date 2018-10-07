package example.chaoyueteam.com.pocketsofanimals.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import butterknife.BindView;
import example.chaoyueteam.com.pocketsofanimals.R;
import example.chaoyueteam.com.pocketsofanimals.db.AnimalIntroduction;
import example.chaoyueteam.com.pocketsofanimals.modules.discover.AnimalActivity;

public class AnimalsAdapter extends BaseMultiItemQuickAdapter<AnimalIntroduction, BaseViewHolder> {
    private Intent intent;


    @BindView(R.id.animal_picture)
    ImageView animalPicture;
    @BindView(R.id.animal_name)
    TextView animalName;
    @BindView(R.id.whole_layout)
    LinearLayout wholeLayout;


    public AnimalsAdapter(List<AnimalIntroduction> animals) {
        super(animals);
        addItemType(AnimalIntroduction.TYPE_PICTURE, R.layout.animal_middle);
    }

    @Override
    protected void convert(BaseViewHolder helper, final AnimalIntroduction animalIntroduction) {
        switch (helper.getItemViewType()){
               case AnimalIntroduction.TYPE_PICTURE:
                   helper.setText(R.id.animal_name,animalIntroduction.getAnimalname());
                   ImageView imageView = helper.getView(R.id.animal_picture);
                   Glide.with(mContext).load(animalIntroduction.getAnimalPhoto().getUrl()).into(imageView);
                   break;
        }
        View view=helper.getView(R.id.whole_layout);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent=new Intent(mContext,AnimalActivity.class);
                intent.putExtra(AnimalActivity.ANIMAL_NAME,animalIntroduction.getAnimalname());
                intent.putExtra(AnimalActivity.ANIMAL_IMAGE_ID,animalIntroduction.getAnimalPhoto().getUrl());
                intent.putExtra(AnimalActivity.ANIMAL_INTRODUCE,animalIntroduction.getIntroduce());
                Log.d(TAG, "onClick: "+animalIntroduction.getIntroduce());
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public void loadMoreComplete() {
        super.loadMoreComplete();
    }

    @Override
    public void loadMoreEnd() {
        super.loadMoreEnd();
    }

    @Override
    public void loadMoreFail() {
        super.loadMoreFail();
    }
}
