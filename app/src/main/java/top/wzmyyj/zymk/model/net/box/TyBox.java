package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/29. email: 2209011667@qq.com
 */
public class TyBox {

    private final String base;
    private final String title;
    private final String next;
    private final List<BookBean> books;

    public TyBox(String base, String title, String next, List<BookBean> books) {
        this.base = base;
        this.title = title;
        this.next = next;
        this.books = books;
    }

    public String getBase() {
        return base;
    }

    public String getTitle() {
        return title;
    }

    public String getNext() {
        return next;
    }

    public List<BookBean> getBooks() {
        return books;
    }
}
