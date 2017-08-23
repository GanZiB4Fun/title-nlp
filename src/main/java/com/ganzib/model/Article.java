package com.ganzib.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.io.Serializable;

/**
 * 文章实体类
 *
 * @author frankx
 * @date 2016年8月12日
 */
@JsonInclude(Include.NON_NULL) // 不序列化空值，既是json中不显示值为空的键值对
public class Article implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
