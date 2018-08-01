package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/13. email: 2209011667@qq.com
 */

public class RankBox {
    List<BookBean> bookList1;
    List<BookBean> bookList2;
    List<BookBean> bookList3;

    public RankBox(List<BookBean> bookList1, List<BookBean> bookList2, List<BookBean> bookList3) {
        this.bookList1 = bookList1;
        this.bookList2 = bookList2;
        this.bookList3 = bookList3;
    }

    public List<BookBean> getBookList1() {
        return bookList1;
    }

    public void setBookList1(List<BookBean> bookList1) {
        this.bookList1 = bookList1;
    }

    public List<BookBean> getBookList2() {
        return bookList2;
    }

    public void setBookList2(List<BookBean> bookList2) {
        this.bookList2 = bookList2;
    }

    public List<BookBean> getBookList3() {
        return bookList3;
    }

    public void setBookList3(List<BookBean> bookList3) {
        this.bookList3 = bookList3;
    }
}
