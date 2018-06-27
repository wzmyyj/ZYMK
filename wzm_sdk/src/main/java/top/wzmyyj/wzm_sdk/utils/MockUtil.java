package top.wzmyyj.wzm_sdk.utils;

import android.content.Context;
import android.view.WindowManager;

/**
 * Created by wzm on 2018/5/1 0001.
 */

public class MockUtil {
    public static int getScreenWidth(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
    }

    public static int getScreenHeight(Context context) {
        return ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getHeight();
    }
}

