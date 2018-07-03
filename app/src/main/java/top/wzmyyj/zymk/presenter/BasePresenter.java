package top.wzmyyj.zymk.presenter;

import android.app.Activity;

import top.wzmyyj.zymk.view.IBaseView;

/**
 * Created by wzm on 2018/06/28. email: 2209011667@qq.com
 */

public class BasePresenter<IV extends IBaseView> {
    protected IV mIV;
    protected Activity mActivity;

    public BasePresenter(Activity activity, IV iv) {
        this.mActivity = activity;
        this.mIV = iv;
    }

    public void onCreate() {
    }

    public void onResume() {
    }

    public void onPause() {

    }

    public void onDestroy() {
        this.mActivity = null;
        this.mIV = null;
    }

}
