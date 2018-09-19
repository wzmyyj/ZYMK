package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface SearchContract {

    interface IView extends IBaseView {
        void showHot(List list);

        void showSmart(String key, List list);

        void showHistory(List list);

        void addHistory(SearchHistoryBean bean);

        void removeHistory(long l);

        void removeAllHistory();
    }

    interface IPresenter extends IBasePresenter {
        void search(String s);

        void getHotTags();

        void smart(String key);

        void getHistory();

        void delHistory(long id);

        void delAllHistory();

        void goDetails(String href, String title);

    }


}
