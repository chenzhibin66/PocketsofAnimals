package example.chaoyueteam.com.pocketsofanimals.util;

import android.util.Log;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import example.chaoyueteam.com.pocketsofanimals.db.AnimalIntroduction;

public class AnimalIntroductionUtil {
    private BmobFile bmobFile;

    public BmobFile getBmobFile(String animalName) {
        BmobQuery<AnimalIntroduction> bmobQuery = new BmobQuery<AnimalIntroduction>();
        bmobQuery.addWhereEqualTo("AnimalName", animalName);
        bmobQuery.findObjects(new FindListener<AnimalIntroduction>() {
            @Override
            public void done(List<AnimalIntroduction> object, BmobException e) {
                if (e == null) {
                    for (AnimalIntroduction animalIntroduction : object) {
                        bmobFile = animalIntroduction.getAnimalPhoto();
                    }
                } else {
                    Log.e("e", "上传失败");
                }
            }
        });
        return bmobFile;
    }
}
