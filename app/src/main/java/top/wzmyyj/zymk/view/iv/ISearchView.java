package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public interface ISearchView extends IBaseView {
    void showHot(List list);

    void showSmart(String key, List list);

    void showHistory(List list);

    void addHistory(SearchHistoryBean bean);

    void delHistory(long l);

    void delAllHistory();


}
