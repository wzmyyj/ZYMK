package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.app.bean.SearchHistoryBean;
import top.wzmyyj.zymk.base.contract.IBasePresenter;
import top.wzmyyj.zymk.base.contract.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */
public interface SearchContract {

    interface IView extends IBaseView {

        void showHot(List<BookBean> list);

        void showSmart(String key, List<BookBean> list);

        void showHistory(List<SearchHistoryBean> list);

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

        void addHistory(String key);

        void goDetails(String href, String title);
    }
}
