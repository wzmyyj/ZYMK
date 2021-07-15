package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.HuaBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.view.adapter.HuaAdapter;
import top.wzmyyj.zymk.base.panel.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class DetailsMuPanel extends BasePanel<DetailsContract.IPresenter> {

    @BindView(R.id.tv_mu_last)
    TextView tvMuLast;
    @BindView(R.id.tv_mu_xu)
    TextView tvMuXu;
    @BindView(R.id.img_mu_xu)
    ImageView imgMuXu;
    @BindView(R.id.rv_mu)
    RecyclerView rvMu;
    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.tl_mu_pager)
    TabLayout tlMuPager;

    private HuaAdapter mAdapter;
    private final List<HuaBean> mData = new ArrayList<>();
    private int mBookId;
    private final List<HuaBean> mALLHuaList = new ArrayList<>();
    private int pagerSize;
    private int sortXu = 1;

    public DetailsMuPanel(Context context, DetailsContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_details_mu;
    }

    @Override
    protected void initView() {
        super.initView();
        rvMu.setLayoutManager(new GridLayoutManager(context, 4));
        rvMu.setNestedScrollingEnabled(false);
        mAdapter = new HuaAdapter(context, mData);
        rvMu.setAdapter(mAdapter);
    }

    @Override
    protected void initListener() {
        super.initListener();
        tlMuPager.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int p = tab.getPosition() + 1;
                setData(p);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        mAdapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                mPresenter.goComic(mBookId, mData.get(position).getId());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    public void setHistoryChapter(ChapterBean chapter) {// 传入历史阅读章节。
        setData(chapter.getChapterId());
    }

    public void setMuData(MuBean mu) {
        if (mu == null) return;
        tvMuLast.setText(mu.getTimeDesc());
        mBookId = mu.getBook_id();
        if (mu.getHuaList() != null) {
            mALLHuaList.clear();
            mALLHuaList.addAll(mu.getHuaList());
            pagerSize = mALLHuaList.size() / 32 + 1;
            tlMuPager.removeAllTabs();
            for (int i = 0; i < pagerSize; i++) {
                tlMuPager.addTab(tlMuPager.newTab().setText(i + 1 + ""));
            }
            sort(mALLHuaList, sortXu);
            setData(1);
        }
    }

    private void sort(List<HuaBean> list, final int k) {
        Collections.sort(list, (o1, o2) -> k * Integer.compare(o2.getIndex(), o1.getIndex()));
    }

    @OnClick(R.id.tv_left)
    public void left() {
        setData(tlMuPager.getSelectedTabPosition());
    }

    @OnClick(R.id.tv_right)
    public void right() {
        setData(tlMuPager.getSelectedTabPosition() + 2);
    }

    @OnClick({R.id.tv_mu_xu, R.id.img_mu_xu})
    public void xu() {
        if (sortXu == 1) {
            sortXu = -1;
            tvMuXu.setText("降序");
            imgMuXu.setImageResource(R.mipmap.svg_booklist_down);
        } else {
            sortXu = 1;
            tvMuXu.setText("升序");
            imgMuXu.setImageResource(R.mipmap.svg_book_list_up);
        }
        sort(mALLHuaList, sortXu);
        setData(1);
    }

    private void setData(long chapter_id) {
        if (chapter_id == 0) {
            setData(1);
            return;
        }
        int p = 1;
        int i = 0;
        for (HuaBean hua : mALLHuaList) {
            if (hua.getId() == chapter_id) {
                mAdapter.setRead(chapter_id);
                p = i / 32 + 1;
                break;
            }
            i++;
        }
        setData(p);
    }

    private void setData(int p) {
        if (p < 1 || p > pagerSize) return;
        Objects.requireNonNull(tlMuPager.getTabAt(p - 1)).select();
        if (p == 1) {
            tvLeft.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        } else {
            tvLeft.setTextColor(context.getResources().getColor(R.color.colorGray_7));
        }
        if (p == pagerSize) {
            tvRight.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        } else {
            tvRight.setTextColor(context.getResources().getColor(R.color.colorGray_7));
        }
        mData.clear();
        if (p == pagerSize) {
            mData.addAll(mALLHuaList.subList(32 * (p - 1), mALLHuaList.size()));
        } else {
            mData.addAll(mALLHuaList.subList(32 * (p - 1), 32 * p));
        }
        mAdapter.notifyDataSetChanged();
    }
}
