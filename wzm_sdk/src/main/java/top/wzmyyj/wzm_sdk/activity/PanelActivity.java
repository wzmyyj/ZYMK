package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import top.wzmyyj.wzm_sdk.panel.Panel;

/**
 * Created by wzm on 2018/05/04. email: 2209011667@qq.com
 */


public abstract class PanelActivity extends InitActivity {

    protected Panel mPanel;

    @NonNull
    protected abstract Panel getPanel();

    private void checkPanelIsNull() {
        if (mPanel == null) {
            throw new IllegalStateException("mPanel is null");
        }
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        mPanel = getPanel();
        checkPanelIsNull();
        mPanel.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        setContentView(mPanel.getView());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPanel.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPanel.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPanel.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPanel.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPanel.onRestart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanel.onDestroy();
        mPanel = null;
    }
}
