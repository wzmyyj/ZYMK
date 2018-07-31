package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/07/30. email: 2209011667@qq.com
 */

public interface ISearchView extends IBaseView {
    void hotSearch(List list);

    void historySearch(List list);

    void smartSearch(List list);
}
