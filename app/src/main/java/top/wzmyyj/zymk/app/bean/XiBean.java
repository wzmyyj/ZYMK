package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class XiBean {
    private AuthorBean author;
    private String juqing;

    public XiBean(AuthorBean author, String juqing) {
        this.author = author;
        this.juqing = juqing;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public void setAuthor(AuthorBean author) {
        this.author = author;
    }

    public String getJuqing() {
        return juqing;
    }

    public void setJuqing(String juqing) {
        this.juqing = juqing;
    }
}
