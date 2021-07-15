package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */
public class NewBox {

    private final List<BookBean> bookList1;
    private final List<BookBean> bookList2;

    public NewBox(List<BookBean> bookList1, List<BookBean> bookList2) {
        this.bookList1 = bookList1;
        this.bookList2 = bookList2;
    }

    public List<BookBean> getBookList1() {
        return bookList1;
    }

    public List<BookBean> getBookList2() {
        return bookList2;
    }
}
