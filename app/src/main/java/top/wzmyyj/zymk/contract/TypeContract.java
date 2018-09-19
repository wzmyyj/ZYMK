package top.wzmyyj.zymk.contract;

import java.util.List;

import top.wzmyyj.zymk.app.bean.TypeBean;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface TypeContract {

    interface IView extends IBaseView {
        void showData(List<TypeBean>... typeList);
    }

    interface IPresenter extends IBasePresenter {
        void loadData();

        void goResult(String href);

        void goSearch();
    }


}
