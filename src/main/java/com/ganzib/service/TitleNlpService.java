package com.ganzib.service;

import com.ganzib.constant.WebConstant;
import com.ganzib.mapper.aliyun.ArticleMapper;
import com.ganzib.mapper.local.ArticleTagMapper;
import com.ganzib.model.ArticleTag;
import com.ganzib.model.SubjectView;
import com.ganzib.util.HttpRequest;
import org.ansj.splitWord.analysis.NlpAnalysis;
import org.ansj.splitWord.analysis.ToAnalysis;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.downloader.HttpClientDownloader;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * Created by GanZiBの智障 on 2017/6/28.
 */
@Service
public class TitleNlpService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private ArticleTagMapper articleTagMapper;

    private static Site site = Site.me().
            setUserAgent(WebConstant.USER_AGENT).
            addHeader("accept", "application/json").
            addHeader("X-Token", "2-82GG6k.16147.6ozIpXuAnTK-").
            addHeader("Accept-Language", WebConstant.ACCEPT_LANGUAGE).addHeader("Content-type", "application/json")
            .setSleepTime(3000).setCharset("UTF-8");


    /**
     * 程序开始
     */
    @PostConstruct
    public void start(){
//        String api_url = "http://api.bosonnlp.com/tag/analysis";
//
//        String params = "?space_mode=3&oov_level=3&t2s=1&&special_char_conv=0&data=";
//        String  data = string2Unicode("难怪设计师都用苹果");
//        String re = HttpRequest.sendPost(api_url,params+data);
//        System.out.println(re);
        /*采集文章标题关系图
        List<String> list = articleMapper.getTitleList();*/
//        List<String> list = articleMapper.getTitleListFrom10W("movie");
//        List<SubjectView> list = articleMapper.getMovieView();
        List<String> list = articleMapper.getMovieTitle();
        for (String title : list){
//            if (subjectView==null|| StringUtils.isBlank(subjectView.getTitle())||StringUtils.isEmpty(subjectView.getTitle())) continue;
//            String titleTamp = subjectView.getTitle()+subjectView.getContent();
//            String title = titleTamp.replaceAll("</?[^>]+>", "");
            if(title==null||StringUtils.isBlank(title)) continue;
            title = title.replaceAll("[\\pP\\pZ\\pS\\pM\\pC]+"," ");
            title = ToDBC(title);
//            System.out.println(title);
            saveTagsBAnsj(title);
        }
    }

    /**
     * 根据Ansj中文文本分词将title进行分词 取其中为名词的分词进行保存
     * @param title
     */
    public void saveTagsBAnsj(String title){
        String toTags[] = ToAnalysis.parse(title).toString().split(",");
        String nlpTags[] = NlpAnalysis.parse(title).toString().split(",");
        List<String> totagList = Arrays.asList(toTags);
        List<String> nlptagList = Arrays.asList(nlpTags);
        Set<String> totleSet = new TreeSet<>();
        totleSet.addAll(totagList);
        totleSet.addAll(nlptagList);
        List<String> totelList = new ArrayList<>(totleSet);
        List<ArticleTag> articleTags = new ArrayList<>();
        for (int i = 0; i < totelList.size(); i++) {
            for (int j = i+1; j < totelList.size(); j++) {
                if (totelList.get(i).equals(totelList.get(j))||"".equals(totelList.get(i))||"".equals(totelList.get(j))){
                    continue;
                }
                if(totelList.get(i).length()<3||totelList.get(j).length()<3){
                    continue;
                }
                if (totelList.get(i).contains("n")&totelList.get(j).contains("n")){
                    ArticleTag articleTag = new ArticleTag();
                    String first_tag = subTag(totelList.get(i));
                    String second_tag = subTag(totelList.get(j));
                    if(first_tag.length()<2||second_tag.length()<2) continue;
                    articleTag.setFirst_tag(first_tag);
                    articleTag.setSecond_tag(second_tag);
                    articleTags.add(articleTag);
                }
            }
        }
        for (ArticleTag articletag : articleTags){
            articletag.setCategory("movie");
//            articleTagMapper.insertTag(articletag);
            articleTagMapper.insertArticle10WTag(articletag);
            System.out.println(articletag);

        }
    }

    /**
     * 清洗分词 将分词中的描述字段去除 如： /n /nr 等
     * @param tag
     * @return
     */
    public String subTag(String tag){
        String result ;
        result = tag.substring(0,tag.indexOf("/"));
        return result;
    }

    public static String string2Unicode(String string) {

        StringBuffer unicode = new StringBuffer();

        for (int i = 0; i < string.length(); i++) {

            // 取出每一个字符
            char c = string.charAt(i);

            // 转换为unicode
            unicode.append("\\u" + Integer.toHexString(c));
        }

        return unicode.toString();
    }

    /**
     * 全角转半角
     * @param input String.
     * @return 半角字符串
     */
    public static String ToDBC(String input) {


        char c[] = input.toCharArray();
        for (int i = 0; i < c.length; i++) {
            if (c[i] == '\u3000') {
                c[i] = ' ';
            } else if (c[i] > '\uFF00' && c[i] < '\uFF5F') {
                c[i] = (char) (c[i] - 65248);

            }
        }
        String returnString = new String(c);

        return returnString;
    }


}
