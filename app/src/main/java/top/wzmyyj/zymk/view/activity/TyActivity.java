package top.wzmyyj.zymk.view.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.TyPresenter;
import top.wzmyyj.zymk.view.activity.base.BaseActivity;
import top.wzmyyj.zymk.view.iv.ITyView;
import top.wzmyyj.zymk.view.panel.TyRecyclerPanel;

public class TyActivity extends BaseActivity<TyPresenter> implements ITyView {

    @Override
    protected void initPresenter() {
        mPresenter = new TyPresenter(activity, this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_ty;
    }

    @Override
    protected void initPanels() {
        super.initPanels();
        addPanels(new TyRecyclerPanel(activity, mPresenter));
    }

    @BindView(R.id.ll_panel)
    LinearLayout layout;
    @BindView(R.id.img_back)
    ImageView img_back;
    @BindView(R.id.tv_title)
    TextView tv_title;

    @Override
    protected void initView() {
        super.initView();
        layout.addView(getPanelView(0));
    }

    @Override
    protected void initListener() {
        super.initListener();
        img_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.finish();
            }
        });
    }

    @Override
    public void update(int w, Object... objs) {
        getPanel(0).f(w, objs);
    }

    @Override
    public void setTitle(String s) {
        if (tv_title != null)
            tv_title.setText(s);
    }
}
