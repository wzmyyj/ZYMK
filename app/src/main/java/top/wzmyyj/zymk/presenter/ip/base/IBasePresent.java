package top.wzmyyj.zymk.presenter.ip.base;

import top.wzmyyj.wzm_sdk.inter.ip.ILogPresent;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public interface IBasePresent extends ILogPresent {
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();
}
