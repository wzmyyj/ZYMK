package top.wzmyyj.zymk.app.utils;

import java.util.Comparator;

import top.wzmyyj.zymk.app.bean.SearchHistoryBean;

/**
 * Created by Administrator on 2017/8/8 0008.
 */

public class SearchHistoryComparator implements Comparator {

    @Override
    public int compare(Object lhs, Object rhs) {
        SearchHistoryBean a = (SearchHistoryBean) lhs;
        SearchHistoryBean b = (SearchHistoryBean) rhs;

        if (a.getTime() < b.getTime()) {
            return 1;
        } else {
            return -1;
        }
    }
}