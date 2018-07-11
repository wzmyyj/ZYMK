package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/06/23. email: 2209011667@qq.com
 */

public class BoBean {
    private String title;
    private String data_src;
    private String href;

    public BoBean() {
    }

    public BoBean(String title, String data_src, String href) {
        this.title = title;
        this.data_src = data_src;
        this.href = href;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData_src() {
        return data_src;
    }

    public void setData_src(String data_src) {
        this.data_src = data_src;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }
}
