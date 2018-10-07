package example.chaoyueteam.com.pocketsofanimals.db;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class AnimalIntroduction extends BmobObject implements MultiItemEntity, Serializable {

    public static final int TYPE_PICTURE=1;
    private BmobFile animalPhoto;
    private String AnimalName;
    private String introduce;

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    private int type;


    public AnimalIntroduction(BmobFile animalPhoto,String AnimalName,String introduce){
        this.animalPhoto=animalPhoto;
        this.AnimalName=AnimalName;
        this.introduce=introduce;
        this.type=1;
    }


    public BmobFile getAnimalPhoto() {
        return animalPhoto;
    }

    public String getAnimalname() {
        return AnimalName;
    }

    public void setAnimalPhoto(BmobFile animalPhoto) {
        this.animalPhoto = animalPhoto;
    }

    public void setAnimalname(String animalname) {
        AnimalName = animalname;

    }

    @Override
    public int getItemType() {
        return type;

    }
}
