package top.wzmyyj.wzm_sdk.panel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2018/07/04. email: 2209011667@qq.com
 * 多个Panel的管理类。
 */

public class PanelManager {
    private List<Panel> mPanelList;

    public PanelManager() {
        mPanelList = new ArrayList<>();
    }

    public void addPanels(@NonNull Panel... panels) {
        for (int i = 0; i < panels.length; i++)
            if (panels[i] != null)
                this.mPanelList.add(panels[i]);
    }

    public void movePanel(@NonNull Panel panel) {
        mPanelList.remove(panel);
    }

    public void movePanel(int i) {
        mPanelList.remove(i);
    }

    public List<Panel> getPanelList() {
        return mPanelList;
    }

    public Panel get(int i) {
        return mPanelList.get(i);
    }

    public void clear() {
        mPanelList.clear();
    }

    public void setPanelList(List<Panel> mPanelList) {
        this.mPanelList = mPanelList;
    }

    public View getPanelView(int i) {
        return this.mPanelList.get(i).getView();
    }

    public int getSize() {
        return mPanelList.size();
    }

    public void onCreate(Bundle savedInstanceState) {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onCreate(savedInstanceState);
        }
    }

    public void onResume() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onResume();
        }
    }


    public void onStart() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onStart();
        }
    }

    public void onRestart() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onRestart();
        }
    }

    public void onPause() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onPause();
        }
    }

    public void onStop() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onStop();
        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onActivityCreated(savedInstanceState);
        }
    }

    public void onDestroyView() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onDestroyView();
        }
    }

    public void onDestroy() {
        if (mPanelList == null || mPanelList.size() == 0)
            return;
        for (Panel p : mPanelList) {
            p.onDestroy();
        }
        mPanelList.clear();
    }

}
