package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.common.utils.DensityUtil;
import top.wzmyyj.zymk.contract.FindContract;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public abstract class FindRecyclerPanel<T> extends BaseRecyclerPanel<T, FindContract.IPresenter> {
    public FindRecyclerPanel(Context context, FindContract.IPresenter p) {
        super(context, p);
    }


    protected boolean isGongge = false;

    protected View mMenu;

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mRefreshLayout.getLayoutParams();
        params.topMargin = DensityUtil.dp2px(context, 35);
        setMenu();
        mFrameLayout.addView(mMenu);
    }


    protected TextView tv_num;
    protected ImageView img_piliang;
    protected ImageView img_gongge;

    protected LinearLayout ll_bottom;
    protected TextView tv_select_all;
    protected TextView tv_delete;


    protected void setMenu() {
        mMenu = mInflater.inflate(R.layout.layout_find_menu, null);
        tv_num = mMenu.findViewById(R.id.tv_favor_num);
        img_piliang = mMenu.findViewById(R.id.img_favor_piliang);
        img_gongge = mMenu.findViewById(R.id.img_favor_gongge);
        ll_bottom = mMenu.findViewById(R.id.ll_bottom);
        tv_select_all = mMenu.findViewById(R.id.tv_select_all);
        tv_delete = mMenu.findViewById(R.id.tv_delete);
        ll_bottom.setVisibility(View.GONE);

        img_piliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) return;
                if (isCanSelect) {
                    isCanSelect = false;
                    mSelectedList.clear();
                    ll_bottom.setVisibility(View.GONE);
                    notifyDataSetChanged();
                } else {
                    isCanSelect = true;
                    ll_bottom.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                }
            }
        });
        img_gongge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) return;
                if (isGongge) {
                    isGongge = false;
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                    notifyDataSetChanged();
                } else {
                    isGongge = true;
                    mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
                    notifyDataSetChanged();

                }
            }
        });
        tv_select_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedList.size() == mData.size()) {
                    unSelect_all();
                } else {
                    select_all();
                }
            }
        });
        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mSelectedList.size() == 0) return;
                delete(getLongIds());
            }
        });
    }

    private void updateMenuView() {
        tv_num.setText("共" + mData.size() + "本");
        if (mData.size() == 0) {
            img_piliang.setImageResource(R.mipmap.ico_piliang_unavailable);

            if (isGongge) {
                img_gongge.setImageResource(R.mipmap.ico_liebiao_unavailable);
            } else {
                img_gongge.setImageResource(R.mipmap.ico_jiugongge_unavailable);
            }
            ll_bottom.setVisibility(View.GONE);
        } else {
            if (isCanSelect) {
                img_piliang.setImageResource(R.mipmap.ico_piliang2);
            } else {
                img_piliang.setImageResource(R.mipmap.ico_piliang);
            }
            if (isGongge) {
                img_gongge.setImageResource(R.mipmap.ico_liebiao);
            } else {
                img_gongge.setImageResource(R.mipmap.ico_jiugongge);
            }
        }


        if (mSelectedList.size() != mData.size()) {
            tv_select_all.setText("全选");
        } else {
            tv_select_all.setText("取消");
        }
        if (mSelectedList.size() != 0) {
            tv_delete.setText("删除（" + mSelectedList.size() + "）");
            tv_delete.setTextColor(context.getResources().getColor(R.color.colorGray_9));
        } else {
            tv_delete.setText("删除");
            tv_delete.setTextColor(context.getResources().getColor(R.color.colorGray_b));
        }
    }

    protected List<T> mSelectedList = new ArrayList<>();

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


    protected boolean isCanSelect;


    public void setFindData(List<T> list) {
        if(list==null)return;
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    public void deleteSuccess() {
        mData.removeAll(mSelectedList);
        mSelectedList.clear();
        notifyDataSetChanged();
    }

    protected TextView tv_end;

    @Override
    protected void setFooter() {
        super.setFooter();
        mFooter = mInflater.inflate(R.layout.layout_footer2, null);
        tv_end = mFooter.findViewById(R.id.tv_end);
        tv_end.setText("-- 没有了哦 --");
        mFooter.setVisibility(View.GONE);
    }


    protected TextView tv_empty;

    @Override
    protected void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_empty, null);
        tv_empty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
    }

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        if (mData.size() == 0) return;
        if (isGongge) {
            mFooter.setVisibility(View.GONE);
        } else {
            mFooter.setVisibility(View.VISIBLE);
        }
    }
}
