package top.wzmyyj.wzm_sdk.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2019/08/19.
 * <p>
 * Adapt Screen. Use pt as the unit of dimension.
 *
 * @author feling
 * @version 1.0.0
 * @since 1.0.0
 */
@SuppressWarnings({"unused", "UnusedReturnValue"})
public final class AdaptScreenUtil {

    private static final String TAG = AdaptScreenUtil.class.getSimpleName();

    /**
     * No instances.
     */
    private AdaptScreenUtil() {
        throw new UnsupportedOperationException(TAG + ": You can't instantiate me.");
    }

    /**
     * For DEBUG.
     */
    public static boolean DEBUG = false;

    /**
     * Resources metrics fields.
     */
    private static List<Field> metricsFields;

    /**
     * Fit the screen width according to the design draft.
     *
     * @param resources   resources
     * @param designWidth Screen width of the design draft, in Pt
     * @return After adaptation resources
     */
    @MainThread
    public static Resources adaptWidth(@NonNull final Resources resources, final int designWidth) {
        float newXdpi = (resources.getDisplayMetrics().widthPixels * 72f) / designWidth;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    /**
     * Close he adaptation and return to normal.
     *
     * @param resources resources
     * @return After adaptation resources
     */
    @MainThread
    public static Resources closeAdapt(@NonNull final Resources resources) {
        float newXdpi = Resources.getSystem().getDisplayMetrics().density * 72f;
        applyDisplayMetrics(resources, newXdpi);
        return resources;
    }

    //--------------private method----------------//

    /**
     * Apply Display Metrics.
     *
     * @param resources resources
     * @param newXdpi   xdpi
     */
    private static void applyDisplayMetrics(@NonNull final Resources resources, final float newXdpi) {
        resources.getDisplayMetrics().xdpi = newXdpi;
        applyOtherDisplayMetrics(resources, newXdpi);
    }

    /**
     * Apply other Display Metrics.
     *
     * @param resources resources
     * @param newXdpi   xdpi
     */
    private static void applyOtherDisplayMetrics(@NonNull final Resources resources, final float newXdpi) {
        if (metricsFields == null) {
            metricsFields = new ArrayList<>();
            Class<?> resCls = resources.getClass();
            Field[] declaredFields = resCls.getDeclaredFields();
            while (declaredFields.length > 0) {
                for (Field field : declaredFields) {
                    if (field.getType().isAssignableFrom(DisplayMetrics.class)) {
                        field.setAccessible(true);
                        DisplayMetrics tmpDm = getMetricsFromField(resources, field);
                        if (tmpDm != null) {
                            metricsFields.add(field);
                            tmpDm.xdpi = newXdpi;
                        }
                    }
                }
                resCls = resCls.getSuperclass();
                if (resCls != null) {
                    declaredFields = resCls.getDeclaredFields();
                } else {
                    break;
                }
            }
        } else {
            applyMetricsFields(resources, newXdpi);
        }
    }

    /**
     * Apply Metrics Fields.
     *
     * @param resources resources
     * @param newXdpi   xdpi
     */
    private static void applyMetricsFields(@NonNull final Resources resources, final float newXdpi) {
        for (Field metricsField : metricsFields) {
            try {
                DisplayMetrics dm = (DisplayMetrics) metricsField.get(resources);
                if (dm != null) {
                    dm.xdpi = newXdpi;
                }
            } catch (Exception e) {
                if (DEBUG) {
                    Log.d(TAG, "Method applyMetricsFields has exception!");
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get Metrics from Field.
     *
     * @param resources resources
     * @param field     field
     * @return DisplayMetrics
     */
    private static DisplayMetrics getMetricsFromField(@NonNull final Resources resources, final Field field) {
        try {
            return (DisplayMetrics) field.get(resources);
        } catch (Exception e) {
            if (DEBUG) {
                Log.d(TAG, "Method getMetricsFromField has exception!");
                e.printStackTrace();
            }
            return null;
        }
    }

}

