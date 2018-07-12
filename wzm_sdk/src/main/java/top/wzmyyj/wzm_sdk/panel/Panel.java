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

    public void onCreate(Bundle savedInstanceState) {
        initPanels();
        mPanels.onCreate(savedInstanceState);
    }

    public void onResume() {
        mPanels.onResume();
    }


    public void onStart() {
        mPanels.onStart();
    }

    public void onRestart() {
        mPanels.onRestart();
    }

    public void onPause() {
        mPanels.onPause();
    }

    public void onStop() {
        mPanels.onStop();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mPanels.onActivityCreated(savedInstanceState);
    }

    public void onDestroyView() {
        mPanels.onDestroyView();
    }

    public void onDestroy() {
        mPanels.onDestroy();
        mPanels = null;
        context = null;
        activity = null;
    }


    public Object f(int w, Object... objects) {
        return null;
    }

    protected List<View> viewList = new ArrayList<>();

    public void bingViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            viewList.add(views[i]);
        }
    }

}
