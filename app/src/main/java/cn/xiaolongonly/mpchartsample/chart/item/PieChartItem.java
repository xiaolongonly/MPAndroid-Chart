
package cn.xiaolongonly.mpchartsample.chart.item;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.markview.DataMarkView;
import cn.xiaolongonly.mpchartsample.chart.util.ColorTemplate;

public class PieChartItem extends BaseChartItem {
    private PieChart mPieChart;
    private boolean drawHole = true;

    public PieChartItem(ChartData cd, Context c, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, c, iAxisValueFormatter);
    }

    public PieChartItem(ChartData cd, Context c, boolean drawHole, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, c, iAxisValueFormatter);
        this.drawHole = drawHole;
    }

    @Override
    public int getItemType() {
        return TYPE_PIECHART;
    }

    @Override
    public View getView(View convertView) {

        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_piechart, null);
            holder.chart = (PieChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        mPieChart = (PieChart) holder.chart;
        setConfig();
        convertView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return convertView;
    }

    public void setConfig() {
        mPieChart.setUsePercentValues(true); //使用百分比数值
//        mPieChart.setDescription();//设置描述
//        mPieChart.setNoDataText(mContext.getResources().getString(R.string.chart_no_data));
        mPieChart.setExtraOffsets(5, 10, 5, 5);
        //旋转摩擦系数越大转越久为0会马上停止
        mPieChart.setDragDecelerationFrictionCoef(0.95f);//设置摩擦系数
        mPieChart.getLegend().setEnabled(false);//是否显示块描述
        //是否绘制中心圆
        mPieChart.setDescription(null);
        mPieChart.setData((PieData) mChartData);

        pieDataSetConfig((PieDataSet) ((PieData) mChartData).getDataSet());
        mPieChart.setExtraLeftOffset(25f);//左边距
        mPieChart.setExtraRightOffset(25f);//右边距
        DataMarkView dataMarkView = new DataMarkView(mContext, 2, "");
        mPieChart.setMarkerView(dataMarkView);
//        mPieChart.setData((PieData)mChartData.chartData);

        mPieChart.setRotationAngle(0);//旋转角度
        mPieChart.setRotationEnabled(true);//是否可旋转
        mPieChart.setHighlightPerTapEnabled(true);//高亮显示选中区域
        mPieChart.animateY(1400, Easing.EasingOption.EaseInOutQuad);//动画
        if (drawHole) {
//            mPieChart.setHoleColor(Color.TRANSPARENT);//中心圆颜色
            mPieChart.setHoleRadius(70f);//半径
//            mPieChart.setTransparentCircleColor(Color.WHITE);//透明区域颜色
            mPieChart.setTransparentCircleAlpha(110);//透明值
            mPieChart.setTransparentCircleRadius(74f);//透明区域半径
            mPieChart.setDrawHoleEnabled(true);//中心圆
        } else {
            mPieChart.setDrawHoleEnabled(false);//中心圆
        }
        Legend l = mPieChart.getLegend();
        mPieChart.setDrawSliceText(false);
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setEnabled(true);
        l.setXEntrySpace(10f);
        l.setYEntrySpace(7f);
    }

    public void pieDataSetConfig(PieDataSet dataSet) {
        dataSet.setSliceSpace(1f);//每块区域间隔
        dataSet.setSelectionShift(10f); //选中区域变大半径
        dataSet.setYValuePosition(PieDataSet.ValuePosition.INSIDE_SLICE);//显示位置
        dataSet.setXValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);//显示位置
        dataSet.setValueLinePart1OffsetPercentage(100f);//折线图中第一段起始位置相对于区域块的偏移量，数值越大，折线距离区域块越远
        dataSet.setValueLinePart1Length(0.4f);//折线中第一段长度占比
        dataSet.setValueLinePart2Length(0.2f);//折线中第二段长度最大占比
        dataSet.setValueLineColor(mContext.getResources().getColor(R.color.black_color_999999));
        dataSet.setValueLineWidth(1);//折线宽度
        dataSet.setValueTextSize(11f);
        dataSet.setDrawValues(false);
    }

    public static class Builder {

        private int[] colors = ColorTemplate.PIE_COLORS;
        private String describle = "";
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

        public Builder setDescribles(String describle) {
            this.describle = describle;
            return this;
        }

        public Builder setColorReses(int[] colorReses) {
            this.colors = new int[colorReses.length];
            for (int i = 0; i < colorReses.length; i++) {
                this.colors[i] = context.getResources().getColor(colorReses[i]);
            }
            return this;
        }


        public PieChartItem build() {
            ArrayList<PieEntry> entries = new ArrayList<PieEntry>();

            for (int i = 0; i < charValueList.size(); i++) {
                entries.add(new PieEntry((float) charValueList.get(i).yVal, charValueList.get(i).xVal));
            }
            PieDataSet d = new PieDataSet(entries, describle);
            d.setSliceSpace(2f);
            d.setColors(colors);
            PieData pieData = new PieData(d);
            PieChartItem pieChartItem = new PieChartItem(pieData, context, new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return labels.get((int) value % labels.size());
                }
            });
            return pieChartItem;
        }
    }
}
