package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobObject;

public class Album extends BmobObject {
    private String animalName;
    private String newAnimalImage;
    private String animalImage;
    private String location;
    private String animalInformation;

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

    public String getNewAnimalImage() {
        return newAnimalImage;
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

    public void setNewAnimalImage(String newAnimalImage) {
        this.newAnimalImage = newAnimalImage;
    }
}
