package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/06/23. email: 2209011667@qq.com
 */
public class BoBean {

    private String title;
    private final String dataSrc;
    private String href;

    public BoBean(String title, String data_src, String href) {
        this.title = title;
        this.dataSrc = data_src;
        this.href = href;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDataSrc() {
        return dataSrc;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
