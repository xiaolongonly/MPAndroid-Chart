package cn.xiaolongonly.mpchartsample.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.chart.markview.DataMarkView;
import cn.xiaolongonly.mpchartsample.chart.util.ColorTemplate;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/6-9:10
 */
public class LineChartActivity1 extends AppCompatActivity {
    private LineChart chart;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item_linechart);
        initView();
    }

    protected void initView() {
        chart = (LineChart) findViewById(R.id.chart);

        ChartConfig();
        //XY轴配置
        XAxis xAxis = chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); //定制X轴是在图表上方还是下方。
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1);
        xAxis.setValueFormatter(new IAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, AxisBase axis) {
                return (int) value + "年";
            }
        });
        YAxis yAxisRight = chart.getAxisRight();
        yAxisRight.setEnabled(false);
        YAxis yAxisLeft = chart.getAxisLeft();
        yAxisLeft.setAxisMinimum(0);
        //动画效果
        chart.animateX(750);
        chart.animateY(750);
    }

    /**
     * 图表的配置 一些提示和Legend
     */
    private void ChartConfig() {

        //设置覆盖物
        DataMarkView dataMarkView = new DataMarkView(this, 0, "");//自定义覆盖物
        chart.setMarkerView(dataMarkView);

        //背景设置
        chart.setDrawGridBackground(false);//表格背景绘制
        chart.setBackgroundColor(getResources().getColor(R.color.chart_bg));

        //Legend定制
        chart.getLegend().setPosition(Legend.LegendPosition.ABOVE_CHART_LEFT);
        chart.getLegend().setForm(Legend.LegendForm.CIRCLE);//Legend样式

        //图表描述
        chart.setDescription(null);
        // 设置无数据文本提示
        chart.setNoDataText(getResources().getString(R.string.chart_no_data));
        //XY轴描述
        chart.setXYDesc("年份", "总金额（元)");
        //设置单方向和双方向缩放 true x,y方向可以同时控制，false只能控制x方向的缩小放大或者Y方向的缩小放大
        chart.setPinchZoom(true);
        //填充数据
        chart.setData(new LineData(generateLineDataSet()));
    }


    private ILineDataSet generateLineDataSet() {
        int color = ColorTemplate.PIE_COLORS[0];
        LineDataSet dataSet = new LineDataSet(generateData(), "年度营业额曲线");
        dataSet.setLineWidth(2.0f);
        dataSet.setCircleRadius(3.5f);
        dataSet.setDrawCircleHole(true);//填充圆
        dataSet.setValueTextSize(9f);
        dataSet.setHighlightLineWidth(2.0f);
        dataSet.setDrawFilled(true);//区域颜色
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

    private List<Entry> generateData() {
        List<Entry> entryList = new ArrayList<>();
        entryList.add(new Entry(2013, 1000));
        entryList.add(new Entry(2014, 2000));
        entryList.add(new Entry(2015, 3000));
        entryList.add(new Entry(2016, 4000));
        return entryList;
    }
}