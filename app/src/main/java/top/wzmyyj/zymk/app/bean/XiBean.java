package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */
public class XiBean {

    private final AuthorBean author;
    private final String plot;

    public XiBean(AuthorBean author, String plot) {
        this.author = author;
        this.plot = plot;
    }

    public AuthorBean getAuthor() {
        return author;
    }

    public String getPlot() {
        return plot;
    }
}
