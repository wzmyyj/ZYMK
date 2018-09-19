package top.wzmyyj.zymk.contract;

import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/09/10. email: 2209011667@qq.com
 */

public interface SettingContract {

    interface IView extends IBaseView {
        void setCache(String s);

        void setVersion(String s);

        void setCue(boolean is);
    }

    interface IPresenter extends IBasePresenter {
        void goAboutMe();

        void goGitHubWeb();

        void goFeedback();

        void changeCue();

        void getCue();

        void getVersion();


        void loadNewApp();

        void getCacheSize();

        void clearCache();
    }


}
