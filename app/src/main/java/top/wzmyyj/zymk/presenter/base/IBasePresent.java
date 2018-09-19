package top.wzmyyj.zymk.presenter.base;

/**
 * Created by yyj on 2018/07/09. email: 2209011667@qq.com
 */

public interface IBasePresent{
    void onCreate();

    void onResume();

    void onPause();

    void onDestroy();

    void finish();

    void log(String s);


}
