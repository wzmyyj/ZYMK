package top.wzmyyj.zymk.app.bean;

import top.wzmyyj.zymk.app.data.Urls;

/**
 * Created by yyj on 2018/08/02. email: 2209011667@qq.com
 */

public class ChapterBean {
    private long chapter_id;
    private String chapter_name;
    private String chapter_title;
    private long create_time;
    private String chapter_addr;
    private int start_var;
    private int end_var;
    private int price;
    private int chapter_type;
    private String image_suffix;
    private Chapter_image chapter_image;

    public ChapterBean() {
    }

    public ChapterBean(long chapter_id) {
        this.chapter_id = chapter_id;
    }

    public void setChapter_id(long chapter_id) {
        this.chapter_id = chapter_id;
    }

    public long getChapter_id() {
        return chapter_id;
    }

    public void setChapter_name(String chapter_name) {
        this.chapter_name = chapter_name;
    }

    public String getChapter_name() {
        return chapter_name;
    }

    public void setChapter_title(String chapter_title) {
        this.chapter_title = chapter_title;
    }

    public String getChapter_title() {
        return chapter_title;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public long getCreate_time() {
        return create_time;
    }

    public void setChapter_addr(String chapter_addr) {
        this.chapter_addr = chapter_addr;
    }

    public String getChapter_addr() {
        return chapter_addr;
    }

    public void setStart_var(int start_var) {
        this.start_var = start_var;
    }

    public int getStart_var() {
        return start_var;
    }

    public void setEnd_var(int end_var) {
        this.end_var = end_var;
    }

    public int getEnd_var() {
        return end_var;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setChapter_type(int chapter_type) {
        this.chapter_type = chapter_type;
    }

    public int getChapter_type() {
        return chapter_type;
    }

    public void setImage_suffix(String image_suffix) {
        this.image_suffix = image_suffix;
    }

    public String getImage_suffix() {
        return image_suffix;
    }

    public void setChapter_image(Chapter_image chapter_image) {
        this.chapter_image = chapter_image;
    }

    public Chapter_image getChapter_image() {
        return chapter_image;
    }

    public class Chapter_image {

        private String low;
        private String middle;
        private String high;

        public void setLow(String low) {
            this.low = low;
        }

        public String getLow() {
            return low;
        }

        public void setMiddle(String middle) {
            this.middle = middle;
        }

        public String getMiddle() {
            return middle;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getHigh() {
            return high;
        }

    }

    public String getImageLow(int i) {
        return Urls.ZYMK_Comic + this.getChapter_image().getLow().replace("$$", "" + i);
    }

    public String getImageMiddle(int i) {
        return Urls.ZYMK_Comic + this.getChapter_image().getMiddle().replace("$$", "" + i);
    }

    public String getImageHigh(int i) {
        return Urls.ZYMK_Comic + this.getChapter_image().getHigh().replace("$$", "" + i);
    }

    public String getImageLow() {
        return getImageLow(this.start_var);
    }

    public String getImageMiddle() {
        return getImageMiddle(this.start_var);
    }

    public String getImageHigh() {
        return getImageHigh(this.start_var);
    }
}
