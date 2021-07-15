package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.xw.repo.BubbleSeekBar;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.wzm_sdk.tools.A;
import top.wzmyyj.wzm_sdk.utils.DensityUtil;
import top.wzmyyj.wzm_sdk.utils.MockUtil;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.ComicBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.base.panel.BasePanel;
import top.wzmyyj.zymk.contract.ComicContract;

/**
 * Created by yyj on 2018/08/06. email: 2209011667@qq.com
 * 漫画阅读菜单面板。
 */
@SuppressLint("NonConstantResourceId")
public class ComicMenuPanel extends BasePanel<ComicContract.IPresenter> {

    @BindView(R.id.fl_top)
    FrameLayout flTop;
    @BindView(R.id.tv_chapter_name)
    TextView tvChapterName;
    @BindView(R.id.tv_chapter_var)
    TextView tvChapterVar;
    @BindView(R.id.ll_bottom)
    LinearLayout llBottom;
    @BindView(R.id.ll_auto)
    LinearLayout llAuto;
    @BindView(R.id.bsb_auto)
    BubbleSeekBar bsbAuto;
    @BindView(R.id.ll_progress)
    LinearLayout llProgress;
    @BindView(R.id.bsb_1)
    BubbleSeekBar mBsb;
    @BindView(R.id.tv_chapter_var2)
    TextView tvChapterVar2;
    @BindView(R.id.rv_catalog)
    RecyclerView rvCatalog;
    @BindView(R.id.img_auto)
    ImageView imgAuto;
    @BindView(R.id.img_definition)
    ImageView imgDefinition;
    @BindView(R.id.ll_definition)
    LinearLayout llDefinition;
    @BindView(R.id.ll_brightness)
    LinearLayout llBrightness;
    @BindView(R.id.v_brightness)
    View vBrightness;
    @BindView(R.id.bsb_brightness)
    BubbleSeekBar bsbBrightness;
    @BindView(R.id.img_catalog_xu)
    ImageView imgCatalogXu;
    @BindView(R.id.tv_catalog_xu)
    TextView tvCatalogXu;
    @BindView(R.id.ll_catalog)
    LinearLayout llCatalog;

    private ComicRecyclerPanel comicRecyclerPanel;
    private ComicRecyclerPanel.MyRunnable myRunnable;
    private final List<ChapterBean> mCatalogChapterList = new ArrayList<>();
    private CommonAdapter<ChapterBean> mCatalogAdapter;
    private LinearSmoothScroller linearSmoothScroller;
    private boolean isBsbOnTouch = false;
    private boolean isShow = false;
    private boolean isAuto = false;
    private boolean isShowMenuBrightness = false;
    private boolean isShowMenuDefinition = false;
    private boolean isShowMenuCatalog = false;
    private int xu = 1;

    public ComicMenuPanel(Context context, ComicContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_comic_menu;
    }

    public void setComicRecyclerPanel(ComicRecyclerPanel comicRecyclerPanel) {
        this.comicRecyclerPanel = comicRecyclerPanel;
        this.myRunnable = comicRecyclerPanel.getMyRunnable();
    }

    @OnClick(R.id.img_back)
    public void back() {
        mPresenter.finish();
    }

    // 跳转到设置页面。
    @OnClick(R.id.ll_menu_1)
    public void menu_1() {
        mPresenter.goSetting();
    }

    // 设置自动滑动和停止。
    @OnClick(R.id.ll_menu_2)
    public void menu_2() {
        if (isAuto) {
            stopAuto();
        } else {
            startAuto();
        }
    }

    // 设置画质。
    @OnClick(R.id.ll_menu_3)
    public void menu_3() {
        if (isShowMenuDefinition) {
            closeMenuDefinition();
        } else {
            showMenuDefinition();
        }
    }

    // 设置亮度
    @OnClick(R.id.ll_menu_4)
    public void menu_4() {
        if (isShowMenuBrightness) {
            closeMenuBrightness();
        } else {
            showMenuBrightness();
        }
    }

    @OnClick(R.id.ll_menu_5)
    public void menu_5() {
        if (!isShowMenuCatalog) {
            closeMenu();
            showMenuCatalog();
        }
    }

    // 流畅画质
    @OnClick(R.id.img_definition_low)
    public void setDefinitionLow() {
        comicRecyclerPanel.setDefinitionLow();
        imgDefinition.setImageResource(R.mipmap.ic_read_definition_low);
        closeMenuDefinition();
    }

    // 标清画质
    @OnClick(R.id.img_definition_middle)
    public void setDefinitionMiddle() {
        comicRecyclerPanel.setDefinitionMiddle();
        imgDefinition.setImageResource(R.mipmap.ic_read_definition_middle);
        closeMenuDefinition();
    }

    // 高清画质
    @OnClick(R.id.img_definition_high)
    public void setDefinitionHigh() {
        comicRecyclerPanel.setDefinitionHigh();
        imgDefinition.setImageResource(R.mipmap.ic_read_definition_high);
        closeMenuDefinition();
    }

    @OnClick({R.id.img_catalog_all, R.id.tv_catalog_all})
    public void catalogAll() {
        comicRecyclerPanel.goDetails();
        mPresenter.finish();
    }

    @OnClick({R.id.img_catalog_xu, R.id.tv_catalog_xu})
    public void catalogXu() {
        if (xu == 1) {
            // 转为倒序。
            xu = -1;
            imgCatalogXu.setImageResource(R.mipmap.ic_read_catalog_reverse);
            tvCatalogXu.setText("倒序");
        } else {
            // 转为正序。
            xu = 1;
            imgCatalogXu.setImageResource(R.mipmap.ic_read_catalog_order);
            tvCatalogXu.setText("正序");
        }
        Collections.reverse(mCatalogChapterList);
        mCatalogAdapter.notifyDataSetChanged();
    }

    public boolean isAuto() {
        return isAuto;
    }

    public void showMenu() {
        closeMenuBrightness();
        closeMenuDefinition();
        if (isShow) return;
        isShow = true;
        toggleMenu();
        closeMenuCatalog();
    }

    public void closeMenu() {
        closeMenuBrightness();
        closeMenuDefinition();
        if (!isShow) return;
        isShow = false;
        toggleMenu();
        closeMenuCatalog();
    }

    public void showMenuCatalog() {
        if (isShowMenuCatalog) return;
        isShowMenuCatalog = true;
        scrollCatalog(true);
        toggleMenuCatalog();
    }

    public void closeMenuCatalog() {
        if (!isShowMenuCatalog) return;
        isShowMenuCatalog = false;
        toggleMenuCatalog();
    }

    public void setCatalogChapterList(List<ChapterBean> list) {
        mCatalogChapterList.clear();
        mCatalogChapterList.addAll(list);
        mCatalogAdapter.notifyDataSetChanged();
    }

    public void scrollCatalog() {
        scrollCatalog(false);
    }

    public void setMenu(ComicBean comic) {
        int max = comic.getVarSize();
        int p = comic.getVar();
        tvChapterName.setText(comic.getChapterName());
        String var = p + "/" + max;
        tvChapterVar.setText(var);
        tvChapterVar2.setText(var);
        if (isBsbOnTouch) return;// bsb_1正在被点击时，不设置它。
        mBsb.getConfigBuilder()
                .max(max)
                .min(1)
                .progress(p)
                .build();
    }

    public void clickSome() {
        if (isShowMenuDefinition
                || isShowMenuBrightness
                || isShowMenuCatalog) {// 有子菜单打开先关闭子菜单。
            closeChildMenu();
            return;
        }
        if (isAuto) {// 自动播放时。
            changeMenu();
            return;
        }
        myRunnable.a = 0;
        myRunnable.b = MockUtil.getScreenHeight(context) / 2;
        myRunnable.c = MockUtil.getScreenHeight(context) / 30;
        comicRecyclerPanel.postAutoScroll();
    }

    @Override
    protected void initView() {
        super.initView();
        flTop.setVisibility(View.INVISIBLE);
        llBottom.setVisibility(View.INVISIBLE);
        llDefinition.setVisibility(View.INVISIBLE);
        llBrightness.setVisibility(View.INVISIBLE);
        llCatalog.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void initData() {
        super.initData();
        rvCatalog.setLayoutManager(new LinearLayoutManager(context));
        linearSmoothScroller = new LinearSmoothScroller(context) {
            @Override
            protected int getVerticalSnapPreference() {
                return SNAP_TO_START;
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return super.calculateSpeedPerPixel(displayMetrics) * 5;
            }
        };
        mCatalogAdapter = new CommonAdapter<ChapterBean>(context, R.layout.layout_catalog_item, mCatalogChapterList) {
            @Override
            protected void convert(ViewHolder holder, ChapterBean chapter, int position) {
                ImageView img_pic = holder.getView(R.id.img_catalog_pic);
                TextView tv_name = holder.getView(R.id.tv_catalog_name);
                LinearLayout ll_bg = holder.getView(R.id.ll_catalog_bg);
                if (chapter.getChapterId() == comicRecyclerPanel.getChapterId()) {
                    ll_bg.setBackgroundResource(R.color.colorPrimary);
                } else {
                    ll_bg.setBackgroundResource(R.color.colorClarity);
                }
                if (chapter.getPrice() == 0) {
                    GlideLoaderHelper.img(img_pic, chapter.getFirstImageLow());
                } else {
                    GlideLoaderHelper.img(img_pic, R.mipmap.pic_need_money);
                }
                tv_name.setText(chapter.getChapterName());
            }
        };
        rvCatalog.setAdapter(mCatalogAdapter);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void initListener() {
        super.initListener();
        mBsb.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    isBsbOnTouch = true;
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_CANCEL:
                    isBsbOnTouch = false;
                    break;
            }
            return false;
        });
        mBsb.setOnProgressChangedListener(new BubbleSeekBarChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar,
                                          int progress, float progressFloat, boolean fromUser) {
                if (fromUser) {// 被点击时，判断是否由于自身被触摸而引发的改变。
                    comicRecyclerPanel.progressChanged(progress);
                }
            }
        });
        bsbAuto.setOnProgressChangedListener(new BubbleSeekBarChangedListener() {
            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar,
                                              int progress, float progressFloat) {
                myRunnable.c = DensityUtil.pt2px(context, (float) 2 * progressFloat);
            }
        });
        bsbBrightness.setOnProgressChangedListener(new BubbleSeekBarChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar,
                                          int progress, float progressFloat, boolean fromUser) {
                vBrightness.setAlpha(1 - progressFloat);
            }
        });
        mCatalogAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                long id = mCatalogChapterList.get(position).getChapterId();
                comicRecyclerPanel.goSeeChapterById(id);
                mCatalogAdapter.notifyDataSetChanged();
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private void startAuto() {
        if (isAuto) return;
        isAuto = true;
        llAuto.setVisibility(View.VISIBLE);
        llProgress.setVisibility(View.GONE);
        imgAuto.setImageResource(R.mipmap.ic_read_stop);
        myRunnable.a = 0;
        myRunnable.b = Integer.MAX_VALUE;
        myRunnable.c = DensityUtil.pt2px(context, bsbAuto.getProgressFloat() * 2);
        comicRecyclerPanel.postAutoScroll();
    }

    private void stopAuto() {
        if (!isAuto) return;
        isAuto = false;
        llAuto.setVisibility(View.GONE);
        llProgress.setVisibility(View.VISIBLE);
        imgAuto.setImageResource(R.mipmap.ic_read_start);
        myRunnable.b = 0;
    }

    private void scrollCatalog(boolean isOpen) {
        if (!isShowMenuCatalog || mCatalogChapterList.size() < 1) return;
        mCatalogAdapter.notifyDataSetChanged();
        int j = mCatalogChapterList.size() - 1;
        final long chapterId = comicRecyclerPanel.getChapterId();
        for (int i = 0; i < mCatalogChapterList.size(); i++) {
            if (mCatalogChapterList.get(i).getChapterId() == chapterId) {
                j = i;
                break;
            }
        }
        int p = Math.max(j - 3, 0);
        if (p > mCatalogChapterList.size() - 1) return;// 防止越界。
        LinearLayoutManager mLayoutManager = (LinearLayoutManager) rvCatalog.getLayoutManager();
        if (mLayoutManager == null) return;
        if (isOpen) {
            mLayoutManager.scrollToPositionWithOffset(p, 0);
        } else {
            int count = rvCatalog.getChildCount();
            if (count == 0) return;
            int first = rvCatalog.getChildLayoutPosition(rvCatalog.getChildAt(0));
            int last = rvCatalog.getChildLayoutPosition(rvCatalog.getChildAt(count - 1));
            if (p >= first && p <= last) {
                linearSmoothScroller.setTargetPosition(p);
                mLayoutManager.startSmoothScroll(linearSmoothScroller);
            } else {
                mLayoutManager.scrollToPositionWithOffset(p, 0);
            }
        }
    }

    private void changeMenu() {
        if (isShow) {
            closeMenu();
        } else {
            showMenu();
        }
    }

    private void toggleMenuCatalog() {
        int w = llCatalog.getWidth();
        int fromX = 0, toX = 0;
        if (isShowMenuCatalog) {
            fromX = w;
            llCatalog.setVisibility(View.VISIBLE);
        } else {
            toX = w;
            llCatalog.setVisibility(View.INVISIBLE);
        }
        A.create()
                .t(fromX, toX, 0, 0)
                .duration(300)
                .listener(new A.AListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (!isShowMenuCatalog) llCatalog.setVisibility(View.INVISIBLE);
                    }
                })
                .into(llCatalog);
    }

    private void showMenuDefinition() {
        if (isShowMenuDefinition) return;
        isShowMenuDefinition = true;
        toggleMenuDefinition();
    }

    private void closeMenuDefinition() {
        if (!isShowMenuDefinition) return;
        isShowMenuDefinition = false;
        toggleMenuDefinition();
    }

    private void toggleMenuDefinition() {
        int h = llDefinition.getHeight();
        int fromY = 0, toY = 0;
        float fromA = 1.0f, toA = 1.0f;
        if (isShowMenuDefinition) {
            fromY = -h / 2;
            fromA = 0.0f;
            llDefinition.setVisibility(View.VISIBLE);
        } else {
            toY = -h / 2;
            toA = 0.0f;
            llDefinition.setVisibility(View.GONE);
        }
        A.create()
                .t(0, 0, fromY, toY)
                .a(fromA, toA)
                .duration(300)
                .listener(new A.AListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (!isShowMenuDefinition) llDefinition.setVisibility(View.GONE);
                    }
                })
                .into(llDefinition);
    }

    private void showMenuBrightness() {
        if (isShowMenuBrightness) return;
        isShowMenuBrightness = true;
        toggleMenuBrightness();
    }

    private void closeMenuBrightness() {
        if (!isShowMenuBrightness) return;
        isShowMenuBrightness = false;
        toggleMenuBrightness();
    }

    private void toggleMenuBrightness() {
        int h = llBrightness.getHeight();
        int fromY = 0, toY = 0;
        float fromA = 1.0f, toA = 1.0f;
        if (isShowMenuBrightness) {
            fromY = -h / 2;
            fromA = 0.0f;
            llBrightness.setVisibility(View.VISIBLE);
        } else {
            toY = -h / 2;
            toA = 0.0f;
            llBrightness.setVisibility(View.GONE);
        }
        A.create()
                .t(0, 0, fromY, toY)
                .a(fromA, toA)
                .duration(300)
                .listener(new A.AListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (!isShowMenuBrightness) llBrightness.setVisibility(View.GONE);
                    }
                })
                .into(llBrightness);
    }

    private void closeChildMenu() {
        closeMenuDefinition();
        closeMenuBrightness();
        closeMenuCatalog();
    }

    private void toggleMenu() {
        int th = flTop.getHeight();
        int bh = llBottom.getHeight();
        int fromY1 = 0, toY1 = 0, fromY2 = 0, toY2 = 0;
        if (isShow) {
            fromY1 = -th;
            fromY2 = bh;
            flTop.setVisibility(View.VISIBLE);
            llBottom.setVisibility(View.VISIBLE);
        } else {
            toY1 = -th;
            toY2 = bh;
            flTop.setVisibility(View.INVISIBLE);
            llBottom.setVisibility(View.INVISIBLE);
        }
        A.create()
                .t(0, 0, fromY1, toY1)
                .duration(300)
                .listener(new A.AListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (!isShow) flTop.setVisibility(View.INVISIBLE);
                    }
                })
                .into(flTop);
        A.create()
                .t(0, 0, fromY2, toY2)
                .duration(300)
                .listener(new A.AListener() {
                    @Override
                    public void onAnimationRepeat(Animation animation) {
                        if (!isShow) llBottom.setVisibility(View.INVISIBLE);
                    }
                })
                .into(llBottom);
    }

    interface BubbleSeekBarChangedListener extends BubbleSeekBar.OnProgressChangedListener {

        default void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
        }

        default void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
        }

        default void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
        }
    }
}
