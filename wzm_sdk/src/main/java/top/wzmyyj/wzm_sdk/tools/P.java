package top.wzmyyj.wzm_sdk.tools;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;
import java.util.Set;

/**
 * Created by yyj on 2018/08/20. email: 2209011667@qq.com
 */

public class P {
    //save
    private SharedPreferences sha;
    private SharedPreferences.Editor ed;

    public P(Context context, String name) {
        sha = context.getSharedPreferences(name, Activity.MODE_PRIVATE);
        ed = sha.edit();
    }

    public P(Context context) {
        this(context, "P");
    }

    public static P create(Context context, String name) {
        return new P(context, name);
    }

    public static P create(Context context) {
        return new P(context);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return sha.getBoolean(key, defValue);
    }

    public int getInt(String key, int defValue) {
        return sha.getInt(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return sha.getLong(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return sha.getFloat(key, defValue);
    }

    public String getString(String key, String defValue) {
        return sha.getString(key, defValue);
    }

    public Map<String, ?> getAll() {
        return sha.getAll();
    }

    public Set<String> getString(String key, Set<String> defValue) {
        return sha.getStringSet(key, defValue);
    }

    public P putBoolean(String key, boolean value) {
        ed.putBoolean(key, value);
        return this;
    }

    public P putInt(String key, int value) {
        ed.putInt(key, value);
        return this;
    }

    public P putLong(String key, long value) {
        ed.putLong(key, value);
        return this;
    }

    public P putFloat(String key, float value) {
        ed.putFloat(key, value);
        return this;
    }

    public P putString(String key, String value) {
        ed.putString(key, value);
        return this;
    }

    public P putStringSet(String key, Set<String> value) {
        ed.putStringSet(key, value);
        return this;
    }

    public void commit() {
        ed.commit();
    }


}
