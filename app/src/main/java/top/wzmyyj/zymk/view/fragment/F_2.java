package top.wzmyyj.zymk.view.fragment;

import android.widget.ImageView;

import top.wzmyyj.wzm_sdk.fragment.InitFragment;
import top.wzmyyj.zymk.R;

/**
 * Created by wzm on 2018/06/29. email: 2209011667@qq.com
 */

public class F_2 extends InitFragment {

    private ImageView imageView;

    @Override
    protected void initView() {
        mVRoot = mInflater.inflate(R.layout.fragment_4, null);
        imageView = mVRoot.findViewById(R.id.img_content);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }


}
