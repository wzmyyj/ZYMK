package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 */

public class ComicBean {
    private long chapter_id;
    private String chapter_name;
    private String chapter_title;
    private int var;
    private int var_size;
    private String img_low;
    private String img_middle;
    private String img_high;
    private int price;

    public long getChapter_id() {
        return chapter_id;
    }

    public void setChapter_id(long chapter_id) {
        this.chapter_id = chapter_id;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public int getVar() {
        return var;
    }

    public void setVar(int var) {
        this.var = var;
    }

    public int getVar_size() {
        return var_size;
    }

    public void setVar_size(int var_size) {
        this.var_size = var_size;
    }

    public String getImg_low() {
        return img_low;
    }

    public void setImg_low(String img_low) {
        this.img_low = img_low;
    }

    public String getImg_middle() {
        return img_middle;
    }

    public void setImg_middle(String img_middle) {
        this.img_middle = img_middle;
    }

    public String getImg_high() {
        return img_high;
    }

    public void setImg_high(String img_high) {
        this.img_high = img_high;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
