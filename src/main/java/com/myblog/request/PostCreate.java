package com.myblog.request;

public class PostCreate {
    public String title;
    public String content;

    //null로 들어오는거는 set이 없기떄문
    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "PostCreate{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}

