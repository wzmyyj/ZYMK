package top.wzmyyj.zymk.common.utils;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by yyj on 2018/08/21. email: 2209011667@qq.com
 * 获取版本号工具类。
 */

public class PackageUtil {

    public static String getVersionName(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo infro = manager.getPackageInfo(context.getPackageName(), 0);
            return infro.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static int getVersionCode(Context context) {
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo infro = manager.getPackageInfo(context.getPackageName(), 0);
            return infro.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }
}