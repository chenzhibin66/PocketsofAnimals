package example.chaoyueteam.com.pocketsofanimals.db;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {
    private String sex;
    private String nick;
    private String personalityIntroduction;
    private String personname;

    public String getSex() {
        return this.sex;
    }

    public String getNick() {
        return this.nick;
    }

    public String getPersonalityIntroduction() {
        return personalityIntroduction;
    }

    public String getPersonname() {
        return personname;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public void setPersonalityIntroduction(String personalityIntroduction) {
        personalityIntroduction = personalityIntroduction;
    }

    public void setPersonname(String personname) {
        this.personname = personname;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

