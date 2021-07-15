package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;

/**
 * Created by yyj on 2018/07/11. email: 2209011667@qq.com
 */
public class MoreBox {

    private final String title;
    private final String content;
    private final String href;
    private final String figure;

    private final List<BookBean> bookList;

    public MoreBox(String href, String title, String content, String figure, List<BookBean> bookList) {
        this.href = href;
        this.title = title;
        this.content = content;
        this.figure = figure;
        this.bookList = bookList;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getHref() {
        return href;
    }

    public String getFigure() {
        return figure;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }
}
