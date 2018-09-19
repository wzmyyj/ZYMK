package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface ResultContract {

    interface IView extends IBaseView {
        void setTitle(String s);

        void showData(boolean isFirst, List<BookBean> books, String base, String next);

        void showLoadFail(String msg);
    }

    interface IPresenter extends IBasePresenter {
        void loadData();

        void loadData(String url);

        void loadData(String url, final boolean isFirst);

        void goDetails(String href);

    }


}
