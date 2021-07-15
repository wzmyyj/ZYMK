package top.wzmyyj.zymk.model.net.box;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.ItemBean;

/**
 * Created by yyj on 2018/07/10. email: 2209011667@qq.com
 */
public class HomeBox {

    private final List<BoBean> boList;
    private final List<ItemBean> itemList;

    public HomeBox(List<BoBean> boList, List<ItemBean> itemList) {
        this.boList = boList;
        this.itemList = itemList;
    }

    public List<BoBean> getBoList() {
        return boList;
    }

    public List<ItemBean> getItemList() {
        return itemList;
    }
}
