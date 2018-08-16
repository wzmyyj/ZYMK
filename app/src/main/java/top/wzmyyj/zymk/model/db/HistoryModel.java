package top.wzmyyj.zymk.model.db;

import android.content.Context;

import top.wzmyyj.zymk.model.db.utils.DaoManager;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public class HistoryModel {
    private DaoManager manager;

    public HistoryModel(Context context) {
        manager = DaoManager.getInstance(context);
    }

}
