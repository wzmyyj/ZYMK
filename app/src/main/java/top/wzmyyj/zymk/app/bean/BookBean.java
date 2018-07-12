package top.wzmyyj.zymk.app.bean;

import java.util.List;

/**
 * Created by yyj on 2018/07/05. email: 2209011667@qq.com
 */

public class BookBean {

    private String title;
    private String data_src;
    private String star;
    private String chapter;
    private String href;
    private String desc;

    private List<String> tags;

    public BookBean() {
    }

    public BookBean(String title, String data_src, String star, String href, String desc) {
        this.title = title;
        this.data_src = data_src;
        this.star = star;
        this.href = href;
        this.desc = desc;
    }

    public BookBean(String title, String data_src, String star, String chapter, String href, String desc) {
        this.title = title;
        this.data_src = data_src;
        this.star = star;
        this.chapter = chapter;
        this.href = href;
        this.desc = desc;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData_src() {
        return data_src;
    }

    public void setData_src(String data_src) {
        this.data_src = data_src;
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

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
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
}
