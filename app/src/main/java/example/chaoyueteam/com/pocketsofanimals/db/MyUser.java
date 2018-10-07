package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;

public class MyUser extends BmobUser {

    private String sex;
    private String nick;
    private String signature;
    private BmobFile picture;

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public BmobFile getPicture() {
        return picture;
    }

    public void setPicture(BmobFile picture) {
        this.picture = picture;
    }
}

