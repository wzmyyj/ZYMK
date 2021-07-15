package top.wzmyyj.zymk.app.bean;

import java.util.List;

import top.wzmyyj.zymk.app.data.Urls;

/**
 * Created by yyj on 2018/07/05. email: 2209011667@qq.com
 */
public class BookBean {

    private int id;
    private String title;
    private String dataSrc;
    private String star;
    private String desc;
    private String num;
    private String ift;
    private String author;
    private long updateTime;
    private String chapter;// 最新章节name
    private long chapterId;// 最新章节ID
    private List<String> tags;

    public void setDataSrc(String dataSrc) {
        this.dataSrc = dataSrc;
    }

    public String getHref() {
        if (id == 0) return null;
        return Urls.ZYMK_Base + id;
    }

    public String getDataSrc() {
        if (dataSrc != null) return dataSrc;
        if (id == 0) return null;
        StringBuilder sb = new StringBuilder();
        sb.append(id);
        while (sb.length() < 9) {
            sb.insert(0, "0");
        }
        sb.insert(6, '/');
        sb.insert(3, '/');
        return Urls.ZYMK_Image.replace("?", sb.toString());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(long updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public String getChapter() {
        return chapter;
    }

    public void setChapter(String chapter) {
        this.chapter = chapter;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getIft() {
        return ift;
    }

    public void setIft(String ift) {
        this.ift = ift;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public long getChapterId() {
        return chapterId;
    }

    public void setChapterId(long chapterId) {
        this.chapterId = chapterId;
    }
}
