package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.app.data.Urls;
import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.contract.MineContract;
import top.wzmyyj.zymk.base.presenter.BasePresenter;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */
public class MinePresenter extends BasePresenter<MineContract.IView> implements MineContract.IPresenter {

    public MinePresenter(Activity activity, MineContract.IView iv) {
        super(activity, iv);
    }

    @Override
    public void goAboutMe() {
        IntentHelper.toBrowser(mActivity, Urls.YYJ_About);
    }

    @Override
    public void goGitHubWeb() {
        IntentHelper.toBrowser(mActivity, Urls.YYJ_GitHub);
    }

    @Override
    public void goHomeWeb() {
        IntentHelper.toBrowser(mActivity, Urls.ZYMK_Base);
    }

    @Override
    public void goHotWeb() {
        IntentHelper.toBrowser(mActivity, Urls.ZYMK_Activity);
    }

    @Override
    public void goTMallWeb() {
        IntentHelper.toBrowser(mActivity, Urls.ZYMK_TMall);
    }

    @Override
    public void goSetting() {
        IntentHelper.toSettingActivity(mActivity);
    }
}
