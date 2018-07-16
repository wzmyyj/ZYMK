package top.wzmyyj.zymk.app.bean;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class HuaBean {

    private String title;
    private String href;
    private long uptime;
    private boolean isLock;

    public HuaBean(String title, String href, long uptime, boolean isLock) {
        this.title = title;
        this.href = href;
        this.uptime = uptime;
        this.isLock = isLock;
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
}
