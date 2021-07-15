package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.app.bean.XiBean;
import top.wzmyyj.zymk.app.bean.ZiBean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */
public class DetailsBox {

    private BookBean book;
    private final XiBean xi;
    private final MuBean mu;
    private final ZiBean zi;
    private final List<BookBean> xgBookList;

    public DetailsBox(BookBean book, XiBean xi, MuBean mu, ZiBean zi, List<BookBean> xgBookList) {
        this.book = book;
        this.xi = xi;
        this.mu = mu;
        this.zi = zi;
        this.xgBookList = xgBookList;
    }

    public BookBean getBook() {
        return book;
    }

    public void setBook(BookBean book) {
        this.book = book;
    }

    public XiBean getXi() {
        return xi;
    }

    public MuBean getMu() {
        return mu;
    }

    public ZiBean getZi() {
        return zi;
    }

    public List<BookBean> getXgBookList() {
        return xgBookList;
    }
}
