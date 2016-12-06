package com.github.mikephil.charting.components;

import android.graphics.Paint;

import com.github.mikephil.charting.utils.Utils;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/10/12-14:09
 */
public class XYDesc extends ComponentBase {
    private String xDesc;
    private String yDesc;
    private float yPadding;
    public String getxDesc() {
        return xDesc;
    }

    public void setxDesc(String xDesc) {
        this.xDesc = xDesc;
    }

    public String getyDesc() {
        return yDesc;
    }

    public void setyDesc(String yDesc) {
        this.yDesc = yDesc;
    }

    public XYDesc() {
        this.setEnabled(false);
    }


    /**
     * returns the maximum length in pixels across all legend labels + formsize
     * + formtotextspace
     *
     * @param p the paint object used for rendering the text
     * @return
     */
    public float getMaximumEntryWidth(Paint p, String desc) {
        float length = (float) Utils.calcTextWidth(p, desc);
        return length;
    }

    /**
     * returns the maximum height in pixels across all legend labels
     *
     * @param p the paint object used for rendering the text
     * @return
     */
    public float getMaximumEntryHeight(Paint p, String desc) {
        float length = (float) Utils.calcTextHeight(p, desc);
        return length;
    }

    public float getyPadding() {
        return yPadding;
    }

    public void setyPadding(float yPadding) {
        this.yPadding = yPadding;
    }
}
