package top.wzmyyj.zymk.view.activity;

import android.content.Intent;
import android.os.Handler;

import top.wzmyyj.zymk.presenter.LaunchPresenter;


/**
 * Created by wzm on 2018/06/24. email: 2209011667@qq.com
 */

public class LaunchActivity extends BaseActivity<LaunchPresenter> {

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void initData() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                Intent i = new Intent();
                i.setClass(LaunchActivity.this, MainActivity.class);
                startActivity(i);
                finish();
                overridePendingTransition(android.R.anim.fade_in,
                        android.R.anim.fade_out);
            }
        }, 1000);
    }

    @Override
    protected void initListener() {

    }
}
