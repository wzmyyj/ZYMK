package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class FansBean {
    private int id;
    private String avatar;
    private String name;
    private String num;

    public FansBean(int id, String avatar, String name, String num) {
        this.id = id;
        this.avatar = avatar;
        this.name = name;
        this.num = num;
    }

    public FansBean(String avatar, String name, String num) {
        this.avatar = avatar;
        this.name = name;
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
