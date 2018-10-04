package cn.bmob.sdkdemo.bean;

import cn.bmob.v3.BmobObject;


public class Comment extends BmobObject {
    private String content;

    public Comment(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "content='" + content + '\'' +
                '}';
    }
}
