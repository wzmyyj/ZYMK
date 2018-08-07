package top.wzmyyj.zymk.app.event;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/08/07. email: 2209011667@qq.com
 */

public class SelectBookEvent {
    private BookBean book;

    public SelectBookEvent(BookBean book) {
        this.book = book;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }
}
