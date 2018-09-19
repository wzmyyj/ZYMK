package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IMineView;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class MinePresenter extends BasePresenter<IMineView> {

    public MinePresenter(Activity activity, IMineView iv) {
        super(activity, iv);
    }

    public void goAboutMe() {
        I.toBrowser(mActivity, Urls.YYJ_About);
    }

    public void goGitHubWeb() {
        I.toBrowser(mActivity, Urls.YYJ_GitHub);
    }

    public void goHomeWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Base);
    }

    public void goActivityWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Activity);
    }

    public void goTmallWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Tmall);
    }

    public void goSetting() {
        I.toSettingActivity(mActivity);
    }
}
