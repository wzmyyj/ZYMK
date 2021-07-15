package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/08/14. email: 2209011667@qq.com
 */
public class HistoryBean {

    private BookBean book;
    private ChapterBean chapter;
    private long readTime;

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

    public long getReadTime() {
        return readTime;
    }

    public void setReadTime(long readTime) {
        this.readTime = readTime;
    }
}
