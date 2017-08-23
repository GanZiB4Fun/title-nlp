package com.ganzib.mapper.local;

import com.ganzib.model.ArticleTag;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by GanZiBの智障 on 2017/6/28.
 */
@Mapper
public interface ArticleTagMapper {
    void insertTag(ArticleTag articleTag);

    void insertArticle10WTag(ArticleTag articleTag);
}
