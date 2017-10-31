
package cn.xiaolongonly.mpchartsample.chart.item;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.markview.DataMarkView;
import cn.xiaolongonly.mpchartsample.chart.util.ColorTemplate;

public class LineChartItem extends BaseChartItem {
    private String xDesc;
    private String yDesc;
    private DataMarkView dataMarkView;

    public LineChartItem(ChartData cd, Context c, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, c, iAxisValueFormatter);
    }

    public LineChartItem(ChartData cd, Context c, DataMarkView dataMarkView, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, c, iAxisValueFormatter);
        this.dataMarkView = dataMarkView;
    }

    @Override
    public int getItemType() {
        return TYPE_LINECHART;
    }

    public View getView() {
        return getView(null);
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
    public View getView(View convertView) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_linechart, null);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            holder.chart = (LineChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling

        LineChart lineChart = (LineChart) holder.chart;
        lineChart.setBackgroundColor(mContext.getResources().getColor(R.color.chart_bg));
//        lineChart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        lineChart.getLegend().setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        lineChart.getLegend().setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        lineChart.getLegend().setForm(Legend.LegendForm.CIRCLE);
        // 设置无数据文本提示
        lineChart.setDescription(null);
//        lineChart.setNoDataText(mContext.getResources().getString(R.string.chart_no_data));
//        lineChart.setXYDesc(xDesc, yDesc);
        if (!xDesc.equals("") || !yDesc.equals("")) {
            lineChart.setXYDesc(xDesc, yDesc, 10f, mContext.getResources().getColor(R.color.normal_black_color));
        }
        //设置单方向和双方向缩放 true x,y方向可以同时控制，false只能控制x方向的缩小放大或者Y方向的缩小放大
        lineChart.setPinchZoom(true);
        DataMarkView dataMarkView = new DataMarkView(mContext, new DataMarkView.IDataValueFormat() {
            @Override
            public String format(Entry e, Highlight highlight) {
                return (int) e.getY() + "元";
            }
        });
        lineChart.setMarker(dataMarkView);
        lineChart.setDrawGridBackground(false);
        XAxis xAxis = lineChart.getXAxis();
        //定制X轴是在图表上方还是下方。
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        //放大的时候X值不增多
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(mIAxisValueFormatter);
        if (dataMarkView != null) {
            lineChart.setMarkerView(dataMarkView);
        }
        YAxis yAxisRight = lineChart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = lineChart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        // set data
        lineChart.setData((LineData) mChartData);

        // do not forget to refresh the chart
        // holder.chart.invalidate();
        lineChart.animateX(750);
        lineChart.animateY(750);

        return convertView;

    }

    public static class Builder {
        private int[] colors = ColorTemplate.PIE_COLORS;
        private String[] describles = new String[]{""};
        private String xDesc = "";
        private String yDesc = "";
        private List<List<ChartValue>> charValueLists = new ArrayList<>();
        private Context context;
        private boolean isFillColor;
        private ArrayList<String> labels = new ArrayList<>();

        public Builder(Context context) {
            this.context = context;
        }

        public Builder addChartValueList(List<ChartValue> charValueLists) {
            this.charValueLists.add(charValueLists);
            return this;
        }

        public Builder setChartValueList(List<List<ChartValue>> charValueLists) {
            this.charValueLists = charValueLists;
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

        public Builder setColorReses(int[] colorReses) {
            this.colors = new int[colorReses.length];
            for (int i = 0; i < colors.length; i++) {
                colors[i] = context.getResources().getColor(colorReses[i]);
            }
            return this;
        }

        public Builder fillColorEnable(boolean isFillColor) {
            this.isFillColor = isFillColor;
            return this;
        }

        public LineChartItem build() {
            return build(null);
        }

        public LineChartItem build(DataMarkView dataMarkView) {
            List<ILineDataSet> lineDataSets = new ArrayList<>();
            for (int listIndexOutside = 0; listIndexOutside < charValueLists.size(); listIndexOutside++) {
                ArrayList<Entry> entries = new ArrayList<Entry>();
                for (int listIndexInside = 0; listIndexInside < charValueLists.get(listIndexOutside).size(); listIndexInside++) {
                    entries.add(new Entry((float) listIndexInside, (float) charValueLists.get(listIndexOutside).get(listIndexInside).yVal));
                    if (listIndexOutside == 0) {
                        labels.add(charValueLists.get(listIndexOutside).get(listIndexInside).xVal);
                    }
                }
                lineDataSets.add(generateLineDataSet(entries, describles[listIndexOutside], colors[listIndexOutside % colors.length]));
            }
            IAxisValueFormatter iAxisValueFormatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return labels.get((int) value % labels.size());
                }
            };
            LineData cd = new LineData(lineDataSets);
            LineChartItem lineChartItem = null;
            if (dataMarkView != null) {
                lineChartItem = new LineChartItem(cd, context, dataMarkView, iAxisValueFormatter);
            } else {
                lineChartItem = new LineChartItem(cd, context, iAxisValueFormatter);
            }
            lineChartItem.setxDesc(xDesc);
            lineChartItem.setyDesc(yDesc);
            return lineChartItem;
        }

        private ILineDataSet generateLineDataSet(ArrayList<Entry> entries, String describle, int color) {
            LineDataSet dataSet = new LineDataSet(entries, describle);
            dataSet.setLineWidth(2.0f);
            dataSet.setCircleRadius(3.5f);
            dataSet.setDrawCircleHole(true);//填充圆
            dataSet.setValueTextSize(9f);
            dataSet.setHighlightLineWidth(2.0f);
            dataSet.setDrawFilled(isFillColor);
            dataSet.setFillAlpha(51);
            dataSet.setFillColor(color); //填充色
            dataSet.setHighLightColor(color); //选中十字线色
            dataSet.setColor(color); //线条颜色
            dataSet.setCircleColor(color); //圆点颜色
            dataSet.setCircleColorHole(Color.WHITE);
            dataSet.setCircleHoleRadius(2.0f);
            dataSet.setDrawValues(false);
            return dataSet;
        }
    }

}
