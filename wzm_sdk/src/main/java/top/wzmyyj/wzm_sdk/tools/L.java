package top.wzmyyj.wzm_sdk.tools;

import android.util.Log;

/**
 * Created by wzm on 2018/01/10. email: 2209011667@qq.com
 */

public class L {
    private static String TAG = "WZM";
    private static boolean debug = true;


    private L() {
    }

    public static void setTAG(String TAG) {
        L.TAG = TAG;
    }

    public static void setDebug(boolean debug) {
        L.debug = debug;
    }

    public static void v(String msg) {
        if (debug)
            Log.v(TAG, msg);
    }

    public static void d(String msg) {
        if (debug)
            Log.d(TAG, msg);
    }

    public static void i(String msg) {
        if (debug)
            Log.i(TAG, msg);
    }

    public static void w(String msg) {
        if (debug)
            Log.w(TAG, msg);
    }

    public static void e(String msg) {
        if (debug)
            Log.e(TAG, msg);
    }

}
