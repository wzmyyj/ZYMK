package top.wzmyyj.zymk.view.panel;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.FansBean;
import top.wzmyyj.zymk.app.bean.ZiBean;
import top.wzmyyj.zymk.app.tools.G;
import top.wzmyyj.zymk.contract.DetailsContract;
import top.wzmyyj.zymk.view.panel.base.BasePanel;

/**
 * Created by yyj on 2018/07/19. email: 2209011667@qq.com
 */

public class DetailsZiPanel extends BasePanel<DetailsContract.IPresenter> {
    public DetailsZiPanel(Context context, DetailsContract.IPresenter p) {
        super(context, p);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.layout_details_zi;
    }

    @BindView(R.id.rv_zi_1)
    RecyclerView rv_zi_1;
    List<String> mAchList = new ArrayList<>();
    CommonAdapter mAchAdaper;

    @BindView(R.id.img_fins_1)
    ImageView img_fans_1;
    @BindView(R.id.img_fins_2)
    ImageView img_fans_2;
    @BindView(R.id.img_fins_3)
    ImageView img_fans_3;
    @BindView(R.id.tv_fans_name_1)
    TextView tv_fans_name_1;
    @BindView(R.id.tv_fans_name_2)
    TextView tv_fans_name_2;
    @BindView(R.id.tv_fans_name_3)
    TextView tv_fans_name_3;
    @BindView(R.id.tv_fans_num_1)
    TextView tv_fans_num_1;
    @BindView(R.id.tv_fans_num_2)
    TextView tv_fans_num_2;
    @BindView(R.id.tv_fans_num_3)
    TextView tv_fans_num_3;

    @BindView(R.id.rv_zi_fans)
    RecyclerView rv_zi_fans;

    List<FansBean> mFansList = new ArrayList<>();
    CommonAdapter mFansAdapter;

    @Override
    protected void initView() {
        super.initView();
        rv_zi_1.setLayoutManager(new GridLayoutManager(context, 3));
        rv_zi_1.setNestedScrollingEnabled(false);
        mAchAdaper = new CommonAdapter<String>(context, R.layout.layout_ach_item, mAchList) {

            int[] bg = new int[]{R.drawable.bg_zi_1, R.drawable.bg_zi_2,
                    R.drawable.bg_zi_3, R.drawable.bg_zi_4,
                    R.drawable.bg_zi_5, R.drawable.bg_zi_6};
            int[] color = new int[]{R.color.colorRed, R.color.colorOrange,
                    R.color.colorSky, R.color.colorGreen,
                    R.color.colorBlue, R.color.colorPurple};
            int[] icon = new int[]{R.mipmap.ic_ach_recommend, R.mipmap.ic_ach_award,
                    R.mipmap.ic_ach_month, R.mipmap.ic_ach_gradle,
                    R.mipmap.ic_ach_gift, R.mipmap.ic_ach_share};
            String[] what = new String[]{"推荐", "打赏", "月票", "打分", "收藏", "分享"};

            @Override
            protected void convert(ViewHolder holder, String s, int position) {
                LinearLayout ll_bg = holder.getView(R.id.ll_ach_bg);
                TextView tv_str = holder.getView(R.id.tv_ach_str);
                ImageView img_icon = holder.getView(R.id.img_ach_icon);
                ll_bg.setBackgroundResource(bg[position]);
                img_icon.setImageResource(icon[position]);
                tv_str.setTextColor(context.getResources().getColor(color[position]));
                tv_str.setText(what[position] + "\n" + s);

            }
        };
        rv_zi_1.setAdapter(mAchAdaper);

        rv_zi_fans.setLayoutManager(new GridLayoutManager(context, 4));
        rv_zi_fans.setNestedScrollingEnabled(false);
        mFansAdapter = new CommonAdapter<FansBean>(context, R.layout.layout_fans_item, mFansList) {
            @Override
            protected void convert(ViewHolder holder, FansBean fans, int position) {
                ImageView img_fans = holder.getView(R.id.img_fins);
                TextView tv_name = holder.getView(R.id.tv_fans_name);
                TextView tv_num = holder.getView(R.id.tv_fans_num);
                G.img(context, fans.getAvatar(), img_fans);
                tv_name.setText(fans.getName());
                tv_num.setText(fans.getNum());
            }
        };
        rv_zi_fans.setAdapter(mFansAdapter);

    }


    public void setZiData(ZiBean zi) {
        if (zi.getFansList() != null && zi.getFansList().size() > 3) {
            setFans(zi.getFansList());
        }

        if (zi.getSupport() != null && zi.getSupport().length == 6) {
            setAch(zi.getSupport());
        }
    }

    private void setAch(String[] strs) {
        mAchList.clear();
        for (int i = 0; i < strs.length; i++) {
            mAchList.add(strs[i]);
        }
        mAchAdaper.notifyDataSetChanged();

    }

    private void setFans(List<FansBean> fansList) {
        FansBean fans1 = fansList.get(0);
        G.img(context, fans1.getAvatar(), img_fans_1);
        tv_fans_name_1.setText(fans1.getName());
        tv_fans_num_1.setText(fans1.getNum());

        FansBean fans2 = fansList.get(1);
        G.img(context, fans2.getAvatar(), img_fans_2);
        tv_fans_name_2.setText(fans2.getName());
        tv_fans_num_2.setText(fans2.getNum());

        FansBean fans3 = fansList.get(2);
        G.img(context, fans3.getAvatar(), img_fans_3);
        tv_fans_name_3.setText(fans3.getName());
        tv_fans_num_3.setText(fans3.getNum());

        mFansList.clear();
        for (int i = 3; i < fansList.size(); i++) {
            mFansList.add(fansList.get(i));
        }
        mFansAdapter.notifyDataSetChanged();

    }
}
