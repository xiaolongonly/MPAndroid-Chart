package cn.xiaolongonly.mpchartsample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.bean.ChartView;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/5-15:46
 */
public class ChartViewAdapter extends CommonAdapter<ChartView> {
    View.OnClickListener mOnItemClickListener;

    public ChartViewAdapter(Context context, ChartView[] mDatas) {
        super(context, mDatas);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = ViewHolder.get(mContext, convertView, parent, R.layout.item_chartview, position);
        TextView tvTitle = viewHolder.getView(R.id.tvTitle);
        tvTitle.setText(mDatas[position].title);
        tvTitle.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        v.setTag(mDatas[position]);
                        mOnItemClickListener.onClick(v);
                    }
                });
        return viewHolder.getConvertView();
    }


    public void setOnItemClickListener(View.OnClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

}
