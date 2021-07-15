package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.contract.ComicContract;
import top.wzmyyj.zymk.base.panel.BasePanel;

/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class ComicLoadPosePanel extends BasePanel<ComicContract.IPresenter> {

    @BindView(R.id.img_load_pose)
    ImageView imgLoadPose;
    @BindView(R.id.tv_load_pose)
    TextView tvLoadPose;

    private final static int MAX_COUNT_LV1 = 10;
    private final static int MAX_COUNT_LV2 = 40;
    private int maxCount = MAX_COUNT_LV2;
    private int count = 0;
    private int status = -1;
    private ComicBean firstComic = null;

    public ComicLoadPosePanel(Context context, ComicContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_comic_loadpose;
    }

    @OnClick(R.id.tv_load_pose)
    public void reLoad() {
        if (status == -1) {
            mHandler.sendEmptyMessage(1);
            mPresenter.loadData();
            tvLoadPose.setText("正在加载中。。。");
            tvLoadPose.setTextColor(context.getResources().getColor(R.color.colorPrimary));
        }
    }

    @Override
    protected void initData() {
        super.initData();
        showLoad();
    }

    @SuppressLint("HandlerLeak")
    private final Handler mHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mPresenter.log("msg what= " + msg.what);
            switch (msg.what) {
                case 1:
                    imgLoadPose.setImageResource(R.mipmap.pic_load_pose1);
                    mHandler.sendEmptyMessageDelayed(2, 100);
                    break;
                case 2:
                    imgLoadPose.setImageResource(R.mipmap.pic_loadpose2);
                    mHandler.sendEmptyMessageDelayed(1, 100);
                    break;
            }
            count++;
            if (isFirstComicReady()) {
                maxCount = Math.max(count + 5, MAX_COUNT_LV1);
                firstComic = null;
            }
            if (status == 0 && (count > MAX_COUNT_LV2 || count > maxCount)) {
                mHandler.removeMessages(1);
                mHandler.removeMessages(2);
                view.setVisibility(View.GONE);
            }
        }
    };

    public void showLoad() {
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
        view.setVisibility(View.VISIBLE);
        count = 0;
        status = -1;
        maxCount = MAX_COUNT_LV2;
        mHandler.sendEmptyMessage(1);
    }

    public void loadSuccess() {
        status = 0;
    }

    public void loadFail() {
        mHandler.removeMessages(0);
        tvLoadPose.setText("加载失败，点击重试！");
        tvLoadPose.setTextColor(context.getResources().getColor(R.color.colorRed));
        imgLoadPose.setImageResource(R.mipmap.pic_load_error);
    }

    public void setFirstComic(ComicBean firstComic) {
        this.firstComic = firstComic;
    }

    private boolean isFirstComicReady() {
        ComicBean comic = this.firstComic;
        if (comic == null) return false;
        if (comic.getChapterId() == -1 || comic.getPrice() > 0) return true;
        return comic.getImgWidth() > 0 && comic.getImgHeight() > 0 && !comic.isLoading();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mHandler.removeMessages(1);
        mHandler.removeMessages(2);
    }
}
