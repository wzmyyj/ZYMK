package top.wzmyyj.wzm_sdk.panel;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2018/07/04. email: 2209011667@qq.com
 * 多个Panel的管理类。
 */
public class PanelManager {

    private final List<Panel> mPanelList;

    public PanelManager() {
        mPanelList = new ArrayList<>();
    }

    public void addPanels(@NonNull Panel... panels) {
        for (Panel panel : panels) if (panel != null) this.mPanelList.add(panel);
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

    public View getPanelView(int i) {
        return this.mPanelList.get(i).getView();
    }

    public int getSize() {
        return mPanelList.size();
    }

    public void onCreate(Bundle savedInstanceState) {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onCreate(savedInstanceState);
    }

    public void onResume() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onResume();
    }

    public void onStart() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onStart();
    }

    public void onRestart() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onRestart();
    }

    public void onPause() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onPause();
    }

    public void onStop() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onStop();
    }

    public void onDestroyView() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onDestroyView();
    }

    public void onDestroy() {
        if (mPanelList.size() == 0) return;
        for (Panel p : mPanelList) p.onDestroy();
        mPanelList.clear();
    }
}
