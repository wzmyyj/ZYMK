package top.wzmyyj.zymk.base.activity;

import android.os.Bundle;

import top.wzmyyj.wzm_sdk.activity.ViewPagerFragmentActivity;
import top.wzmyyj.wzm_sdk.utils.StatusBarUtil;

/**
 * Created by yyj on 2018/06/24. email: 2209011667@qq.com
 */

public abstract class BaseMainActivity extends ViewPagerFragmentActivity {
    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        StatusBarUtil.initStatusBar(activity,true,true,true);
    }
}
