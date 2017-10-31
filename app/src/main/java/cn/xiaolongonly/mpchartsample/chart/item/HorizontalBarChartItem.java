package cn.xiaolongonly.mpchartsample.chart.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.markview.DataMarkView;
import cn.xiaolongonly.mpchartsample.chart.util.ColorTemplate;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/10/27-14:44
 */
public class HorizontalBarChartItem extends BaseChartItem {
    private HorizontalBarChart mHorizontalBarChart;
    private String xDesc;
    private String yDesc;

    public String getxDesc() {
        return xDesc;
    }

    public void setxDesc(String xDesc) {
        this.xDesc = xDesc;
    }

    public String getyDesc() {
        return yDesc;
    }

    public void setyDesc(String yDesc) {
        this.yDesc = yDesc;
    }

    public HorizontalBarChartItem(ChartData cd, Context context, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, context, iAxisValueFormatter);
    }

    @Override
    public int getItemType() {
        return TYPE_HORIZONTALBARCHART;
    }

    @Override
    public View getView(View convertView) {
        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_horizontalbarchart, null);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.chart = (HorizontalBarChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        mHorizontalBarChart = (HorizontalBarChart) holder.chart;
        if (!xDesc.equals("") || !yDesc.equals("")) {
            mHorizontalBarChart.setXYDesc(xDesc, yDesc, 10f, mContext.getResources().getColor(R.color.normal_black_color));
        }
        setConfig();
        return convertView;
    }

    private void setConfig() {
        //图表描述设置为空
        Description description = new Description();
        description.setText("描述");
        mHorizontalBarChart.setDescription(description); //设置图表描述
        mHorizontalBarChart.setNoDataText("无数据");//空数据
        mHorizontalBarChart.setGridBackgroundColor(mContext.getResources().getColor(R.color.white)); // 表格的的颜色
        mHorizontalBarChart.setMarker(new DataMarkView(mContext, new DataMarkView.IDataValueFormat() {
            @Override
            public String format(Entry e, Highlight highlight) {
                return (int) e.getY() + "元";
            }
        }));
        XAxis xAxis = mHorizontalBarChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //定制X轴是在图表上方还是下方。
        xAxis.setAvoidFirstLastClipping(false);//定制X轴起点和终点Label不能超出屏幕。
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(mIAxisValueFormatter);
        xAxis.setGranularity(1);//放大的时候X值不增多
//        xAxis.setDrawGridLines(true);//设置是否显示横轴表格
//        xAxis.setGridColor();//设置x轴表格颜色
        YAxis yAxisRight = mHorizontalBarChart.getAxisRight();//右边Y轴
        yAxisRight.setAxisMinimum(0);//设置Y轴起始值
        yAxisRight.setDrawAxisLine(true);
        yAxisRight.setDrawGridLines(false);
        YAxis yAxisLeft = mHorizontalBarChart.getAxisLeft();//左边Y轴
        yAxisLeft.setEnabled(false);//设置是否可用
        mHorizontalBarChart.setData((BarData) mChartData);
        //将图表数据提示设置为不显示
        mHorizontalBarChart.getLegend().setEnabled(false);
        mHorizontalBarChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);//动画
    }

    public static class Builder {

        private int[] colors = ColorTemplate.PIE_COLORS;
        private String describle = "";
        private String xDesc = "";
        private String yDesc = "";
        private List<ChartValue> charValueList = new ArrayList<>();
        private Context context;
        private ArrayList<String> labels = new ArrayList<>();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setChartValueList(List<ChartValue> charValueList) {
            this.charValueList = charValueList;
            return this;
        }

        public Builder setyDesc(String yDesc) {
            this.yDesc = yDesc;
            return this;
        }

        public Builder setxDesc(String xDesc) {
            this.xDesc = xDesc;
            return this;
        }

        public Builder setDescrible(String describle) {
            this.describle = describle;
            return this;
        }

        public Builder setBarColorReses(int[] colorReses) {
            this.colors = new int[colorReses.length];
            for (int i = 0; i < colorReses.length; i++) {
                this.colors[i] = context.getResources().getColor(colorReses[i]);
            }
            return this;
        }


        public HorizontalBarChartItem build() {
            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();

            for (int i = 0; i < charValueList.size(); i++) {
                entries.add(new BarEntry(i, (float) charValueList.get(i).yVal));
                labels.add(charValueList.get(i).xVal);
            }
            BarDataSet d = new BarDataSet(entries, describle);
            d.setColors(colors);
            d.setHighLightAlpha(20);
            d.setValueTextColor(context.getResources().getColor(R.color.black_color_999999));
            d.setValueTextSize(11f);
            IAxisValueFormatter iAxisValueFormatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return labels.get((int) value % labels.size());
                }
            };
            BarData barData = new BarData(d);
            HorizontalBarChartItem horizontalBarChartItem = new HorizontalBarChartItem(barData, context, iAxisValueFormatter);
            horizontalBarChartItem.setxDesc(xDesc);
            horizontalBarChartItem.setyDesc(yDesc);
            return horizontalBarChartItem;
        }
    }
}
