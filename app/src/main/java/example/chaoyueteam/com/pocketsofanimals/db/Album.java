package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobDate;

public class Album extends BmobObject {
    private BmobDate date;
    private String animalName;
    private String animalImage;
    private String location;
    private String animalInformation;

    public BmobDate getDate() {
        return date;
    }

    public String getAnimalName() {
        return animalName;
    }

    public String getAnimalImage() {
        return animalImage;
    }

    public String getLocation() {
        return location;
    }

    public String getAnimalInformation() {
        return animalInformation;
    }

    public void setDate(BmobDate date) {
        this.date = date;
    }

    public void setAnimalName(String animalName) {
        this.animalName = animalName;
    }

    public void setAnimalImage(String animalImage) {
        this.animalImage = animalImage;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setAnimalInformation(String animalInformation) {
        this.animalInformation = animalInformation;
    }
}
