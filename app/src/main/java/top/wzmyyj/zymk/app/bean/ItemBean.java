package top.wzmyyj.zymk.app.bean;

import java.util.List;
import java.util.Random;

import top.wzmyyj.zymk.R;

/**
 * Created by yyj on 2018/06/30. email: 2209011667@qq.com
 */

public class ItemBean {
    private int icon;
    private String title;
    private String summary;
    private String href;
    private List<BookBean> books;
    private int type;

    public ItemBean() {
    }

    private int[] icons = new int[]{
            R.mipmap.svg_pic_list_jd,
            R.mipmap.svg_pic_list_fire,
            R.mipmap.svg_pic_list_love,
            R.mipmap.svg_pic_list_fast,
            R.mipmap.svg_pic_list_dream,
            R.mipmap.svg_pic_list_new
    };

    private int icon() {
        Random rand = new Random();
        int i = rand.nextInt(6);
        return icons[i];
    }

    private int icon(String title) {
        if (title.equals("经典")) return icons[0];
        if (title.equals("独家")) return icons[5];
        if (title.equals("霸总")) return icons[2];
        if (title.equals("新番")) return icons[3];
        if (title.equals("燃")) return icons[1];
        if (title.equals("萌")) return icons[0];
        if (title.equals("锐")) return icons[5];
        if (title.equals("幻")) return icons[4];
        if (title.equals("完结")) return icons[5];
        if (title.equals("最新上架")) return icons[1];
        if (title.equals("最新更新")) return icons[4];
        return icons[0];
    }


    public ItemBean(String title, String summary, String href) {
        this.icon = icon(title);
        this.title = title;
        this.summary = summary;
        this.href = href;
    }


    public ItemBean(String title, String summary, String href, List<BookBean> books) {
        this.icon = icon(title);
        this.title = title;
        this.summary = summary;
        this.href = href;
        this.books = books;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        if (icon == 0) this.icon = icon();
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }


    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }
}

