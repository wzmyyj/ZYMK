package top.wzmyyj.wzm_sdk.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.panel.Panel;

/**
 * Created by wzm on 2018/05/04. email: 2209011667@qq.com
 */


public abstract class PanelActivity extends InitActivity {

    protected List<Panel> mPanelList = new ArrayList<>();

    public void addPanels(@NonNull Panel... panels) {
        for (int i = 0; i < panels.length; i++)
            if (panels[i] != null)
                this.mPanelList.add(panels[i]);
    }

    protected void initPanelList() {

    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        initPanelList();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onCreate(savedInstanceState);
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onResume();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onStart();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onPause();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onStop();
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onRestart();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onDestroy();
        }
        mPanelList.clear();
    }
}
