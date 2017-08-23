package com.ganzib.util;

import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * 网页转图片处理类，使用外部CMD 需安装 phantomjs-2.1.1-windows
 *
 * Created by GanZiB on 2017/3/20.
 */
@Service
public class PhantomTools {
    // windows下phantomjs位置
    private static final String path = "C:/phantomjs-2.1.1-windows/";
    // 要执行的js，要使用决定路径
    private static final String jsPath = path + "bin/getPage.js ";

    /**
     *
     * 根据传入的url，调用phantomjs进行下载，并返回base64流信息
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String getPageText(String url) throws IOException {
        Runtime rt = Runtime.getRuntime();

        final String cmd = path + "bin/phantomjs " + jsPath + url.trim();
        // 执行CMD命令
        Process process = rt.exec(cmd);

        BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
        StringBuffer sbf = new StringBuffer();
        String tmp = "";
        while ((tmp = br.readLine()) != null) {
            sbf.append(tmp);
        }
        return sbf.toString();
    }
}
