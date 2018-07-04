package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import top.wzmyyj.wzm_sdk.panel.Panel;
import top.wzmyyj.wzm_sdk.panel.Panels;

/**
 * Created by wzm on 2018/05/04. email: 2209011667@qq.com
 */


public abstract class PanelFragment extends InitFragment {

    protected Panels mPanels = new Panels();

    public void addPanels(@NonNull Panel... panels) {
        mPanels.addPanels(panels);
    }

    public View getPanelView(int i) {
        return mPanels.getPanelView(i);
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
    public void onResume() {
        super.onResume();
        mPanels.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPanels.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPanels.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPanels.onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPanels.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPanels.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanels.onDestroy();
        mPanels = null;
    }


}
