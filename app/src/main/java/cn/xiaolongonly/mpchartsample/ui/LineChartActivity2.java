package cn.xiaolongonly.mpchartsample.ui;

import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.base.BaseTitleActivity;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.item.LineChartItem;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/5-17:32
 */
public class LineChartActivity2 extends BaseTitleActivity {
    private RelativeLayout rlContent;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        rlContent = findView(R.id.rlContent);
        List<ChartValue> chartValues = new ArrayList<>();
        chartValues.add(new ChartValue("11月", 110f));
        chartValues.add(new ChartValue("10月", 120f));
        chartValues.add(new ChartValue("9月", 100f));
        List<ChartValue> chartValues2 = new ArrayList<>();
        chartValues2.add(new ChartValue("11月", 155f));
        chartValues2.add(new ChartValue("10月", 133f));
        chartValues2.add(new ChartValue("9月", 122f));
        LineChartItem lineChartItem = new LineChartItem.Builder(this).setxDesc("单位(月)")
                .setyDesc("单位(万)").setDescribles(new String[]{"项目支出金额","项目回报金额"})
                .addChartValueList(chartValues).addChartValueList(chartValues2)
                .build();
        rlContent.addView(lineChartItem.getView());
    }

    @Override
    protected void setListener() {

    }

}
