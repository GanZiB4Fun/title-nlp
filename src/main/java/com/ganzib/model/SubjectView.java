package com.ganzib.model;

/**
 * Created by GanZiBの智障 on 2017/8/11.
 */
public class SubjectView {
    private String title;
    private String content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "SubjectView{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
