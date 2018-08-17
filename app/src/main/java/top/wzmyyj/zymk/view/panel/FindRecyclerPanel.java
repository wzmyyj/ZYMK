package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.presenter.FindPresenter;
import top.wzmyyj.zymk.view.panel.base.BaseRecyclerPanel;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

public abstract class FindRecyclerPanel<T> extends BaseRecyclerPanel<T, FindPresenter> {
    public FindRecyclerPanel(Context context, FindPresenter p) {
        super(context, p);
    }

    @Override
    protected void setData() {

    }


    protected View mMenu;
    protected View mEmpty;

    @Override
    protected void initView() {
        super.initView();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        setEmpty();
        setMenu();
        mFrameLayout.addView(mEmpty);
        mFrameLayout.addView(mMenu);
    }

    protected TextView tv_empty;

    private void setEmpty() {
        mEmpty = mInflater.inflate(R.layout.layout_find_empty, null);
        tv_empty = mEmpty.findViewById(R.id.tv_empty_text);
        mEmpty.setVisibility(View.GONE);
    }


    protected TextView tv_select_all;
    protected TextView tv_delete;

    protected void setMenu() {
        mMenu = mInflater.inflate(R.layout.layout_find_menu, null);
        tv_select_all = mMenu.findViewById(R.id.tv_select_all);
        tv_delete = mMenu.findViewById(R.id.tv_delete);
        mMenu.setVisibility(View.GONE);
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

    protected void sort() {

    }

    @Override
    protected void notifyDataSetChanged() {
        sort();
        super.notifyDataSetChanged();
        updateMenuView();
    }


    protected boolean isCanSelect;


    @Override
    public Object f(int w, Object... objects) {
        switch (w) {
            case 1:
                List<T> list = (List<T>) objects[0];
                if (list == null) return null;
                loadData(list);
                break;
            case 2:
                deleteSuccess();
                break;
        }
        return super.f(w, objects);
    }

    private void loadData(List<T> list) {
        mData.clear();
        mData.addAll(list);
        notifyDataSetChanged();
    }

    private void deleteSuccess() {
        mData.removeAll(mSelectedList);
        mSelectedList.clear();
        notifyDataSetChanged();
    }


    protected TextView tv_num;
    protected ImageView img_piliang;

    @Override
    protected void setHeader() {
        super.setHeader();
        mHeader = mInflater.inflate(R.layout.layout_find_header, null);
        tv_num = mHeader.findViewById(R.id.tv_favor_num);
        img_piliang = mHeader.findViewById(R.id.img_favor_piliang);
        img_piliang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.size() == 0) return;
                if (isCanSelect) {
                    isCanSelect = false;
                    mSelectedList.clear();
                    mMenu.setVisibility(View.GONE);
                    notifyDataSetChanged();
                } else {
                    isCanSelect = true;
                    mMenu.setVisibility(View.VISIBLE);
                    notifyDataSetChanged();
                }
            }
        });
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

    @Override
    protected void upHeaderAndFooter() {
        super.upHeaderAndFooter();
        tv_num.setText("共" + mData.size() + "本");
        if (mData.size() == 0) {
            img_piliang.setImageResource(R.mipmap.ico_piliang_unavailable);
            mFooter.setVisibility(View.GONE);
            mMenu.setVisibility(View.GONE);
            mEmpty.setVisibility(View.VISIBLE);
        } else {
            if (isCanSelect) {
                img_piliang.setImageResource(R.mipmap.ico_piliang2);
            } else {
                img_piliang.setImageResource(R.mipmap.ico_piliang);
            }
            mFooter.setVisibility(View.VISIBLE);
            mEmpty.setVisibility(View.GONE);
        }
    }
}
