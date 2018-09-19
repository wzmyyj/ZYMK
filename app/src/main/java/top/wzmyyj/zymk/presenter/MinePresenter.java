package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.contract.MineContract;
import top.wzmyyj.zymk.presenter.base.BasePresenter;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class MinePresenter extends BasePresenter<MineContract.IView> implements MineContract.IPresenter {

    public MinePresenter(Activity activity, MineContract.IView iv) {
        super(activity, iv);
    }

    @Override
    public void goAboutMe() {
        I.toBrowser(mActivity, Urls.YYJ_About);
    }

    @Override
    public void goGitHubWeb() {
        I.toBrowser(mActivity, Urls.YYJ_GitHub);
    }

    @Override
    public void goHomeWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Base);
    }

    @Override
    public void goHotWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Activity);
    }

    @Override
    public void goTmallWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Tmall);
    }

    @Override
    public void goSetting() {
        I.toSettingActivity(mActivity);
    }
}
