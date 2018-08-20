package top.wzmyyj.zymk.view.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import top.wzmyyj.zymk.R;
import top.wzmyyj.zymk.app.bean.HuaBean;


/**
 * Created by yyj on 2018/07/27. email: 2209011667@qq.com
 */

public class HuaAdapter extends CommonAdapter<HuaBean> {

    public HuaAdapter(Context context, int layoutId, List<HuaBean> datas) {
        super(context, layoutId, datas);
    }

    private long read = 0;

    public void setRead(long read) {
        this.read = read;
        notifyDataSetChanged();
    }

    @Override
    protected void convert(ViewHolder holder, HuaBean huaBean, int position) {
        TextView tv_text = holder.getView(R.id.tv_hua_text);
        ImageView img_lock = holder.getView(R.id.img_hua_lock);
        ImageView img_new = holder.getView(R.id.img_hua_new);
        ImageView img_reading = holder.getView(R.id.img_hua_reading);

        tv_text.setText(huaBean.getName());
        if (huaBean.isLock()) {
            img_lock.setVisibility(View.VISIBLE);
        } else {
            img_lock.setVisibility(View.GONE);
        }

        if (huaBean.isDot()) {
            img_new.setVisibility(View.VISIBLE);
        } else {
            img_new.setVisibility(View.GONE);
        }

        if (huaBean.getId()==read) {
            img_reading.setVisibility(View.VISIBLE);
            img_new.setVisibility(View.GONE);// 防止两个标记重叠。
        } else {
            img_reading.setVisibility(View.GONE);
        }

    }
}
