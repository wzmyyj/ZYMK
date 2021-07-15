package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.wzm_sdk.utils.DensityUtil;
import top.wzmyyj.zymk.contract.FindContract;
import top.wzmyyj.zymk.base.panel.BaseRecyclerPanel;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public abstract class FindRecyclerPanel<T> extends BaseRecyclerPanel<T, FindContract.IPresenter> {

    protected TextView tvEmpty;
    protected View mMenu;
    protected TextView tvNum;
    protected ImageView imgPl;
    protected ImageView imgGg;
    protected LinearLayout llBottom;
    protected TextView tvSelectAll;
    protected TextView tvDelete;
    protected TextView tvEnd;
    protected boolean isGrid = false;
    protected boolean isCanSelect;
    protected final List<T> mSelectedList = new ArrayList<>();

    public FindRecyclerPanel(Context context, FindContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mRefreshLayout.getLayoutParams();
        params.topMargin = DensityUtil.pt2px(context, 35);
        setMenu();
        mFrameLayout.addView(mMenu);
    }

    @SuppressLint("InflateParams")
    protected void setMenu() {
        mMenu = mInflater.inflate(R.layout.layout_find_menu, null);
        tvNum = mMenu.findViewById(R.id.tv_favor_num);
        imgPl = mMenu.findViewById(R.id.img_favor_pl);
        imgGg = mMenu.findViewById(R.id.img_favor_gg);
        llBottom = mMenu.findViewById(R.id.ll_bottom);
        tvSelectAll = mMenu.findViewById(R.id.tv_select_all);
        tvDelete = mMenu.findViewById(R.id.tv_delete);
        llBottom.setVisibility(View.GONE);
        imgPl.setOnClickListener(v -> {
            if (mData.size() == 0) return;
            if (isCanSelect) {
                isCanSelect = false;
                mSelectedList.clear();
                llBottom.setVisibility(View.GONE);
            } else {
                isCanSelect = true;
                llBottom.setVisibility(View.VISIBLE);
            }
            notifyDataSetChanged();
        });
        imgGg.setOnClickListener(v -> {
            if (mData.size() == 0) return;
            if (isGrid) {
                isGrid = false;
                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                isGrid = true;
                mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));

            }
            notifyDataSetChanged();
        });
        tvSelectAll.setOnClickListener(v -> {
            if (mSelectedList.size() == mData.size()) {
                unSelect_all();
            } else {
                select_all();
            }
        });
        tvDelete.setOnClickListener(v -> {
            if (mSelectedList.size() == 0) return;
            delete(getLongIds());
        });
    }

    @Override
    public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
        if (isCanSelect) {
            isCanSelect = false;
            mSelectedList.clear();
            llBottom.setVisibility(View.GONE);
        } else {
            isCanSelect = true;
            llBottom.setVisibility(View.VISIBLE);
        }
        notifyDataSetChanged();
        return false;
    }

    private void updateMenuView() {
        tvNum.setText(("共" + mData.size() + "本"));
        if (mData.size() == 0) {
            imgPl.setImageResource(R.mipmap.ico_pl_unavailable);
            if (isGrid) {
                imgGg.setImageResource(R.mipmap.ico_liebiao_unavailable);
            } else {
                imgGg.setImageResource(R.mipmap.ico_9gg_unavailable);
            }
            llBottom.setVisibility(View.GONE);
        } else {
            if (isCanSelect) {
                imgPl.setImageResource(R.mipmap.ico_pl2);
            } else {
                imgPl.setImageResource(R.mipmap.ico_pl);
            }
            if (isGrid) {
                imgGg.setImageResource(R.mipmap.ico_lb);
            } else {
                imgGg.setImageResource(R.mipmap.ico_9gg);
            }
        }
        if (mSelectedList.size() != mData.size()) {
            tvSelectAll.setText("全选");
        } else {
            tvSelectAll.setText("取消");
        }
        if (mSelectedList.size() != 0) {
            tvDelete.setText(("删除（" + mSelectedList.size() + "）"));
            tvDelete.setTextColor(context.getResources().getColor(R.color.colorGray_9));
        } else {
            tvDelete.setText("删除");
            tvDelete.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        }
    }

    protected void unSelect_all() {
        mSelectedList.clear();
        notifyDataSetChanged();
    }

    protected void select_all() {
        mSelectedList.clear();
        mSelectedList.addAll(mData);
        notifyDataSetChanged();
    }

    protected void select(T t) {
        mSelectedList.add(t);
        notifyDataSetChanged();
    }

    protected void unSelect(T t) {
        mSelectedList.remove(t);
        notifyDataSetChanged();
    }

    protected boolean isSelect(T t) {
        return mSelectedList.contains(t);
    }

    private Long[] getLongIds() {
        final int n = mSelectedList.size();
        Long[] ids = new Long[n];
        for (int i = 0; i < n; i++) {
            ids[i] = getLongId(mSelectedList.get(i));
        }
        return ids;
    }

    protected abstract void delete(Long[] ids);

    protected abstract Long getLongId(T t);

    @Override
    protected void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        updateMenuView();
    }

    public void setFindData(List<T> list) {
        if (list == null) return;
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteSuccess() {
        mData.removeAll(mSelectedList);
        mSelectedList.clear();
        notifyDataSetChanged();
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer2, null);
        tvEnd = mFooter.findViewById(R.id.tv_end);
        tvEnd.setText("-- 没有了哦 --");
        mFooter.setBackgroundResource(R.color.colorWhite);
        mFooter.setVisibility(View.GONE);
    }

    @SuppressLint("InflateParams")
    @Override
    protected void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_empty, null);
        tvEmpty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        if (mData.size() == 0) return;
        if (isGrid) {
            mFooter.setVisibility(View.GONE);
        } else {
            mFooter.setVisibility(View.VISIBLE);
        }
    }
}
