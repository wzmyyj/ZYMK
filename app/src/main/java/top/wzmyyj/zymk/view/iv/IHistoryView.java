package top.wzmyyj.zymk.view.iv;

import java.util.List;

import top.wzmyyj.zymk.app.bean.HistoryBean;
import top.wzmyyj.zymk.view.iv.base.IBaseView;

/**
 * Created by yyj on 2018/08/17. email: 2209011667@qq.com
 */

public interface IHistoryView extends IBaseView {
    void loadHistory(List<HistoryBean> list);

    void deleteHistory(boolean is);
}
