package cn.xiaolongonly.mpchartsample.ui;

import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import cn.xiaolongonly.mpchartsample.R;
import cn.xiaolongonly.mpchartsample.base.BaseTitleActivity;
import cn.xiaolongonly.mpchartsample.bean.ChartValue;
import cn.xiaolongonly.mpchartsample.chart.item.BarChartItem;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/6-14:53
 */
public class MutiBarChartActivity extends BaseTitleActivity {
    private RelativeLayout rlContent;
    private Random random;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        random = new Random();
        rlContent = findView(R.id.rlContent);
        List<List<ChartValue>> vaLists = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<ChartValue> chartValues = new ArrayList<>();
            chartValues.add(new ChartValue("9月", numRandom(100,1)));
            chartValues.add(new ChartValue("10月", numRandom(100,1)));
            chartValues.add(new ChartValue("11月", numRandom(100,1)));
            vaLists.add(chartValues);
        }

        BarChartItem lineChartItem = new BarChartItem.Builder(this, BarChartItem.Builder.TYPE_MUTI).setxDesc("单位(月)")
                .setyDesc("单位(万)").setDescribles(new String[]{"项目一", "项目二", "项目三"})
                .setCharValueLists(vaLists)
                .build();
        rlContent.addView(lineChartItem.getView());
    }

    @Override
    protected void setListener() {

    }

    public float numRandom(int range, int start) {
        return random.nextInt(range) + start;
    }
}
