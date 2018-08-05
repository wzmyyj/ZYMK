package top.wzmyyj.zymk.app.utils;

import java.util.Comparator;

import top.wzmyyj.zymk.app.bean.HuaBean;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class HuaComparator implements Comparator {

    private int k;

    public HuaComparator(int k) {
        this.k = k;
    }

    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    @Override
    public int compare(Object lhs, Object rhs) {
        HuaBean a = (HuaBean) lhs;
        HuaBean b = (HuaBean) rhs;

        if (a.getId() > b.getId()) {
            return k;
        } else {
            return -k;
        }
    }
}