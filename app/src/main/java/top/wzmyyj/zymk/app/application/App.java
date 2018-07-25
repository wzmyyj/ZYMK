package top.wzmyyj.zymk.app.application;

import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;

import top.wzmyyj.zymk.common.utils.MockUtil;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public class App extends BaseApplication {

    public static int StatusBarHeight;

    @Override
    public void onCreate() {
        super.onCreate();
        StatusBarHeight = MockUtil.getStatusBarHeight(this);
    }


    public static void fitsStatusBarView(@NonNull View... views) {
        for (int i = 0; i < views.length; i++) {
            ViewGroup.LayoutParams params = views[i].getLayoutParams();
            params.height = StatusBarHeight;
        }
    }
}
