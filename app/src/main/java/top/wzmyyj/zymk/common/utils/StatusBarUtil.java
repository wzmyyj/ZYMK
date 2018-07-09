package top.wzmyyj.zymk.common.utils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by yyj on 2018/06/29. email: 2209011667@qq.com
 */

public class StatusBarUtil {


    /**
     * 状态栏设置
     */
    public static void initStatusBar(Activity context, boolean isTint) {
        initStatusBar(context, isTint, false, false);
    }

    public static void initStatusBar(Activity context, boolean isTint, boolean isDark) {
        initStatusBar(context, isTint, isDark, false);
    }

    public static void initStatusBar(Activity context, boolean isTint, boolean isDark, boolean isTransparent) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        Window window = context.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        int flag = window.getDecorView().getWindowSystemUiVisibility();
        // 沉浸式
        if (isTint) {
            flag |= View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;

        } else {
            flag |= View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        }

        // 文字
        if (isDark) {
            flag |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        } else {
            flag |= View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR;
        }
        window.getDecorView().setSystemUiVisibility(flag);
        // 背景颜色
        if (isTransparent) {
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }

    }

}
