package cn.xiaolongonly.mpchartsample;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;

import cn.xiaolongonly.mpchartsample.adapter.ChartViewAdapter;
import cn.xiaolongonly.mpchartsample.base.BaseTitleActivity;
import cn.xiaolongonly.mpchartsample.bean.ChartView;
import cn.xiaolongonly.mpchartsample.ui.BarChartActivity;
import cn.xiaolongonly.mpchartsample.ui.HorizontalBarChartActivity;
import cn.xiaolongonly.mpchartsample.ui.LineChartActivity1;
import cn.xiaolongonly.mpchartsample.ui.LineChartActivity2;
import cn.xiaolongonly.mpchartsample.ui.PieChartActivity;
import cn.xiaolongonly.mpchartsample.ui.RadarChartActivty;

public class MainActivity extends BaseTitleActivity {
    private ListView lvChartList;
    private ChartView[] mChartViews = new ChartView[]{
            new ChartView("线性图1", LineChartActivity1.class),
            new ChartView("线性图2", LineChartActivity2.class),
            new ChartView("柱状图", BarChartActivity.class),
            new ChartView("横向柱状图", HorizontalBarChartActivity.class),
            new ChartView("饼图", PieChartActivity.class),
            new ChartView("雷达图", RadarChartActivty.class)
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        lvChartList = findView(R.id.lvChartList);
        ChartViewAdapter chartViewAdapter = new ChartViewAdapter(this, mChartViews);
        chartViewAdapter.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ChartView chartView = (ChartView) v.getTag();
                Intent intent = new Intent(MainActivity.this, chartView.clazz);
                intent.putExtras(BaseTitleActivity.BuildBundle(chartView.title));
                startActivity(intent);
            }
        });
        lvChartList.setAdapter(chartViewAdapter);
        tvTitle.setText("Sample");
        ivBack.setVisibility(View.GONE);
    }

    @Override
    protected void setListener() {

    }


}
