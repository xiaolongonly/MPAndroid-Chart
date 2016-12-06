package cn.xiaolongonly.mpchartsample.chart.item;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

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
 * @date 2016/10/27-14:00
 */
public class RadarChartItem extends BaseChartItem {
    RadarChart mRadarChart;

    public RadarChartItem(ChartData cd, Context context, IAxisValueFormatter iAxisValueFormatter) {
        super(cd, context, iAxisValueFormatter);
    }

    @Override
    public int getItemType() {
        return TYPE_RADARCHART;
    }

    @Override
    public View getView(View convertView) {
        ViewHolder holder = null;

        if (convertView == null) {

            holder = new ViewHolder();

            convertView = LayoutInflater.from(mContext).inflate(
                    R.layout.list_item_radarchart, null);
            holder.chart = (RadarChart) convertView.findViewById(R.id.chart);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        // apply styling
        mRadarChart = (RadarChart) holder.chart;
        setConfig();
        return convertView;
    }

    private void setConfig() {

        mRadarChart.setDescription(null);
        mRadarChart.setWebLineWidth(1f);
        mRadarChart.setWebColor(Color.LTGRAY);
        mRadarChart.setWebLineWidthInner(1f);
        mRadarChart.setWebColorInner(Color.LTGRAY);
        mRadarChart.setWebAlpha(100);
        mRadarChart.setDrawWeb(true);
        mRadarChart.getLegend().setEnabled(false);
        DataMarkView dataMarkView = new DataMarkView(mContext, 2, "");
        mRadarChart.setMarkerView(dataMarkView);

        XAxis xAxis = mRadarChart.getXAxis();
        xAxis.setTextSize(9f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setTextColor(mContext.getResources().getColor(R.color.black_color_999999));
        xAxis.setTextSize(11f);
        xAxis.setValueFormatter(mIAxisValueFormatter);
        mRadarChart.setData((RadarData) mChartData);
        YAxis yAxis = mRadarChart.getYAxis();
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
//        yAxis.setAxisMaximum(120f);
        yAxis.setDrawLabels(false);
    }

    public static class Builder {

        private String describle = "";
        private List<ChartValue> charValueList = new ArrayList<>();
        private Context context;

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

        public RadarChartItem build() {
            ArrayList<RadarEntry> entries = new ArrayList<RadarEntry>();
            final ArrayList<String> labels = new ArrayList<>();
            for (int i = 0; i < charValueList.size(); i++) {
                entries.add(new RadarEntry((float) charValueList.get(i).yVal));
                labels.add(charValueList.get(i).xVal);
            }
            IAxisValueFormatter iAxisValueFormatter = new IAxisValueFormatter() {
                @Override
                public String getFormattedValue(float value, AxisBase axis) {
                    return labels.get((int) value % labels.size());
                }
            };

            int color = ColorTemplate.PIE_COLORS[0];
            RadarDataSet d = new RadarDataSet(entries, describle);
            // Y数据颜色设置
            d.setColor(context.getResources().getColor(R.color.black_color_999999));
            // 是否实心填充区域
            d.setDrawFilled(true);
            // 数据线条宽度
            d.setLineWidth(2.0f);
            d.setDrawValues(true);
            d.setValueTextSize(11f);
            d.setFillAlpha(92);
            d.setFillColor(color); //填充色
            d.setHighLightColor(color); //选中十字线色
            d.setColor(color); //线条颜色
            RadarData pieData = new RadarData(d);
            RadarChartItem pieChartItem = new RadarChartItem(pieData, context, iAxisValueFormatter);
            return pieChartItem;
        }
    }
}
