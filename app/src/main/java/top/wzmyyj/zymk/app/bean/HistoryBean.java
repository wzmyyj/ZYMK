package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */

public class HistoryBean {
    private BookBean book;
    private ChapterBean chapter;
    private long read_time;

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public ChapterBean getChapter() {
        return chapter;
    }

    public void setChapter(ChapterBean chapter) {
        this.chapter = chapter;
    }

    public long getRead_time() {
        return read_time;
    }

    public void setRead_time(long read_time) {
        this.read_time = read_time;
    }
}
