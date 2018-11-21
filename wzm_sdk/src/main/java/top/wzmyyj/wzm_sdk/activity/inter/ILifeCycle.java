package top.wzmyyj.wzm_sdk.activity.inter;

/**
 * Created by yyj on 2018/11/20. email: 2209011667@qq.com
 */

public interface ILifeCycle {
    void onCreate();

    void onReStart();

    void onStart();

    void onResume();

    void onPause();

    void onStop();

    void onDestroy();
}
