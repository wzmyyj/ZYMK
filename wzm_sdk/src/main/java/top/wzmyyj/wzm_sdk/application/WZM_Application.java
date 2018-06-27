package top.wzmyyj.wzm_sdk.application;

import android.app.Application;

import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/4/8 0008.
 */

public class WZM_Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);
    }
}
