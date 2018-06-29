package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.wzmyyj.wzm_sdk.panel.Panel;

/**
 * Created by wzm on 2018/05/04. email: 2209011667@qq.com
 */


public abstract class PanelFragment extends InitFragment {

    protected Panel mPanel;

    @NonNull
    protected abstract Panel getPanel();

    private void checkPanelIsNull() {
        if (mPanel == null) {
            throw new IllegalStateException("mPanel is null");
        }
    }

    @Override
    protected void initSome(Bundle savedInstanceState) {
        super.initSome(savedInstanceState);
        mPanel = getPanel();
        checkPanelIsNull();
        mPanel.onCreate(savedInstanceState);
    }

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        return mPanel.getView();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPanel.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mPanel.onStart();
    }

    @Override
    public void onPause() {
        super.onPause();
        mPanel.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        mPanel.onStart();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPanel.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mPanel.onDestroyView();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mPanel.onDestroy();
        mPanel = null;
    }


}
