package top.wzmyyj.wzm_sdk.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import top.wzmyyj.wzm_sdk.panel.Panel;

/**
 * Created by wzm on 2018/5/4 0004.
 */

public abstract class PanelFragment extends Fragment {

    protected Panel mPanel;

    @NonNull
    protected abstract Panel getPanel();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return mPanel.getView();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPanel = getPanel();
        mPanel.onCreate(savedInstanceState);

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
