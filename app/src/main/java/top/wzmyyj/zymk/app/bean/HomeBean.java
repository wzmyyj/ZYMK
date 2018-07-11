package top.wzmyyj.zymk.app.bean;

import java.util.List;

/**
 * Created by yyj on 2018/07/10. email: 2209011667@qq.com
 */

public class HomeBean {
    private List<BoBean> boList;
    private List<ItemBean> itemList;

    public HomeBean(List<BoBean> boList, List<ItemBean> itemList) {
        this.boList = boList;
        this.itemList = itemList;
    }

    public List<BoBean> getBoList() {
        return boList;
    }

    public void setBoList(List<BoBean> boList) {
        this.boList = boList;
    }

    public List<ItemBean> getItemList() {
        return itemList;
    }

    public void setItemList(List<ItemBean> itemList) {
        this.itemList = itemList;
    }
}
