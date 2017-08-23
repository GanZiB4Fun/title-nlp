package com.ganzib.model;

/**
 * Created by GanZiBの智障 on 2017/6/28.
 */
public class ArticleTag {
    private String first_tag;
    private String second_tag;
    private String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getFirst_tag() {
        return first_tag;
    }

    public void setFirst_tag(String first_tag) {
        this.first_tag = first_tag;
    }

    public String getSecond_tag() {
        return second_tag;
    }

    public void setSecond_tag(String second_tag) {
        this.second_tag = second_tag;
    }

    @Override
    public String toString() {
        return "ArticleTag{" +
                "first_tag='" + first_tag + '\'' +
                ", second_tag='" + second_tag + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
