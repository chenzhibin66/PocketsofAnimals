package example.chaoyueteam.com.pocketsofanimals.util;

import android.content.Context;
import android.util.Log;

import java.io.File;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import example.chaoyueteam.com.pocketsofanimals.db.Album;
import example.chaoyueteam.com.pocketsofanimals.db.AnimalIntroduction;
import example.chaoyueteam.com.pocketsofanimals.db.MyUser;

public class UserUtil {

    //注册 参数账号，密码，头像，性别，昵称，个性签名
    public void registered(String username,String password,String pic,
                           String sex,String nick,String personalityIntroduction){
        MyUser bu = new MyUser();
        bu.setUsername(username);
        bu.setPassword(password);
        bu.setPic(pic);
        bu.setPersonalityIntroduction(personalityIntroduction);
        bu.setNick(nick);
        bu.setSex(sex);
        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser s, BmobException e) {
                if(e==null){

                }else{
                    Log.e("",e.toString());
                }
            }
        });
    }

    //登录 账号，密码
    public void logIn(String username,String password){
        BmobUser.loginByAccount(username, password, new LogInListener<MyUser>() {

            @Override
            public void done(MyUser user, BmobException e) {
                if(user!=null){
                    Log.i("smile","用户登陆成功");
                }
            }
        });
    }

    //修改密码 旧密码，新密码
    public void updateUserPassword(String oldPwd,String newPwd){
        MyUser.updateCurrentUserPassword(oldPwd, newPwd, new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    //toast("密码修改成功，可以用新密码进行登录啦");
                }else{
                    //toast("失败:" + e.getMessage());
                }
            }
        });
    }
    //更改性别
    public void updateUserSex(String sex) {
        MyUser myUser = (MyUser)BmobUser.getCurrentUser();
        myUser.setSex(sex);
        myUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    //更改昵称
    public void updateUserNick(String nick) {
        MyUser myUser = (MyUser)BmobUser.getCurrentUser();
        myUser.setSex(nick);
        myUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    //更改个性签名
    public void updateUserPI(String pi) {
        MyUser myUser = (MyUser)BmobUser.getCurrentUser();
        myUser.setPersonalityIntroduction(pi);
        myUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {

            }
        });
    }

    //更改照片
    public void updateUserPic(final String pic) {
        File file = new File(pic);
        final BmobFile bmobFile_pic = new BmobFile(file);
        bmobFile_pic.uploadblock(new UploadFileListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    MyUser myUser = (MyUser) BmobUser.getCurrentUser();
                    myUser.setPic(pic);
                    myUser.save(new SaveListener<String>() {
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