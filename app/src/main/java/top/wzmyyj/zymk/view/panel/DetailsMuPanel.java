package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.ChapterBean;
import top.wzmyyj.zymk.app.bean.HuaBean;
import top.wzmyyj.zymk.app.bean.MuBean;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.view.adapter.HuaAdapter;
import top.wzmyyj.zymk.view.panel.base.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */

public class DetailsMuPanel extends BasePanel<DetailsContract.IPresenter> {
    public DetailsMuPanel(Context context, DetailsContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_details_mu;
    }

    @BindView(R.id.tv_mu_last)
    TextView tv_mu_last;
    @BindView(R.id.tv_mu_xu)
    TextView tv_mu_xu;
    @BindView(R.id.img_mu_xu)
    ImageView img_mu_xu;
    @BindView(R.id.rv_mu)
    RecyclerView rv_mu;
    @BindView(R.id.tv_left)
    TextView tv_left;
    @BindView(R.id.tv_right)
    TextView tv_right;

    HuaAdapter mAdapter;
    List<HuaBean> mData = new ArrayList<>();


    @BindView(R.id.tl_mu_pager)
    TabLayout tl_mu_pager;

    @Override
    protected void initView() {
        super.initView();
        rv_mu.setLayoutManager(new GridLayoutManager(context, 4));
        rv_mu.setNestedScrollingEnabled(false);
        mAdapter = new HuaAdapter(context, R.layout.layout_mu_item, mData);
        rv_mu.setAdapter(mAdapter);


    }

    @Override
    protected void initListener() {
        super.initListener();
        tl_mu_pager.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
                mPresenter.goComic(mBookID, mData.get(position).getId());
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
    }

    private int mBookID;
    private List<HuaBean> mALLHuaList = new ArrayList<>();

    public void setHistoryChapter(ChapterBean chapter) {// 传入历史阅读章节。
        read_chapter_id = chapter.getChapter_id();
        setData(read_chapter_id);
    }

    public void setMuData(MuBean mu) {
        if (mu == null) return;
        tv_mu_last.setText(mu.getTime_desc());

        mBookID = mu.getBook_id();

        if (mu.getHuaList() != null) {
            mALLHuaList.clear();
            mALLHuaList.addAll(mu.getHuaList());

            pager_size = mALLHuaList.size() / 32 + 1;

            tl_mu_pager.removeAllTabs();
            for (int i = 0; i < pager_size; i++) {
                tl_mu_pager.addTab(tl_mu_pager.newTab().setText(i + 1 + ""));
            }

            sort(mALLHuaList, sort_xu);
            setData(1);
        }
    }

    private void sort(List list, int k) {
        Collections.sort(list, new HuaComparator(k));
    }

    private long read_chapter_id;
    private int pager_size;
    private int sort_xu = 1;

    @OnClick(R.id.tv_left)
    public void left() {
        setData(tl_mu_pager.getSelectedTabPosition());
    }

    @OnClick(R.id.tv_right)
    public void right() {
        setData(tl_mu_pager.getSelectedTabPosition() + 2);
    }


    @OnClick({R.id.tv_mu_xu, R.id.img_mu_xu})
    public void xu() {
        if (sort_xu == 1) {
            sort_xu = -1;
            tv_mu_xu.setText("降序");
            img_mu_xu.setImageResource(R.mipmap.svg_booklist_down);
        } else {
            sort_xu = 1;
            tv_mu_xu.setText("升序");
            img_mu_xu.setImageResource(R.mipmap.svg_booklist_up);
        }

        sort(mALLHuaList, sort_xu);
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
        if (p < 1 || p > pager_size) return;

        tl_mu_pager.getTabAt(p - 1).select();

        if (p == 1) {
            tv_left.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        } else {
            tv_left.setTextColor(context.getResources().getColor(R.color.colorGray_7));
        }

        if (p == pager_size) {
            tv_right.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        } else {
            tv_right.setTextColor(context.getResources().getColor(R.color.colorGray_7));
        }

        mData.clear();
        if (p == pager_size) {
            mData.addAll(mALLHuaList.subList(32 * (p - 1), mALLHuaList.size()));
        } else {
            mData.addAll(mALLHuaList.subList(32 * (p - 1), 32 * p));
        }
        mAdapter.notifyDataSetChanged();

    }

    public class HuaComparator implements Comparator<HuaBean> {

        private int k;

        public HuaComparator(int k) {
            this.k = k;
        }

        @Override
        public int compare(HuaBean o1, HuaBean o2) {
            if (o1.getIndex() < o2.getIndex()) {
                return k;
            } else {
                return -k;
            }
        }
    }

}
