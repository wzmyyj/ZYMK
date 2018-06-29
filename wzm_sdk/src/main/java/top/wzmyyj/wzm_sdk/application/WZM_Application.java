package top.wzmyyj.wzm_sdk.application;

import android.app.Application;

import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by wzm on 2018/04/08. email: 2209011667@qq.com
 */


public class WZM_Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        T.init(this);
    }
}
