package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import android.os.Handler;

import top.wzmyyj.zymk.app.helper.IntentHelper;
import top.wzmyyj.zymk.contract.LaunchContract;
import top.wzmyyj.zymk.base.presenter.BasePresenter;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */
public class LaunchPresenter extends BasePresenter<LaunchContract.IView> implements LaunchContract.IPresenter {

    private final Handler mHandler = new Handler();

    public LaunchPresenter(Activity activity, LaunchContract.IView iv) {
        super(activity, iv);
    }

    @Override
    public void CheckPermission() {
    }

    @Override
    public void init() {
    }

    @Override
    public void goMain() {
        mHandler.postDelayed(() -> {
            IntentHelper.toMainActivity(mActivity);
            mActivity.finish();
            mActivity.overridePendingTransition(android.R.anim.fade_in,
                    android.R.anim.fade_out);
        }, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }
}
