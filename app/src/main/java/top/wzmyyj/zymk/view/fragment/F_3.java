package top.wzmyyj.zymk.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_3View;

/**
 * Created by yyj on 2018/07/06. email: 2209011667@qq.com
 */

public class F_3 extends BaseFragment<FindPresenter> implements IF_3View {
    @Override
    protected void initPresenter() {
        mPresenter = new FindPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_3;
    }


    @BindView(R.id.ll_1)
    LinearLayout ll_1;
    @BindView(R.id.ll_2)
    LinearLayout ll_2;
    @BindView(R.id.ll_3)
    LinearLayout ll_3;
    @BindView(R.id.img_search)
    ImageView img_search;

    @Override
    protected void initView() {
        super.initView();
        ll_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goHomeWeb();
            }
        });
        ll_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goActivityWeb();
            }
        });
        ll_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.goTmallWeb();
            }
        });
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("搜索");
            }
        });
    }
}
