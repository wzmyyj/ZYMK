package top.wzmyyj.zymk.common.java;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yyj on 2018/04/26. email: 2209011667@qq.com
 * Vanessa 凡妮莎，第八个七骑士，拥有控制时间的沙漏和魔法。
 * 时间转换工具类。
 */


public class Vanessa {


    public static String long2str(long l, String format) {
        if (l == 0) {
            return "";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        String s = sdf.format(new Date(l));
        return s;
    }

    public static long str2long(String time, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        long l = 0;
        try {
            l = sdf.parse(time).getTime();
        } catch (ParseException e) {
//            e.printStackTrace();
        }
        return l;
    }

    public static long getTime() {
        Date d = new Date();
        return d.getTime();
    }

    @SuppressLint("SimpleDateFormat")
    public static String getEasyTime(long l) {
        Date d = new Date();
        String s;
        long t = Math.abs(d.getTime() - l);
        //一天内
        if (t < 24L * 60 * 60 * 1000) {
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
            s = sdf.format(new Date(l));
        } else if (t < 30L * 24 * 60 * 60 * 1000) {// 30天内
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd HH:mm");
            s = sdf.format(new Date(l));
        } else if (t < 12L * 30 * 24 * 60 * 60 * 1000) {// 12个月内
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            s = sdf.format(new Date(l));
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            s = sdf.format(new Date(l));
        }
        return s;
    }

    public static String getEasyText(long l) {
        Date d = new Date();
        String s;
        long t = Math.abs(d.getTime() - l);
        if (t <= 5L * 60 * 1000) {
            s = "刚刚";
        } else if (t < 60L * 60 * 1000) {
            int a = (int) (t / (60 * 1000));
            s = a + "分钟前";
        } else if (t < 24L * 60 * 60 * 1000) {
            int a = (int) (t / (60 * 60 * 1000));
            s = a + "小时前";
        } else if (t < 30L * 24 * 60 * 60 * 1000) {
            int a = (int) (t / (24L * 60 * 60 * 1000));
            s = a + "天前";
        } else {
            s = getEasyTime(l);
        }

        return s;
    }

    // 是否在i天内.
    public static boolean isInDay(long l, int i) {
        Date d = new Date();
        long t = Math.abs(d.getTime() - l);
        return t < i * 24L * 60 * 60 * 1000;
    }
}
