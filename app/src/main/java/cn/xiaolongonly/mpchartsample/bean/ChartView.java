package cn.xiaolongonly.mpchartsample.bean;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/12/5-15:39
 */
public class ChartView {
    public String title;
    public Class clazz;

    public ChartView(String title, Class clazz) {
        this.clazz = clazz;
        this.title = title;
    }
}
