package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface MoreContract {

    interface IView extends IBaseView {
        void setTitle(String s);

        void showData(String content, String figure, List<BookBean> books);
    }

    interface IPresenter extends IBasePresenter {

        void addEmptyData(List<BookBean> data);

        void loadData();

        void goDetails(String href);
    }


}
