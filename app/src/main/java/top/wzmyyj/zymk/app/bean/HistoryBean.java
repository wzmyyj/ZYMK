package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */

public class HistoryBean {
    private long id;
    private long chapter_id;
    private String chapter_name;
    private BookBean book;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }
}
