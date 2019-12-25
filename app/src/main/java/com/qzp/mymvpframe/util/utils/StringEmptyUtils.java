package com.qzp.mymvpframe.util.utils;/**
 * Created by admin on 2018/5/29.
 */

import android.icu.text.SimpleDateFormat;
import android.text.TextUtils;

import java.util.Date;

/**
 * @author qzp at 2018/5/29 and introduction:
 */
public class StringEmptyUtils {


    /**
     * 判断字符串是否为 null 或长度为 0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(final CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为 null 或全为空白字符
     *
     * @param str 待校验字符串
     * @return {""}: null 或全空白字符<br> {str}: 不为 null 且不全空白字符
     */
    public static String isEmptyResuleString(String str){
        if (TextUtils.isEmpty(str) || str ==null){
            return "";
        }
        return str;
    }


    /**
     * 判断字符串是否为 null 或全为空白字符
     *
     * @param str 待校验字符串
     * @return {"0"}: null 或全空白字符<br> {string类型int值}: 不为 null 且不全空白字符
     */
    public static String isEmptyResuleStringInt(String str){
        if (str ==null ||TextUtils.isEmpty(str) ||  str.equals("null")){
            return "0";
        }
        return str;
    }


    /**
     * 判断字符串是否为 null 或全为空白字符
     *
     * @param str 待校验字符串
     * @return {"0"}: null 或全空白字符<br> {string类型int值}: 不为 null 且不全空白字符
     */
    public static String isEmptyResuleString1(String str){
        if (TextUtils.isEmpty(str) || str ==null){
            return "1";
        }
        return str;
    }

    public static String timeToYear(String str){
        String time = "";
        if (TextUtils.isEmpty(str) || str ==null){
            return time;
        }
        SimpleDateFormat sdf = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
//            sdf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm");
            sdf = new SimpleDateFormat("yyyy-MM-dd");
            long  l = Long.valueOf(str);
            time = sdf.format(new Date(l));//单位秒
        }


        return time;
    }
}
