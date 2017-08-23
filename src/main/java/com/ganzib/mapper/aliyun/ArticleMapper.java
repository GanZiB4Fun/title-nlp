package com.ganzib.mapper.aliyun;

import com.ganzib.model.SubjectView;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by GanZiBの智障 on 2017/6/28.
 */
@Mapper
public interface ArticleMapper {

    List<String> getTitleList();

    List<String> getTitleListFrom10W(String source);


    List<SubjectView> getMovieView();

    List<String> getMovieTitle();
}
