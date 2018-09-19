package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import top.wzmyyj.wzm_sdk.adapter.ivd.IVD;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.DownloadBean;
import top.wzmyyj.zymk.contract.FindContract;


/**
 * Created by yyj on 2018/08/01. email: 2209011667@qq.com
 */

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
                return !isGongge;// 列表形式。
            }

            @Override
            public void convert(ViewHolder holder, DownloadBean downloadBean, int position) {

                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_new = holder.getView(R.id.tv_new);
                TextView tv_chapter = holder.getView(R.id.tv_chapter);
                TextView tv_title = holder.getView(R.id.tv_title);



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
                return isGongge;// 表格形式。
            }

            @Override
            public void convert(ViewHolder holder, DownloadBean downloadBean, int position) {
                ImageView img_book = holder.getView(R.id.img_book);
                TextView tv_new = holder.getView(R.id.tv_new);
                TextView tv_title = holder.getView(R.id.tv_title);
                TextView tv_some = holder.getView(R.id.tv_some);



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
        } else {
//            mPresenter.goDetails(mData.get(position).getBook().getHref());
        }
    }

    @Override
    public void update() {
        mPresenter.loadDownload();
    }

    @Override
    protected void sort() {
        super.sort();
        Collections.sort(mData, new Comparator<DownloadBean>() {
            @Override
            public int compare(DownloadBean o1, DownloadBean o2) {
                if (o1.getTime() < o2.getTime()) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    @Override
    protected Long getLongId(DownloadBean downloadBean) {
        return (long) downloadBean.getBook().getId();
    }

    @Override
    protected void delete(Long[] ids) {
        mPresenter.deleteSomeDownload(ids);
    }

    @Override
    protected void initView() {
        super.initView();
        tv_empty.setText("暂不支持离线缓存！");
    }
}
