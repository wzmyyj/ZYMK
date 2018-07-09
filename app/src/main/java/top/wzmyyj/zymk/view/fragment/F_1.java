package top.wzmyyj.zymk.view.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.tools.T;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.p.HomePresenter;
import top.wzmyyj.zymk.view.fragment.base.BaseFragment;
import top.wzmyyj.zymk.view.iv.IF_1View;
import top.wzmyyj.zymk.view.panel.HomeRecyclerPanel;

/**
 * Created by yyj on 2018/06/28. email: 2209011667@qq.com
 */

public class F_1 extends BaseFragment<HomePresenter> implements IF_1View {


    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }


    @Override
    public void showToast(String t) {

    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new HomeRecyclerPanel(activity, mPresenter));
    }

    @BindView(R.id.ll_panel)
    LinearLayout layout;
    @BindView(R.id.img_a)
    ImageView img_a;
    @BindView(R.id.img_search)
    ImageView img_search;


    @Override
    protected void initView() {
        super.initView();
        layout.addView(getPanelView(0));
    }

    @Override
    protected void initListener() {
        super.initListener();
        img_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                T.s("搜索");
            }
        });
    }

    @Override
    public void update(Object... objs) {
        getPanel(0).f(0, objs);
    }
}



