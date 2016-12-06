package cn.xiaolongonly.mpchartsample.chart.item;

import android.content.Context;
import android.view.View;

import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import cn.xiaolongonly.mpchartsample.R;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/9/22-11:08
 */
public abstract class BaseChartItem {

    protected static final int TYPE_BARCHART = 0;
    protected static final int TYPE_LINECHART = 1;
    protected static final int TYPE_PIECHART = 2;
    protected static final int TYPE_HORIZONTALBARCHART = 3;
    protected static final int TYPE_RADARCHART = 4;
    protected ChartData mChartData;
    protected Context mContext;
    protected IAxisValueFormatter mIAxisValueFormatter;

    public BaseChartItem(ChartData cd, Context context, IAxisValueFormatter iAxisValueFormatter) {
        this.mChartData = cd;
        this.mContext = context;
        mIAxisValueFormatter = iAxisValueFormatter;
    }


    public Chart<?> getChartView(View convertView) {
        if (convertView.getTag() != null) {
            return ((ViewHolder) convertView.getTag()).chart;
        } else {
            return (Chart) convertView.findViewById(R.id.chart);
        }
    }

    //图表类型
    public abstract int getItemType();

    /**
     * 获取图表View对象
     *
     * @param convertView
     * @return
     */
    public abstract View getView(View convertView);

    protected static class ViewHolder {
        Chart chart;
    }
}
