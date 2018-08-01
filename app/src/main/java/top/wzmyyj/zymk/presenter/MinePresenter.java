package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.net.Urls;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_4View;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class MinePresenter extends BasePresenter<IF_4View> {

    public MinePresenter(Activity activity, IF_4View iv) {
        super(activity, iv);
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
}
