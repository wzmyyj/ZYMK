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
import top.wzmyyj.zymk.contract.ComicContract;
import top.wzmyyj.zymk.view.panel.base.BasePanel;


/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 */

public class ComicLoadPasePanel extends BasePanel<ComicContract.IPresenter> {
    public ComicLoadPasePanel(Context context, ComicContract.IPresenter p) {
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
            mHandler.sendEmptyMessage(1);
            mPresenter.loadData();
            tv_loadpose.setText("正在加载中。。。");
            tv_loadpose.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }


    @Override
    protected void initData() {
        super.initData();
        count = 0;
        status = -1;
        mHandler.sendEmptyMessage(1);
    }


    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    img_loadpose.setImageResource(R.mipmap.pic_loadpose1);
                    mHandler.sendEmptyMessageDelayed(2, 200);
                    break;
                case 2:
                    img_loadpose.setImageResource(R.mipmap.pic_loadpose2);
                    mHandler.sendEmptyMessageDelayed(1, 200);
                    break;
                default:
                    mHandler.removeMessages(1);
                    mHandler.removeMessages(2);
                    break;
            }

            count++;
            if (status == 0 && count > 7) {
                mHandler.sendEmptyMessage(0);
                view.setVisibility(View.GONE);
            }
        }
    };
    private int count = 0;
    private int status = -1;

    public void loadSuccess() {
        status = 0;
    }

    public void loadFail() {
        mHandler.sendEmptyMessage(0);
        tv_loadpose.setText("加载失败，点击重试！");
        tv_loadpose.setTextColor(context.getResources().getColor(R.color.colorRed));
        img_loadpose.setImageResource(R.mipmap.pic_load_error);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.sendEmptyMessage(0);
    }
}
