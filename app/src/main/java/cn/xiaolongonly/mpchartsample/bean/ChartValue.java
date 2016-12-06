package cn.xiaolongonly.mpchartsample.bean;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/9/22-17:32
 */
public class ChartValue<T> {
    public String xVal;
    public T yVal;

    public ChartValue() {
    }

    public ChartValue(String xVal, T yVal) {
        this.xVal = xVal;
        this.yVal = yVal;
    }
}
