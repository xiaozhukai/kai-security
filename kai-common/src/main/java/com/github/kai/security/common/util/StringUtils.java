package com.github.kai.security.common.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TODO: 字符串工具
 *
 * Author: kai
 * CreateDate: 2017/9/17
 * CreateTime: 12:38
 */
public class StringUtils {

    /**
     * TODO:获取匹配字符串
     * v:查找的字符串长
     * code:匹配的参数长度
     * redirect:结尾的锚点
     *
     * Author: kai
     * CreateDate: 2017/9/17
     * CreateTime: 1:44
     */
    public static String getInfo(String v,String code,String redirect){
        Pattern p= Pattern.compile(code+"(.*?)"+redirect);
        Matcher matcher = p.matcher(v);
        while (matcher.find()) {
            return matcher.group(1);
        }
        return null;
    }

    /**
     * TODO:
     * 解析字符串数据
     * Author: kai
     * CreateDate: 2017/9/17
     * CreateTime: 2:47
     */
    public static String analysis(String v,String start,String end){
        int startIndex = v.indexOf("(");
        int endIndex = v.lastIndexOf(")");
        return v.substring(startIndex+1, endIndex);
    }


}
