package top.wzmyyj.wzm_sdk.panel;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wzm on 2018/4/23 0023.
 */

public class Panel {

    protected LayoutInflater mInflater;
    protected Context context;
    protected Activity activity;
    protected Fragment fragment;
    protected Panel rootPanel;
    protected View view;
    protected String title = "";

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public Panel getRootPanel() {
        return rootPanel;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Panel(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    public Panel(Activity activity) {
        this((Context) activity);
        this.activity = activity;
    }

    public Panel(Fragment fragment) {
        this(fragment.getActivity());
        this.fragment = fragment;
    }

    public Panel(Panel panel) {
        this(panel.getActivity());
        this.rootPanel = panel;
        if (panel.getFragment() != null) {
            this.fragment = panel.getFragment();
        }
    }

    public View getView() {
        return view;
    }



    // child panels
    protected List<Panel> mPanelList = new ArrayList<>();

    public void addPanel(Panel panel) {
        if (panel != null)
            this.mPanelList.add(panel);
    }

    protected void initPanelList() {
        mPanelList.clear();
    }



    public List<Panel> getPanelList() {
        return this.mPanelList;
    }

    public void onCreate(Bundle savedInstanceState) {
        initPanelList();
        for (Panel p : mPanelList) {
            p.onCreate(savedInstanceState);
        }
    }

    public void onResume() {
        for (Panel p : mPanelList) {
            p.onResume();
        }
    }


    public void onStart() {
        for (Panel p : mPanelList) {
            p.onStart();
        }
    }

    public void onRestart() {
        for (Panel p : mPanelList) {
            p.onRestart();
        }
    }

    public void onPause() {
        for (Panel p : mPanelList) {
            p.onPause();
        }
    }

    public void onStop() {
        for (Panel p : mPanelList) {
            p.onStop();
        }
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        for (Panel p : mPanelList) {
            p.onActivityCreated(savedInstanceState);
        }
    }


    public void onDestroy() {
        for (Panel p : mPanelList) {
            p.onDestroy();
        }
        mPanelList.clear();
    }

    public void onDestroyView() {
        for (Panel p : mPanelList) {
            p.onDestroyView();
        }
        context = null;
        activity = null;
        fragment = null;
        rootPanel = null;
    }


}
