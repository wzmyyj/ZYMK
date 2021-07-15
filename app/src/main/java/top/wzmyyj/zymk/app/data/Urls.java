package top.wzmyyj.zymk.app.data;

/**
 * Created by yyj on 2018/06/23. email: 2209011667@qq.com
 */
public class Urls {
    // 主页
    public final static String ZYMK_Base = "https://m.zymk.cn/";
    // 全部漫画
    public final static String ZYMK_All = ZYMK_Base + "sort/all.html";

    // api
    public final static String ZYMK_BaseApi = "https://api.zymk.cn/";
    // 热门搜索
    public final static String API_HotSearch = "app_api/v5/gethotsearch/";
    // 匹配搜索
    public final static String API_SmartSearch = "apinew/getsortlist_new/"; // key
    // 漫画信息
    public final static String API_GetComic = "app_api/v5/getcomic/"; // comic_id
    // 漫画详细信息
    public final static String API_GetComicInfo = "app_api/v5/getcomicinfo/"; // comic_id

    // 活动列表
    public final static String ZYMK_Activity = "https://activity.zymk.cn/find/";
    // 天猫商城
    public final static String ZYMK_TMall = "https://zymk.m.tmall.com/";
    // 图片
    public final static String ZYMK_Comic = "http://mhpic.xiaomingtaiji.net/comic/";
    // 封面
    public final static String ZYMK_Image = "https://image.zymkcdn.com/file/cover/?.jpg-300x400.webp";

    // GitHub
    public final static String YYJ_GitHub = "https://github.com/wzmyyj/ZYMK";
    // 关于作者
    public final static String YYJ_About = "http://wzmyyj.top/about";
}
