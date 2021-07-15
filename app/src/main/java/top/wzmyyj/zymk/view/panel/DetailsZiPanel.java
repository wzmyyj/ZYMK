package top.wzmyyj.zymk.view.panel;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.FansBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.app.helper.GlideLoaderHelper;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.base.panel.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */
@SuppressLint("NonConstantResourceId")
public class DetailsZiPanel extends BasePanel<DetailsContract.IPresenter> {

    @BindView(R.id.img_fins_1)
    ImageView imgFans1;
    @BindView(R.id.img_fins_2)
    ImageView imgFans2;
    @BindView(R.id.img_fins_3)
    ImageView imgFans3;
    @BindView(R.id.tv_fans_name_1)
    TextView tvFansName1;
    @BindView(R.id.tv_fans_name_2)
    TextView tvFansName2;
    @BindView(R.id.tv_fans_name_3)
    TextView tvFansName3;
    @BindView(R.id.tv_fans_num_1)
    TextView tvFansNum1;
    @BindView(R.id.tv_fans_num_2)
    TextView tvFansNum2;
    @BindView(R.id.tv_fans_num_3)
    TextView tvFansNum3;
    @BindView(R.id.rv_zi_1)
    RecyclerView rvZi1;
    @BindView(R.id.rv_zi_fans)
    RecyclerView rvZiFans;
    @BindView(R.id.tv_fans_empty)
    TextView tvFansEmpty;

    private final List<String> mAchList = new ArrayList<>();
    private CommonAdapter<String> mAchAdapter;
    private final List<FansBean> mFansList = new ArrayList<>();
    private CommonAdapter<FansBean> mFansAdapter;

    public DetailsZiPanel(Context context, DetailsContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_details_zi;
    }

    @Override
    protected void initView() {
        super.initView();
        rvZi1.setLayoutManager(new GridLayoutManager(context, 3));
        rvZi1.setNestedScrollingEnabled(false);
        mAchAdapter = new CommonAdapter<String>(context, R.layout.layout_ach_item, mAchList) {
            final int[] bg = new int[]{R.drawable.bg_zi_1, R.drawable.bg_zi_2,
                    R.drawable.bg_zi_3, R.drawable.bg_zi_4,
                    R.drawable.bg_zi_5, R.drawable.bg_zi_6};
            final int[] color = new int[]{R.color.colorRed, R.color.colorOrange,
                    R.color.colorSky, R.color.colorGreen,
                    R.color.colorBlue, R.color.colorPurple};
            final int[] icon = new int[]{R.mipmap.ic_ach_recommend, R.mipmap.ic_ach_award,
                    R.mipmap.ic_ach_month, R.mipmap.ic_ach_gradle,
                    R.mipmap.ic_ach_gift, R.mipmap.ic_ach_share};
            final String[] what = new String[]{"推荐", "打赏", "月票", "打分", "收藏", "分享"};

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                LinearLayout ll_bg = holder.getView(R.id.ll_ach_bg);
                TextView tv_str = holder.getView(R.id.tv_ach_str);
                ImageView img_icon = holder.getView(R.id.img_ach_icon);
                ll_bg.setBackgroundResource(bg[position]);
                img_icon.setImageResource(icon[position]);
                tv_str.setTextColor(context.getResources().getColor(color[position]));
                tv_str.setText((what[position] + "\n" + s));
            }
        };
        rvZi1.setAdapter(mAchAdapter);
        rvZiFans.setLayoutManager(new GridLayoutManager(context, 4));
        rvZiFans.setNestedScrollingEnabled(false);
        mFansAdapter = new CommonAdapter<FansBean>(context, R.layout.layout_fans_item, mFansList) {
            @Override
            protected void convert(ViewHolder holder, FansBean fans, int position) {
                ImageView img_fans = holder.getView(R.id.img_fins);
                TextView tv_name = holder.getView(R.id.tv_fans_name);
                TextView tv_num = holder.getView(R.id.tv_fans_num);
                GlideLoaderHelper.img(img_fans, fans.getAvatar());
                tv_name.setText(fans.getName());
                tv_num.setText(fans.getNum());
            }
        };
        rvZiFans.setAdapter(mFansAdapter);
    }

    public void setZiData(ZiBean zi) {
        if (zi.getFansList() != null && zi.getFansList().size() > 3) {
            setFans(zi.getFansList());
        }
        if (zi.getSupport() != null && zi.getSupport().length == 6) {
            setAch(zi.getSupport());
        }
    }

    private void setAch(String[] strings) {
        mAchList.clear();
        Collections.addAll(mAchList, strings);
        mAchAdapter.notifyDataSetChanged();
    }

    private void setFans(List<FansBean> fansList) {
        FansBean fans1 = fansList.get(0);
        GlideLoaderHelper.img(imgFans1, fans1.getAvatar());
        tvFansName1.setText(fans1.getName());
        tvFansNum1.setText(fans1.getNum());
        FansBean fans2 = fansList.get(1);
        GlideLoaderHelper.img(imgFans2, fans2.getAvatar());
        tvFansName2.setText(fans2.getName());
        tvFansNum2.setText(fans2.getNum());
        FansBean fans3 = fansList.get(2);
        GlideLoaderHelper.img(imgFans3, fans3.getAvatar());
        tvFansName3.setText(fans3.getName());
        tvFansNum3.setText(fans3.getNum());
        mFansList.clear();
        for (int i = 3; i < fansList.size(); i++) {
            mFansList.add(fansList.get(i));
        }
        mFansAdapter.notifyDataSetChanged();
        if (mFansList.size() > 0) tvFansEmpty.setVisibility(View.GONE);
    }
}
