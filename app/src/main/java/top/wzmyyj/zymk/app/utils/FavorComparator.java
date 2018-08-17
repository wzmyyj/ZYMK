package top.wzmyyj.zymk.app.utils;

import java.util.Comparator;

import top.wzmyyj.zymk.app.bean.FavorBean;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class FavorComparator implements Comparator {


    @Override
    public int compare(Object lhs, Object rhs) {
        FavorBean a = (FavorBean) lhs;
        FavorBean b = (FavorBean) rhs;

        if (a.getBook().getUpdate_time() < b.getBook().getUpdate_time()) {
            return 1;
        } else {
            return -1;
        }
    }
}