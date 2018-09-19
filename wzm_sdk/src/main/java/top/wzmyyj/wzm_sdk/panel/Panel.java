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
 * Panel意为面板。设计初衷是希望有个类似Fragment，但比Fragment简洁，小规模的View的控制器。
 * Panel可以放在Activity,Fragment，也可以放在Panel里，形成层层套嵌模型。
 * Panel拥有Fragment类似的生命周期，子层比父层先执行，同层按顺序先执行。
 * Panel与父层通讯紧密时，可以用内部类。
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

    public Panel setTitle(String title) {
        this.title = title;
        return this;
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

    // 内部panel的管理类。
    protected PanelManager mPanelManager = new PanelManager();

    // 内部添加panel。
    public void addPanels(@NonNull Panel... panels) {
        mPanelManager.addPanels(panels);
    }

    public View getPanelView(int i) {
        return mPanelManager.getPanelView(i);
    }

    public Panel getPanel(int i) {
        return mPanelManager.get(i);
    }


    protected void initPanels() {

    }

    public void onCreate(Bundle savedInstanceState) {
        initPanels();
        mPanelManager.onCreate(savedInstanceState);
    }

    public void onResume() {
        mPanelManager.onResume();
    }


    public void onStart() {
        mPanelManager.onStart();
    }

    public void onRestart() {
        mPanelManager.onRestart();
    }

    public void onPause() {
        mPanelManager.onPause();
    }

    public void onStop() {
        mPanelManager.onStop();
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        mPanelManager.onActivityCreated(savedInstanceState);
    }

    public void onDestroyView() {
        mPanelManager.onDestroyView();
    }

    public void onDestroy() {
        mPanelManager.onDestroy();
        viewList.clear();
        mPanelManager = null;
        context = null;
        activity = null;
    }


//    // 给外部调用的统一万能函数。多个基本数据类型参数时可以选择分装成been。
//    public Object f(int w, Object... objects) {
//        return null;
//    }

    protected List<View> viewList = new ArrayList<>();

    // 外部调用，用于绑定外界的View。可以控制外界的view。
    public void bingViews(View... views) {
        for (int i = 0; i < views.length; i++) {
            viewList.add(views[i]);
        }
    }

}
