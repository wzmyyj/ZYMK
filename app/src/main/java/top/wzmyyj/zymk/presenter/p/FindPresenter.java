package top.wzmyyj.zymk.presenter.p;

import android.app.Activity;

import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.model.net.Urls;
import top.wzmyyj.zymk.presenter.p.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.IF_3View;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class FindPresenter extends BasePresenter<IF_3View> {

    public FindPresenter(Activity activity, IF_3View iv) {
        super(activity, iv);
    }

    public void goHomeWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_HomePage);
    }

    public void goActivityWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Activity);
    }

    public void goTmallWeb() {
        I.toBrowser(mActivity, Urls.ZYMK_Tmall);
    }
}
