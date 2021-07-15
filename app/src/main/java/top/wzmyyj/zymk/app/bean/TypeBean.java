package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */
public class TypeBean {

    private String title;
    private String href;
    private final String dataSrc;

    public TypeBean(String title, String href, String data_src) {
        this.title = title;
        this.href = href;
        this.dataSrc = data_src;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getDataSrc() {
        return dataSrc;
    }
}
