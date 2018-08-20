package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.wzm_sdk.panel.Panels;

/**
 * Created by wzm on 2018/05/04. email: 2209011667@qq.com
 */


public abstract class PanelActivity extends InitActivity {

    protected Panels mPanels = new Panels();

    public void addPanels(@NonNull Panel... panels) {
        mPanels.addPanels(panels);
    }

    public View getPanelView(int i) {
        return mPanels.getPanelView(i);
    }
    public Panel getPanel(int i) {
        return mPanels.get(i);
    }

    protected void initPanels() {

    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        initPanels();
        mPanels.onCreate(savedInstanceState);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mPanels.onResume();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mPanels.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPanels.onPause();
    }


    @Override
    protected void onStop() {
        super.onStop();
        mPanels.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        mPanels.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPanels.onDestroy();
        mPanels = null;
    }
}
