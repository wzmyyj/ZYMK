package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */

public class MoreBox {

    private String title;
    private String content;
    private String href;
    private String figure;

    private List<BookBean> bookList;

    public MoreBox() {
    }

    public MoreBox(String title, String content, String figure) {
        this.title = title;
        this.content = content;
        this.figure = figure;
    }

    public MoreBox(String title, String content, String figure, List<BookBean> bookList) {
        this.title = title;
        this.content = content;
        this.figure = figure;
        this.bookList = bookList;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getFigure() {
        return figure;
    }

    public void setFigure(String figure) {
        this.figure = figure;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }

    public void setBookList(List<BookBean> bookList) {
        this.bookList = bookList;
    }
}
