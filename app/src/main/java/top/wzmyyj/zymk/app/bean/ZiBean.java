package top.wzmyyj.zymk.app.bean;

import java.util.List;

/**
 * Created by yyj on 2018/07/15. email: 2209011667@qq.com
 */

public class ZiBean {
    private List<FansBean> fansList;
    private String[] support = new String[6];

    public ZiBean(List<FansBean> fansList, String[] support) {
        this.fansList = fansList;
        this.support = support;
    }

    public List<FansBean> getFansList() {
        return fansList;
    }

    public void setFansList(List<FansBean> fansList) {
        this.fansList = fansList;
    }

    public String[] getSupport() {
        return support;
    }

    public void setSupport(String[] support) {
        this.support = support;
    }
}
