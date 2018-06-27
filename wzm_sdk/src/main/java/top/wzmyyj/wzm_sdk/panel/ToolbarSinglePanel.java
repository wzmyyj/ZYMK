package top.wzmyyj.wzm_sdk.panel;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.LinearLayout;

import top.wzmyyj.wzm_sdk.R;

/**
 * Created by wzm on 2018/4/28 0028.
 */

public abstract class ToolbarSinglePanel extends InitPanel {


    private LinearLayout layout;
    private Toolbar mToolbar;
    private ImageView img_1;
    private ImageView img_2;
    private Panel mPanel;

    public ToolbarSinglePanel(Context context) {
        super(context);
    }

    @Override
    protected void initPanelList() {
        super.initPanelList();
        mPanel = getPanel();
        addPanel(mPanel);
    }

    @NonNull
    protected abstract Panel getPanel();

    @Override
    protected void initView() {
        view = mInflater.inflate(R.layout.af_single_panel, null);
        layout = view.findViewById(R.id.ll_1);
        mToolbar = view.findViewById(R.id.toolbar);
        img_1 = view.findViewById(R.id.img_1);
        img_2 = view.findViewById(R.id.img_2);
        mPanel = mPanelList.get(0);
        setView(mToolbar, img_1, img_2);
        layout.addView(mPanel.getView());
    }

    protected abstract void setView(Toolbar toolbar, ImageView img_1, ImageView img_2);


    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }
}
