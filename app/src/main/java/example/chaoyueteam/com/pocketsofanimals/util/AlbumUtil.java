package example.chaoyueteam.com.pocketsofanimals.util;

import android.util.Log;

import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.SaveListener;
import example.chaoyueteam.com.pocketsofanimals.db.Album;

public class AlbumUtil {
    //把动物照片信息存到服务器，参数动物名字，动物图片，拍照地点，动物描述
    public void setAlbum(String animalName,String animalImage,String location,String animalInformation){
        Album album = new Album();
        album.setAnimalName(animalName);
        album.setAnimalImage(animalImage);
        album.setLocation(location);
        album.setAnimalInformation(animalInformation);
        album.save(new SaveListener<String>() {
            @Override
            public void done(String objectId, BmobException e) {
                if(e==null){

                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
    public void getNewAlbum(String animalName){
        BmobQuery<Album> query = new BmobQuery<Album>();
        query.addWhereEqualTo("animalName", animalName);
        query.setLimit(1);
        query.findObjects(new FindListener<Album>() {
            @Override
            public void done(List<Album> object, BmobException e) {
                if(e==null){
                    for (Album album : object) {
                        album.getAnimalImage();
                        album.getAnimalInformation();
                    }
                }else{
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });
    }
}
