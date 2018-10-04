package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;


public class Album extends BmobObject {
    private String animalName;
    private BmobFile newAnimalImage;
    private BmobFile animalImage;
    private String location;

    public void setMp3File(BmobFile mp3File) {
        this.mp3File = mp3File;
    }

    public BmobFile getMp3File() {

        return mp3File;
    }

    private String animalInformation;
    private  BmobFile mp3File;

    public String getAnimalName() {
        return animalName;
    }

    public BmobFile getAnimalImage() {
        return animalImage;
    }

    public String getLocation() {
        return location;
    }

    public String getAnimalInformation() {
        return animalInformation;
    }

    public BmobFile getNewAnimalImage() {
        return newAnimalImage;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalImage(BmobFile animalImage) {
        this.animalImage = animalImage;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAnimalInformation(String animalInformation) {
        this.animalInformation = animalInformation;
    }

    public void setNewAnimalImage(BmobFile newAnimalImage) {
        this.newAnimalImage = newAnimalImage;
    }
}
