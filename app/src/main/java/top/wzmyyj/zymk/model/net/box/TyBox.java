package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/29. email: 2209011667@qq.com
 */

public class TyBox {
    private String base;
    private String title;
    private String next;
    private List<BookBean> books;

    public TyBox() {
    }

    public TyBox(String base, String title, String next, List<BookBean> books) {
        this.base = base;
        this.title = title;
        this.next = next;
        this.books = books;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public List<BookBean> getBooks() {
        return books;
    }

    public void setBooks(List<BookBean> books) {
        this.books = books;
    }
}
