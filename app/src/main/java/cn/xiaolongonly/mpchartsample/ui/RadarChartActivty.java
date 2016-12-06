package cn.xiaolongonly.mpchartsample.ui;

import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.base.BaseTitleActivity;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.item.RadarChartItem;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/6-14:54
 */
public class RadarChartActivty extends BaseTitleActivity {
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
        RadarChartItem lineChartItem = new RadarChartItem.Builder(this)
                .setDescribles("项目支出金额")
                .setChartValueList(chartValues)
                .build();
        View chartView = lineChartItem.getView(null);
        chartView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        rlContent.addView(chartView);
    }

    @Override
    protected void setListener() {

    }
}
