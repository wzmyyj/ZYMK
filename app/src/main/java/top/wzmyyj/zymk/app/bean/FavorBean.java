package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/08/16. email: 2209011667@qq.com
 */

public class FavorBean {
    private BookBean book;
    private boolean isUnRead;

    public FavorBean() {
    }

    public FavorBean(BookBean book) {
        this.book = book;
    }

    public FavorBean(BookBean book, boolean isUnRead) {
        this.book = book;
        this.isUnRead = isUnRead;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public boolean isUnRead() {
        return isUnRead;
    }

    public void setUnRead(boolean unRead) {
        isUnRead = unRead;
    }
}
