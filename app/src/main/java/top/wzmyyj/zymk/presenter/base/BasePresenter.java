package top.wzmyyj.zymk.presenter.base;

import android.app.Activity;

import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.contract.base.IBasePresenter;
import top.wzmyyj.zymk.contract.base.IBaseView;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public class BasePresenter<V extends IBaseView> implements IBasePresenter {
    protected V mView;
    protected Activity mActivity;

    public BasePresenter(Activity activity, V iv) {
        this.mActivity = activity;
        this.mView = iv;
    }

    @Override
    public void onCreate() {
    }

    @Override
    public void onResume() {
    }

    @Override
    public void onPause() {

    }

    @Override
    public void onDestroy() {
        this.mActivity = null;
        this.mView = null;
    }

    @Override
    public void finish() {
        mActivity.finish();
    }

    @Override
    public void log(String s) {
        L.d(s);
    }
}
