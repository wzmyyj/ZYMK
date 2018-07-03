package top.wzmyyj.zymk.view.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import butterknife.BindView;
import top.wzmyyj.wzm_sdk.inter.IVD;
import top.wzmyyj.wzm_sdk.inter.SingleIVD;
import top.wzmyyj.wzm_sdk.panel.RecyclerPanel;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.BookBean;
import top.wzmyyj.zymk.presenter.HomePresenter;
import top.wzmyyj.zymk.view.IF_1View;

/**
 * Created by wzm on 2018/06/28. email: 2209011667@qq.com
 */

public class F_1 extends BaseFragment<HomePresenter> implements IF_1View {


    @Override
    protected void initPresenter() {
        mPresenter = new HomePresenter(activity, this);
    }

    @Override
    protected void initPanelList() {
        super.initPanelList();
        mPanelList.add(getRecyclerPanel());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_1;
    }


    @Override
    public void showToast(String t) {

    }

    @BindView(R.id.ll_1)
    LinearLayout layout;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_1)
    ImageView img_1;
    @BindView(R.id.img_2)
    ImageView img_2;

    @Override
    protected void initView(View view) {
        super.initView(view);
        mToolbar.setTitle("Home");
        layout.addView(mPanelList.get(0).getView());
    }


    private RecyclerPanel getRecyclerPanel() {
        return new RecyclerPanel<BookBean>(getContext()) {
            @Override
            protected void setData() {
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());
                mData.add(new BookBean());

            }

            @Override
            protected void setIVD(List<IVD<BookBean>> ivd) {
                ivd.add(new SingleIVD<BookBean>() {
                    @Override
                    public int getItemViewLayoutId() {
                        return R.layout.layout_book;
                    }

                    @Override
                    public void convert(ViewHolder holder, BookBean bookBean, int position) {

                    }
                });
            }

            @Override
            protected void setView(RecyclerView rv, SwipeRefreshLayout srl, FrameLayout layout) {

            }

            @Override
            protected View getHeader() {
                return null;
            }

            @Override
            protected View getFooter() {
                return null;
            }


        };
    }


}
