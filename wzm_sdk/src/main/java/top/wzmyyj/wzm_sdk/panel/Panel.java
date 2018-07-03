package top.wzmyyj.wzm_sdk.panel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2018/04/23. email: 2209011667@qq.com
 */


public class Panel {

    protected LayoutInflater mInflater;
    protected Context context;
    protected Activity activity;
    protected View view;
    protected String title = "";

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Panel(Context context) {
        this.activity = (Activity) context;
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }


    public View getView() {
        return view;
    }


    // child panels
    protected List<Panel> mPanelList = new ArrayList<>();

    public void addPanels(@NonNull Panel... panels) {
        for (int i = 0; i < panels.length; i++)
            if (panels[i] != null)
                this.mPanelList.add(panels[i]);
    }

    protected void initPanelList() {

    }

    public void onCreate(Bundle savedInstanceState) {
        initPanelList();
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
        context = null;
        activity = null;
    }


}
