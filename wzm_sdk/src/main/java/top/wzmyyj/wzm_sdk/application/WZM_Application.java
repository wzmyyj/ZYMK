package top.wzmyyj.wzm_sdk.application;

import android.app.Application;

import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.wzm_sdk.tools.T;

/**
 * Created by yyj on 2018/09/19. email: 2209011667@qq.com
 */

public class WZM_Application extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        L.setTAG("WZM");
        T.init(this);
    }
}
