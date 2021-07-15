package top.wzmyyj.zymk.app.bean;

import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Transient;

import java.util.List;

import top.wzmyyj.zymk.app.data.Urls;

/**
 * Created by yyj on 2018/08/02. email: 2209011667@qq.com
 */
public class ChapterBean {

    @SerializedName("chapter_id")
    long chapterId;
    @SerializedName("chapter_name")
    String chapterName;
    @SerializedName("start_var")
    int startVar;
    @SerializedName("end_var")
    int endVar;
    @SerializedName("price")
    int price;
    @SerializedName("chapter_image")
    ChapterImage chapterImage;

    @Transient
    private List<ComicBean> comicList = null;

    public static class ChapterImage {
        @SerializedName("low")
        String low;
        @SerializedName("middle")
        String middle;
        @SerializedName("high")
        String high;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public int getStartVar() {
        return startVar;
    }

    public int getEndVar() {
        return endVar;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getFirstImageLow(int i) {
        return Urls.ZYMK_Comic + chapterImage.low.replace("$$", "" + i);
    }

    public String getImageMiddle(int i) {
        return Urls.ZYMK_Comic + chapterImage.middle.replace("$$", "" + i);
    }

    public String getImageHigh(int i) {
        return Urls.ZYMK_Comic + chapterImage.high.replace("$$", "" + i);
    }

    public String getFirstImageLow() {
        return getFirstImageLow(this.startVar);
    }

    public List<ComicBean> getComicList() {
        return comicList;
    }

    public void setComicList(List<ComicBean> comicList) {
        this.comicList = comicList;
    }
}
