package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.BoBean;
import top.wzmyyj.zymk.app.bean.FavorBean;
import top.wzmyyj.zymk.app.bean.ItemBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface HomeContract {

    interface IView extends IBaseView {
        void showFavor(List<FavorBean> list);

        void showData(List<BoBean> boBeans, List<ItemBean> itemBeans);
    }

    interface IPresenter extends IBasePresenter {
        void loadData();

        void loadFavor();

        void loadNetFavor();

        void goMore(String href);

        void goDetails(String href);

        void goNew();

        void goRank();

        void goSearch();
    }


}
