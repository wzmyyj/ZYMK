package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class HuaBean {

    private long id;
    private String title;
    private long uptime;
    private boolean isLock;
    private boolean isDot;

    public HuaBean(long id,String title,long uptime, boolean isLock, boolean isDot) {
        this.id=id;
        this.title = title;
        this.uptime = uptime;
        this.isLock = isLock;
        this.isDot = isDot;
    }

    public long getId() {
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
