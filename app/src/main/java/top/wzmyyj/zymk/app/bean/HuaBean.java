package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class HuaBean {

    private int id;
    private String title;
    private String href;
    private long uptime;
    private boolean isLock;
    private boolean isDot;

    public HuaBean(int id,String title, String href, long uptime, boolean isLock, boolean isDot) {
        this.id=id;
        this.title = title;
        this.href = href;
        this.uptime = uptime;
        this.isLock = isLock;
        this.isDot = isDot;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public long getUptime() {
        return uptime;
    }

    public void setUptime(long uptime) {
        this.uptime = uptime;
    }

    public boolean isLock() {
        return isLock;
    }

    public void setLock(boolean lock) {
        isLock = lock;
    }

    public boolean isDot() {
        return isDot;
    }

    public void setDot(boolean dot) {
        isDot = dot;
    }
}
