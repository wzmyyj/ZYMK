package top.wzmyyj.wzm_sdk.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import androidx.annotation.NonNull;

/**
 * Created by yyj on 2018/07/16. email: 2209011667@qq.com
 * 常用单位转换的辅助类，涉及到单位转换的方法都在这里.
 */
@SuppressWarnings("unused")
public class DensityUtil {
    /**
     * Conversion from DP to PX (pixels) according to the resolution of the phone.
     *
     * @param context context
     * @param dpValue dp value
     * @return px value
     */
    public static int dp2px(@NonNull Context context, float dpValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpValue, context.getResources().getDisplayMetrics());
    }

    /**
     * Conversion from PX (pixels) to DP according to the resolution of the phone.
     *
     * @param context context
     * @param pxValue px value
     * @return dp value
     */
    public static float px2dp(@NonNull Context context, int pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxValue / scale);
    }

    /**
     * Conversion from SP to PX (pixels) according to the resolution of the phone.
     *
     * @param context context
     * @param spValue sp value
     * @return px value
     */
    public static int sp2px(@NonNull Context context, float spValue) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spValue, context.getResources().getDisplayMetrics());
    }

    /**
     * Conversion from PX (pixels) to SP according to the resolution of the phone.
     *
     * @param context context
     * @param pxValue px value
     * @return dp value
     */
    public static float px2sp(@NonNull Context context, int pxValue) {
        return (pxValue / context.getResources().getDisplayMetrics().scaledDensity);
    }

    /**
     * Conversion from PT to PX (pixels) according to the resolution of the phone.
     *
     * @param context context
     * @param ptValue pt value
     * @return px value
     */
    public static int pt2px(@NonNull Context context, float ptValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (ptValue * metrics.xdpi / 72f + 0.5f);
    }

    /**
     * Conversion from PX (pixels) to PT according to the resolution of the phone.
     *
     * @param context context
     * @param pxValue px value
     * @return pt value
     */
    public static float px2pt(@NonNull Context context, int pxValue) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        return (int) (pxValue * 72f / metrics.xdpi + 0.5f);
    }
}
