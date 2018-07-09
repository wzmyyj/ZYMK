package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class TypeBean {
    private String type;
    private String name;
    private String url;

    public TypeBean() {
    }

    public TypeBean(String type, String name, String url) {
        this.type = type;
        this.name = name;
        this.url = url;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
