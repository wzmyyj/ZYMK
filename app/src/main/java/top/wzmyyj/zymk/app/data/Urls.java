package top.wzmyyj.zymk.app.data;

/**
 * Created by yyj on 2018/06/23. email: 2209011667@qq.com
 */

public class Urls {
    // 主页
    public final static String ZYMK_Base = "https://m.zymk.cn/";
    // 全部漫画
    public final static String ZYMK_All = ZYMK_Base + "sort/all.html";
    // 搜索

    /////////////////////////// api
    public final static String ZYMK_BaseApi = "https://api.zymk.cn/";
    // 热门搜索
    public final static String API_HotSearch = "app_api/v5/gethotsearch/";
    // 匹配搜索
    public final static String API_SmartSearch = "app_api/v5/getsortlist_new/"; // kry
    // 漫画信息
    public final static String API_GetComic = "app_api/v5/getcomic/"; // comic_id
    // 漫画详细信息
    public final static String API_GetComicInfo = "app_api/v5/getcomicinfo/"; // comic_id


    //    // 经典
//    public static String ZYMK_Book_1="http://m.zymk.cn/book/1.html";
//    // 燃
//    public static String ZYMK_Book_2="http://m.zymk.cn/book/2.html";
//    // 萌
//    public static String ZYMK_Book_3="http://m.zymk.cn/book/3.html";
//    // 锐
//    public static String ZYMK_Book_4="http://m.zymk.cn/book/4.html";
//    // 幻
//    public static String ZYMK_Book_5="http://m.zymk.cn/book/5.html";
//    // 新番
//    public static String ZYMK_Book_6="http://m.zymk.cn/book/6.html";
//    // 独家
//    public static String ZYMK_Book_7="http://m.zymk.cn/book/7.html";
//    // 完结
//    public static String ZYMK_Book_8="http://m.zymk.cn/book/8.html";
//    // 霸总
//    public static String ZYMK_Book_10357="http://m.zymk.cn/book/10357.html";
    // 活动列表
    public static String ZYMK_Activity = "https://activity.zymk.cn/find/";
    // 天猫商城
    public static String ZYMK_Tmall = "https://zymk.m.tmall.com/";
    // 图片
    public static String ZYMK_Comic = "http://mhpic.zymkcdn.com/comic/";
    // 封面
    public static String ZYMK_Image = "https://image.zymkcdn.com/file/cover/?.jpg-300x400.webp";

    // GitHub
    public static String YYJ_GitHub = "https://github.com/wzmyyj/ZYMK";

    // 关于作者
    public static String YYJ_About = "http://wzmyyj.top/about";


}
