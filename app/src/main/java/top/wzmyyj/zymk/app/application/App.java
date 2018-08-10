package top.wzmyyj.zymk.app.application;

import top.wzmyyj.wzm_sdk.tools.L;
import top.wzmyyj.zymk.common.utils.StatusBarUtil;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 * 应用的Application。
 */

public class App extends BaseApplication {


    @Override
    public void onCreate() {
        super.onCreate();
        StatusBarUtil.initStatusBarHeight(this);
        L.setTAG("ZZZZZZ");
    }
}
