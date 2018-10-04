package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class AnimalIntroduction extends BmobObject {
    private BmobFile animalPhoto;
    private String Animalname;

    public BmobFile getAnimalPhoto() {
        return animalPhoto;
    }

    public String getAnimalname() {
        return Animalname;
    }

    public void setAnimalPhoto(BmobFile animalPhoto) {
        this.animalPhoto = animalPhoto;
    }

    public void setAnimalname(String animalname) {
        Animalname = animalname;
    }
}
