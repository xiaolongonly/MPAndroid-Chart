package cn.xiaolongonly.mpchartsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

/**
 * 通用适配器基类
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected LayoutInflater mInflater;
    protected Context mContext;
    protected T[] mDatas;

    public CommonAdapter(Context context, T[] mDatas) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {
        return mDatas.length;
    }

    @Override
    public Object getItem(int position) {
        return mDatas[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

}
