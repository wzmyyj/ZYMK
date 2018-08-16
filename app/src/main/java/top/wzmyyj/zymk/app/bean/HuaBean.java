package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class HuaBean {

    private int index;
    private long id;
    private String name;
    private long uptime;
    private boolean isLock;
    private boolean isDot;

    public HuaBean(long id,String name,long uptime, boolean isLock, boolean isDot) {
        this.id=id;
        this.name = name;
        this.uptime = uptime;
        this.isLock = isLock;
        this.isDot = isDot;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }


    public String getName() {
        return name;
    }

    public void setName(String title) {
        this.name = title;
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
