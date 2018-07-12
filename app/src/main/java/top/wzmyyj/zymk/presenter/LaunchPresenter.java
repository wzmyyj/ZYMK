package top.wzmyyj.zymk.presenter;

import android.app.Activity;
import android.os.Handler;

import top.wzmyyj.zymk.app.tools.I;
import top.wzmyyj.zymk.presenter.base.BasePresenter;
import top.wzmyyj.zymk.view.iv.ILaunchView;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class LaunchPresenter extends BasePresenter<ILaunchView> {

    private Handler mHandler = new Handler();

    public LaunchPresenter(Activity activity, ILaunchView iv) {
        super(activity, iv);
    }

    public void CheckPermission() {

    }

    public void init() {

    }

    public void go() {
        mHandler.postDelayed(new Runnable() {

            @Override
            public void run() {
                I.toMainActivity(mActivity);
                mActivity.finish();
                mActivity.overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, 2000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(0);
    }
}
