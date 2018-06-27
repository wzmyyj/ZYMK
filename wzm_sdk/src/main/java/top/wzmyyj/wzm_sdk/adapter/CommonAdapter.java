package top.wzmyyj.wzm_sdk.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wzm on 2018/1/19 0019.
 */

public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected List<T> mData;
    private int layoutId;

    public CommonAdapter(Context context, List<T> data, int layoutId) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public T getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = (convertView == null) ?
                new ViewHolder(mInflater, parent, layoutId) :
                (ViewHolder) convertView.getTag();
        convert(holder, getItem(position), position);
        return holder.getConvertView();
    }

    public abstract void convert(ViewHolder holder, T bean, int position);


    public class ViewHolder {

        private SparseArray<View> mViews;
        private View mConvertView;

        public ViewHolder(LayoutInflater inflater, ViewGroup parent, int layoutId) {
            this.mViews = new SparseArray<View>();
            mConvertView = inflater.inflate(layoutId, parent, false);
            mConvertView.setTag(this);
        }

        public View getConvertView() {
            return mConvertView;
        }

        public <E extends View> E getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (E) view;
        }

        public ViewHolder setText(int viewId, String s) {
            ((TextView) getView(viewId)).setText(s);
            return this;
        }

        public ViewHolder setImageResource(int viewId, int resource) {
            ((ImageView) getView(viewId)).setImageResource(resource);
            return this;
        }
    }

}
