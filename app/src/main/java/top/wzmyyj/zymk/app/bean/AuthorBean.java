package top.wzmyyj.zymk.app.bean;

import java.util.List;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class AuthorBean {
    private String avatar;
    private String name;
    private String fans_num;
    private String content;
    private List<BookBean> bookList;

    public AuthorBean(String avatar, String name, String fans_num, String content) {
        this.avatar = avatar;
        this.name = name;
        this.fans_num = fans_num;
        this.content = content;
    }

    public AuthorBean(String avatar, String name, String fans_num, String content, List<BookBean> bookList) {
        this.avatar = avatar;
        this.name = name;
        this.fans_num = fans_num;
        this.content = content;
        this.bookList = bookList;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFans_num() {
        return fans_num;
    }

    public void setFans_num(String fans_num) {
        this.fans_num = fans_num;
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

    public void setBookList(List<BookBean> bookList) {
        this.bookList = bookList;
    }
}
