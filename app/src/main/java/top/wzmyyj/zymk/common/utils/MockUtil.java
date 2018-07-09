package top.wzmyyj.zymk.common.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

/**
 * Created by yyj on 2018/05/01. email: 2209011667@qq.com
 */

public class MockUtil {

    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE))
                .getDefaultDisplay().getHeight();
    }


    /***
     * 获取屏幕宽度
     * @return 屏幕宽度（px）
     */
    public int getMobileWidth(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        return width;
    }

    /**
     * 获取屏幕高度
     * @return 屏幕高度(px)
     */
    public int getMobileHeight(Activity context) {
        DisplayMetrics dm = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;
        return height;
    }


    /**
     * 获取状态栏高度
     * @return 高度（px）
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resultId = context.getResources().getIdentifier("status_bar_height",
                "dimen", "android");
        if (resultId > 0) {
            result = context.getResources().getDimensionPixelSize(resultId);
        }
        return result;
    }
}

