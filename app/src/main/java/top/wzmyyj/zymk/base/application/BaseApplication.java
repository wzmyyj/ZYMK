package top.wzmyyj.zymk.base.application;

import top.wzmyyj.wzm_sdk.application.WZM_Application;
import top.wzmyyj.wzm_sdk.utils.AdaptScreenUtil;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;
import top.wzmyyj.zymk.app.data.Config;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */
public class BaseApplication extends WZM_Application {

    @Override
    public void onCreate() {
        super.onCreate();
        StatusBarUtil.initStatusBarHeight(this);
        AdaptScreenUtil.adaptWidth(getResources(),  Config.BASE_WIDTH);
    }
}
