package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.wzm_sdk.panel.Panel;

/**
 * Created by wzm on 2018/05/04. email: 2209011667@qq.com
 */


public abstract class PanelFragment extends InitFragment {

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
    public void onResume() {
        super.onResume();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onResume();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onStart();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onStop();
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onActivityCreated(savedInstanceState);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onDestroyView();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        for (Panel p : mPanelList) {
            p.onDestroy();
        }
        mPanelList.clear();
    }


}
