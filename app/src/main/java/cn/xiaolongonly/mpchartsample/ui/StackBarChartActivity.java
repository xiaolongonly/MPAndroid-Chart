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
public class StackBarChartActivity extends BaseTitleActivity {
    private RelativeLayout rlContent;
    private Random random;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        String[] timeArray = new String[]{"9月", "10月", "11月"};
        random = new Random();
        rlContent = findView(R.id.rlContent);
        List<ChartValue> vaList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            float[] datas = new float[3];
            datas[0] = numRandom(100, 1);
            datas[1] = numRandom(100, 1);
            datas[2] = numRandom(100, 1);
            ChartValue chartValue = new ChartValue(timeArray[i], datas);
            vaList.add(chartValue);
        }

        BarChartItem lineChartItem = new BarChartItem.Builder(this, BarChartItem.Builder.TYPE_STACK).setxDesc("单位(月)")
                .setyDesc("单位(万)").setDescribles(new String[]{"项目支出金额"}).setStackLabels(new String[]{"项目一", "项目二", "项目三"})
                .setChartValueList(vaList)
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
