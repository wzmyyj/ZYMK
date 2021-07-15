package top.wzmyyj.zymk.app.bean;

import java.util.List;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */
public class AuthorBean {

    private final String avatar;
    private String name;
    private final String fansNum;
    private String content;
    private final List<BookBean> bookList;

    public AuthorBean(String avatar, String name, String fans_num, String content, List<BookBean> bookList) {
        this.avatar = avatar;
        this.name = name;
        this.fansNum = fans_num;
        this.content = content;
        this.bookList = bookList;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFansNum() {
        return fansNum;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<BookBean> getBookList() {
        return bookList;
    }
}
