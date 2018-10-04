package example.chaoyueteam.com.pocketsofanimals.util;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.LogInListener;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UpdateListener;
import example.chaoyueteam.com.pocketsofanimals.db.MyUser;
import example.chaoyueteam.com.pocketsofanimals.modules.RegisterActivity;

public class UserUtil {
    //注册 参数账号，密码，头像，性别，昵称，个性签名
    public void registered(String username,String password,String pic, String sex,String nick,String personalityIntroduction){
        MyUser bu = new MyUser();
        bu.setUsername(username);
        bu.setPassword(password);
        bu.setPicture(pic);
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
                }else {

                }
            }
        });
    }


    //更改个人信息  参数图片，性别，昵称，个性签名
    public void updateUserImformation(String pic,String sex,

                                      String nick,String personalityIntroduction) {
        MyUser myUser = new MyUser();
        myUser.setSex(sex);
        myUser.setNick(nick);

        myUser.setPicture(pic);


        myUser.setPersonalityIntroduction(personalityIntroduction);
        myUser.update(new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    //toast("更新用户信息成功");
                }else{
                    //toast("更新用户信息失败:" + e.getMessage());
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
}
