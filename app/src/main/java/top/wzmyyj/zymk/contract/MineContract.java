package top.wzmyyj.zymk.contract;

import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface MineContract {

    interface IView extends IBaseView {

    }

    interface IPresenter extends IBasePresenter {
        void goAboutMe();

        void goGitHubWeb();

        void goHomeWeb();

        void goHotWeb();

        void goTmallWeb();

        void goSetting();
    }


}
