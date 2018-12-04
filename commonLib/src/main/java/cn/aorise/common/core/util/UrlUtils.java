package cn.aorise.common.core.util;

import android.view.View;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.annotations.NonNull;

/**
 * <pre>
 *     author : Mark
 *     e-mail : makun.cai@aorise.org
 *     time   : 2018/06/15
 *     desc   : URL工具类
 *     version: 1.0
 * </pre>
 */
public class UrlUtils {

    /**
     * 读取baseurl
     * @param url
     * @return
     */
    public static String getBasUrl(@NonNull String url) {
        String head = "";
        int index = url.indexOf("://");
        if (index != -1) {
            head = url.substring(0, index + 3);
            url = url.substring(index + 3);
        }
        index = url.indexOf("/");
        if (index != -1) {
            url = url.substring(0, index + 1);
        }
        return head + url;
    }

    /**
     * FileName
     * @param url
     * @return
     */
    public static String getFileName(@NonNull String url) {
        int index = url.lastIndexOf("/");
        return url.substring(index + 1);
    }

    /**
     * url是否是网络地址
     * @param url
     * @return
     */
    public static boolean isNetUrl(@NonNull String url){
        if (url.toLowerCase().startsWith("http") || url.toLowerCase().startsWith("rtsp") || url.toLowerCase().startsWith("mms")) {
            return true;
        }
        return false;
    }

    /**
     * 获取网络地址的文件类型
     * @param url
     * @return
     */
    public static String parseSuffix(String url) {
        Pattern pattern = Pattern.compile("\\S*[?]\\S*");
        Matcher matcher = pattern.matcher(url);
        String[] spUrl = url.toString().split("/");
        int len = spUrl.length;
        String endUrl = spUrl[len - 1];

        if(matcher.find()) {
            String[] spEndUrl = endUrl.split("\\?");
            return spEndUrl[0].split("\\.")[1];
        }
        return endUrl.split("\\.")[1];
    }

}
