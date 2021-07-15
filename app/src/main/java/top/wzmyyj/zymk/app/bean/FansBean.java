package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */
public class FansBean {

    private final String avatar;
    private final String name;
    private final String num;

    public FansBean(String avatar, String name, String num) {
        this.avatar = avatar;
        this.name = name;
        this.num = num;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getNum() {
        return num;
    }
}
