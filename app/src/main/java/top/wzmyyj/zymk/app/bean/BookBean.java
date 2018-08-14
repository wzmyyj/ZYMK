package top.wzmyyj.zymk.app.bean;

import java.util.List;

import top.wzmyyj.zymk.app.data.Urls;

/**
 * Created by yyj on 2018/07/05. email: 2209011667@qq.com
 */

public class BookBean {

    private int id;
    private String title;
    private String data_src;
    private String star;
    private String desc;
    private String num;
    private String ift;
    private String author;
    private long update_time;

    private String chapter;// 最新章节name
    private long chapter_id;// 最新章节ID

    private List<String> tags;


    public BookBean() {
    }

    public void setData_src(String data_src) {
        this.data_src = data_src;
    }

    public String getHref() {
        if (id == 0) return null;
        return Urls.ZYMK_Base + id;
    }

    public String getData_src() {
        if (data_src != null) {
            return data_src;
        }
        if (id == 0) return null;
        String s = "" + id;
        while (s.length() < 9) {
            s = "0" + s;
        }
        StringBuffer sb = new StringBuffer(s);
        sb.insert(6, '/');
        sb.insert(3, '/');
        String src = Urls.ZYMK_Image.replace("?", sb.toString());
        return src;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
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


    public long getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(long chapter_id) {
        this.chapter_id = chapter_id;
    }
}
