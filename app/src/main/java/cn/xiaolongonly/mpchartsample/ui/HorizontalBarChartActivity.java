package cn.xiaolongonly.mpchartsample.ui;

import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.base.BaseTitleActivity;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.item.HorizontalBarChartItem;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/6-14:53
 */
public class HorizontalBarChartActivity extends BaseTitleActivity {
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
        chartValues.add(new ChartValue("11月", 120f));
        chartValues.add(new ChartValue("11月", 100f));
        HorizontalBarChartItem lineChartItem = new HorizontalBarChartItem.Builder(this).setxDesc("单位(万)")
                .setyDesc("单位(月)").setDescrible("项目支出金额")
                .setChartValueList(chartValues)
                .build();
        rlContent.addView(lineChartItem.getView(null));
    }

    @Override
    protected void setListener() {

    }
}
