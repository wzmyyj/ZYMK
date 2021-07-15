package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Collections;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.contract.FindContract;

/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class DownloadRecyclerPanel extends FindRecyclerPanel<DownloadBean> {

    public DownloadRecyclerPanel(Context context, FindContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected void setIVD(List<IVD<DownloadBean>> ivd) {
        ivd.add(new IVD<DownloadBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_download;
            }

            @Override
            public boolean isForViewType(DownloadBean item, int position) {
                return !isGrid;// 列表形式。
            }

            @Override
            public void convert(ViewHolder holder, DownloadBean downloadBean, int position) {
                ImageView img_select = holder.getView(R.id.img_select);
                RelativeLayout rl_select = holder.getView(R.id.rl_select);
                if (isCanSelect) {
                    rl_select.setVisibility(View.VISIBLE);
                } else {
                    rl_select.setVisibility(View.GONE);
                }
                if (isSelect(downloadBean)) {
                    img_select.setImageResource(R.mipmap.icon_mine_has_selected);
                } else {
                    img_select.setImageResource(R.mipmap.icon_mine_not_select);
                }
            }
        });
        ivd.add(new IVD<DownloadBean>() {
            @Override
            public int getItemViewLayoutId() {
                return R.layout.layout_book_find;
            }

            @Override
            public boolean isForViewType(DownloadBean item, int position) {
                return isGrid;// 表格形式。
            }

            @Override
            public void convert(ViewHolder holder, DownloadBean downloadBean, int position) {
                ImageView img_select = holder.getView(R.id.img_select);
                RelativeLayout rl_select = holder.getView(R.id.rl_select);
                if (isCanSelect) {
                    rl_select.setVisibility(View.VISIBLE);
                } else {
                    rl_select.setVisibility(View.GONE);
                }
                if (isSelect(downloadBean)) {
                    img_select.setImageResource(R.mipmap.icon_mine_has_selected);
                } else {
                    img_select.setImageResource(R.mipmap.icon_mine_not_select);
                }
            }
        });
    }

    @Override
    public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
        super.onItemClick(view, holder, position);
        if (isCanSelect) {
            if (isSelect(mData.get(position))) {
                unSelect(mData.get(position));
            } else {
                select(mData.get(position));
            }
        }
    }

    @Override
    public void update() {
        mPresenter.loadDownload();
    }

    @Override
    protected void sort() {
        super.sort();
        Collections.sort(mData, (o1, o2) -> 0);
    }

    @Override
    protected Long getLongId(DownloadBean downloadBean) {
        return 0L;
    }

    @Override
    protected void delete(Long[] ids) {
        mPresenter.deleteSomeDownload(ids);
    }

    @Override
    protected void initView() {
        super.initView();
        tvEmpty.setText("暂不支持离线缓存！");
    }
}
