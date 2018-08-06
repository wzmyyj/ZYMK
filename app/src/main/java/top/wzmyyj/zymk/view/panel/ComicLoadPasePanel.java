package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.presenter.ComicPresenter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;


/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 */

public class ComicLoadPasePanel extends BasePanel<ComicPresenter> {
    public ComicLoadPasePanel(Context context, ComicPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_comic_loadpose;
    }

    @BindView(R.id.img_loadpose)
    ImageView img_loadpose;
    @BindView(R.id.tv_loadpose)
    TextView tv_loadpose;

    @OnClick(R.id.tv_loadpose)
    public void reLoad() {
        if (status == -1) {
            mHandler.sendEmptyMessageDelayed(1, 500);
            mPresenter.loadData();
            tv_loadpose.setText("正在加载中。。。");
        }
    }


    @Override
    protected void initData() {
        super.initData();
        mHandler.sendEmptyMessageDelayed(1, 500);
        mPresenter.loadData();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int w = msg.what;
            if (w == 1) {
                G.img(context, R.mipmap.pic_loadpose1, img_loadpose);
                mHandler.sendEmptyMessageDelayed(2, 500);
            } else if (w == 2) {
                G.img(context, R.mipmap.pic_loadpose2, img_loadpose);
                mHandler.sendEmptyMessageDelayed(1, 500);
            } else {
                mHandler.removeMessages(1);
                mHandler.removeMessages(2);
            }
        }
    };

    private int status = 0;

    @Override
    public Object f(int w, Object... objects) {
        status = w;
        mHandler.sendEmptyMessage(0);
        if (w == -1) {
            tv_loadpose.setText("加载失败，点击重试！");
            return null;
        }
        view.setVisibility(View.GONE);
        return super.f(w, objects);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.sendEmptyMessage(0);
    }
}
