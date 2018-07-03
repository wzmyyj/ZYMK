package top.wzmyyj.zymk.view.fragment;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import top.wzmyyj.wzm_sdk.fragment.InitFragment;
import top.wzmyyj.zymk.R;

/**
 * Created by wzm on 2018/06/29. email: 2209011667@qq.com
 */

public class F_2 extends InitFragment {

    private ImageView imageView;

    @Override
    protected View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container) {
        View view = inflater.inflate(R.layout.fragment_4, null);
        imageView = view.findViewById(R.id.img_content);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


}
