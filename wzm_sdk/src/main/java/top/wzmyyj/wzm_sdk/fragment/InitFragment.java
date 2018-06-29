package top.wzmyyj.wzm_sdk.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by wzm on 2018/04/23. email: 2209011667@qq.com
 */


public abstract class InitFragment extends Fragment {

    protected Activity activity;
    protected Context context;
    protected Fragment fragment;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = this.getActivity();
        this.fragment = this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initSome(savedInstanceState);
        View view = initView(inflater, container);
        initData();
        initListener();
        initEvent();
        return view;
    }

    protected void initSome(Bundle savedInstanceState) {
    }

    protected abstract View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container);

    protected abstract void initData();

    protected abstract void initListener();

    protected void initEvent() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context = null;
        activity = null;
        fragment=null;
    }
}
