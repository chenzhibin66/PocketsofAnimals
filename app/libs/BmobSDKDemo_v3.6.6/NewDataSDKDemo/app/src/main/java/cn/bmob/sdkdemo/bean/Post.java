package cn.bmob.sdkdemo.bean;

import cn.bmob.v3.BmobObject;


public class Post extends BmobObject {
    private String content;
    private Comment comment;
    private String name;
    private int postId;
    private MyUser author;

    public MyUser getAuthor() {
        return author;
    }

    public void setAuthor(MyUser author) {
        this.author = author;
    }

    public Post(String content, Comment comment) {
        this.content = content;
        this.comment = comment;
    }

    public Comment getComment() {
        return comment;
    }

    public void setComment(Comment comment) {
        this.comment = comment;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Post{" +
                "content='" + content + '\'' +
                ", comment=" + comment +
                ", name='" + name + '\'' +
                ", postId=" + postId +
                ", author=" + author +
                '}';
    }
}
