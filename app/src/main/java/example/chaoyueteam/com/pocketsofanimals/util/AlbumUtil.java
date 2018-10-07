package example.chaoyueteam.com.pocketsofanimals.util;

import android.util.Log;

import java.io.File;
import java.util.List;

import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;
import example.chaoyueteam.com.pocketsofanimals.db.Album;

public class AlbumUtil {
    private Album album;

    public Album setAlbuma(String path_imag, String path_mp3, final String annimalName,
                           final String animalIformation) {
        File file_image = new File(path_imag);
        File file_mp3 = new File(path_mp3);
        final BmobFile bmobFile_imag = new BmobFile(file_image);
        final BmobFile bmobFile_mp3 = new BmobFile(file_mp3);
        bmobFile_imag.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    bmobFile_mp3.uploadblock(new UploadFileListener() {
                        @Override
                        public void done(BmobException e) {
                            if (e == null) {
                                Album album = new Album();
                                album.setMp3File(bmobFile_mp3);
                                album.setAnimalImage(bmobFile_imag);
                                album.setAnimalName(annimalName);
                                album.setAnimalInformation(animalIformation);
                                album.save(new SaveListener<String>() {
                                    @Override
                                    public void done(String s, BmobException e) {
                                        if (e == null) {
                                            Log.d("bmob", "成功");
                                        } else {
                                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                        }
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });
        return album;
    }

    public void setAlbuma(String path_imag, String path_mp3, String path_newimage, final String annimalName, final String animalIformation) {
        File file_image = new File(path_imag);
        File file_mp3 = new File(path_mp3);
        File file_newimage = new File(path_newimage);
        final BmobFile bmobFile_imag = new BmobFile(file_image);
        final BmobFile bmobFile_mp3 = new BmobFile(file_mp3);
        final BmobFile bmobFile_newImage = new BmobFile(file_newimage);
        bmobFile_newImage.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                bmobFile_imag.uploadblock(new UploadFileListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            bmobFile_mp3.uploadblock(new UploadFileListener() {
                                @Override
                                public void done(BmobException e) {
                                    if (e == null) {
                                        Album album = new Album();
                                        album.setMp3File(bmobFile_mp3);
                                        album.setAnimalImage(bmobFile_imag);
                                        album.setAnimalName(annimalName);
                                        album.setAnimalInformation(animalIformation);
                                        album.setNewAnimalImage(bmobFile_newImage);
                                        album.save(new SaveListener<String>() {
                                            @Override
                                            public void done(String s, BmobException e) {
                                                if (e == null) {
                                                    Log.d("bmob", "成功");
                                                } else {
                                                    Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                                                }
                                            }
                                        });
                                    }
                                }
                            });
                        }
                    }
                });
            }
        });

    }
}

