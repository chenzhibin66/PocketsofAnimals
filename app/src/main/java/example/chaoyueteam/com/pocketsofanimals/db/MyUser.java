package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
    private String sex;
    private String nick;
    private String personalityIntroduction;
    private String pic;

    public String getSex() {
        return this.sex;
    }

    public String getNick() {
        return this.nick;
    }

    public String getPersonalityIntroduction() {
        return personalityIntroduction;
    }

    public String getPic() {
        return pic;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPersonalityIntroduction(String personalityIntroduction) {
        this.personalityIntroduction = personalityIntroduction;
    }


    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

