package cn.xiaolongonly.mpchartsample.chart.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.XAxis.XAxisPosition;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.markview.DataMarkView;
import cn.xiaolongonly.mpchartsample.chart.util.ColorTemplate;

public class BarChartItem extends BaseChartItem {
    private static final String TAG = "BarChartItem";
    private String xDesc;
    private String yDesc;

    public BarChartItem(ChartData cd, Context c, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, c, iAxisValueFormatter);
    }

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

    @Override
    public int getItemType() {
        return TYPE_BARCHART;
    }

    public View getView() {
        return getView(null);
    }

    @Override
    public View getView(View convertView) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_barchart, null);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.chart = (BarChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        DataMarkView dataMarkView = new DataMarkView(mContext, 0, "");
        BarChart barChart = (BarChart) holder.chart;
        barChart.setMarkerView(dataMarkView);
        barChart.setDescription(null);
        barChart.setNoDataText("无数据");
        barChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_CENTER);
        if (!xDesc.equals("") || !yDesc.equals("")) {
            barChart.setXYDesc(xDesc, yDesc, 10f, mContext.getResources().getColor(R.color.normal_black_color));
        }
        //设置单方向和双方向缩放 true x,y方向可以同时控制，false只能控制x方向的缩小放大或者Y方向的缩小放大
        barChart.setPinchZoom(true);
//        barChart.setGridBackgroundColor(getResources().getColor(R.color.colorTxt3)); // 表格的的颜色
        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxisPosition.BOTTOM); //定制X轴是在图表上方还是下方。
        xAxis.setDrawGridLines(false);
        xAxis.setValueFormatter(mIAxisValueFormatter);
        xAxis.setGranularity(1);//放大的时候X值不增多
        xAxis.setAxisMinimum(0);
        YAxis yAxisRight = barChart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = barChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        //图标提示信息显示位置，显示方式
        //setData
        barChart.setData((BarData) mChartData);
        barChart.animateX(1400, Easing.EasingOption.EaseInOutQuad);//动画
        return convertView;
    }

    public static class Builder {
        public final static int TYPE_NORMAL = 1;
        public final static int TYPE_MUTI = 2;
        public final static int TYPE_STACK = 3;

        private int[] colors = ColorTemplate.PIE_COLORS;
        private String[] describles = new String[]{""};
        private String xDesc = "";
        private String yDesc = "";
        private List<ChartValue> charValueList = new ArrayList<>();
        private List<List<ChartValue>> charValueLists = new ArrayList<>();
        private Context context;
        private int type = 1;
        private ArrayList<String> labels = new ArrayList<>();

        public Builder(Context context, int type) {
            this.context = context;
            this.type = type;
        }

        public Builder setChartValueList(List<ChartValue> charValueList) {
            this.charValueList = charValueList;
            return this;
        }

        public Builder addChartValueList(List<ChartValue> charValueList) {
            this.charValueLists.add(charValueList);
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

        public Builder setDescribles(String[] describles) {
            this.describles = describles;
            return this;
        }

        public Builder setBarColorReses(int[] colorReses) {
            this.colors = new int[colorReses.length];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = colorReses[i];
            }
            return this;
        }

        public Builder setType(int type) {
            this.type = type;
            return this;
        }

        public BarChartItem build() {
            ChartData chartData = null;
            switch (type) {
                case TYPE_NORMAL:
                    chartData = normalDataGenerate();
                    break;
                case TYPE_MUTI:
                    chartData = mutiDataGenerate();
                    break;
                case TYPE_STACK:
                    chartData = stackDataGenerate();
                    break;
            }
            IAxisValueFormatter iAxisValueFormatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return labels.get((int) value % labels.size());
                }
            };
            BarChartItem barChartItem = new BarChartItem(chartData, context, iAxisValueFormatter);
            barChartItem.setxDesc(xDesc);
            barChartItem.setyDesc(yDesc);
            return barChartItem;
        }


        /**
         * 普通柱子
         *
         * @return
         */
        private ChartData normalDataGenerate() {
            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
            for (int i = 0; i < charValueList.size(); i++) {
                entries.add(new BarEntry(i, (float) charValueList.get(i).yVal));
                labels.add(charValueList.get(i).xVal);
            }
            BarDataSet d = new BarDataSet(entries, describles[0]);
            d.setColors(new int[]{context.getResources().getColor(R.color.barchart_one)});
            d.setHighLightAlpha(20);
            d.setValueTextSize(11f);
            d.setValueTextColor(context.getResources().getColor(R.color.normal_black_color));
            BarData barData = new BarData(d);
            return barData;
        }

        /**
         * 有两条的柱子
         */
        private ChartData mutiDataGenerate() {
            List<IBarDataSet> barDataSets = new ArrayList<>();
            for (int listIndexOutside = 0; listIndexOutside < charValueLists.size(); listIndexOutside++) {
                ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
                for (int listIndexInside = 0; listIndexInside < charValueLists.get(listIndexOutside).size(); listIndexInside++) {
                    entries.add(new BarEntry((Float) charValueLists.get(listIndexOutside).get(listIndexInside).yVal, listIndexInside));
                    if (listIndexOutside == 0) {
                        labels.add(charValueLists.get(listIndexOutside).get(listIndexInside).xVal);
                    }
                }
                barDataSets.add(generateBarDataSet(entries, describles[listIndexOutside], colors[listIndexOutside % colors.length]));
            }
            BarData cd = new BarData(barDataSets);
            cd.setValueFormatter(new LargeValueFormatter());
            return cd;
        }

        private IBarDataSet generateBarDataSet(ArrayList<BarEntry> entries, String describle, int color) {
            BarDataSet dataSet = new BarDataSet(entries, describle);
            // set1.setColors(ColorTemplate.createColors(getApplicationContext(),
            // ColorTemplate.FRESH_COLORS));
            dataSet.setColor(color);
            dataSet.setValueTextSize(11f);
            dataSet.setHighLightColor(context.getResources().getColor(R.color.bar_4CFFFFFF));
            return dataSet;
        }

        /**
         * stakBar
         */
        private ChartData stackDataGenerate() {
            ArrayList<BarEntry> entries = new ArrayList<BarEntry>();
            for (int i = 0; i < charValueList.size(); i++) {
                float[] yValList = (float[]) charValueList.get(i).yVal;
                entries.add(new BarEntry(i, yValList));
                labels.add(charValueList.get(i).xVal);
            }
            BarDataSet d = new BarDataSet(entries, describles[0]);
            d.setHighLightAlpha(20);
            d.setValueTextSize(11f);
            d.setColors(colors);
            d.setHighLightAlpha(20);
            d.setValueTextColor(context.getResources().getColor(R.color.normal_black_color));
            BarData barData = new BarData(d);
            return barData;
        }
    }

}
