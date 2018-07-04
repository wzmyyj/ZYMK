package top.wzmyyj.wzm_sdk.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    protected View mVRoot;
    protected LayoutInflater mInflater;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mInflater = inflater;
        if (mVRoot == null) {
            initSome(savedInstanceState);
            initView();
            initData();
            initListener();
            initEvent();
        }
        return mVRoot;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = (Activity) context;
        this.fragment = this;
    }

    protected void initSome(Bundle savedInstanceState) {

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void initListener();

    protected void initEvent() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        context = null;
        activity = null;
        fragment = null;
    }

    protected void addFragment(int view_id, Fragment f,String tag) {
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(view_id, f, tag);
        fragmentTransaction.addToBackStack(tag);
        fragmentTransaction.commitAllowingStateLoss();
    }
}
