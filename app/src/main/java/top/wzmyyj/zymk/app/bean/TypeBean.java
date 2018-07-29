package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class TypeBean {

    private String title;
    private String href;
    private String data_src;

    public TypeBean() {
    }

    public TypeBean(String title, String href, String data_src) {
        this.title = title;
        this.href = href;
        this.data_src = data_src;
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


    public String getData_src() {
        return data_src;
    }

    public void setData_src(String data_src) {
        this.data_src = data_src;
    }
}
