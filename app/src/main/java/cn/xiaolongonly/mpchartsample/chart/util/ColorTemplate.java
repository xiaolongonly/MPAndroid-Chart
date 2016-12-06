package cn.xiaolongonly.mpchartsample.chart.util;

import android.content.res.Resources;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xiaolong
 * @version v1.0
 * @function <描述功能>
 * @date 2016/11/7-10:36
 */
public class ColorTemplate {


    public static final int[] PIE_COLORS = {
            rgb("#21b2ff"),
            rgb("#10cdde"),
            rgb("#f4c82d"),
            rgb("#ff64c3"),
            rgb("#cd8cff"),
            rgb("#8551ee"),
            rgb("#2d69f2"),
            rgb("#03b46f"),
            rgb("#93c50b"),
            rgb("#d89743")
    };
    public static final int[] APIE_COLORS = {
            argb("#3321b2ff"),
            argb("#3310cdde"),
            argb("#33f4c82d"),
            argb("#33ff64c3"),
            argb("#33cd8cff"),
            argb("#338551ee"),
            argb("#332d69f2"),
            argb("#3303b46f"),
            argb("#3393c50b"),
            argb("#33d89743")
    };

    /**
     * Converts the given hex-color-string to rgb.
     *
     * @param hex
     * @return
     */
    public static int rgb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.rgb(r, g, b);
    }

    /**
     * Converts the given hex-color-string to rgb.
     *
     * @param hex
     * @return
     */
    public static int argb(String hex) {
        int color = (int) Long.parseLong(hex.replace("#", ""), 16);
        int a = (color >> 24) & 0xFF;
        int r = (color >> 16) & 0xFF;
        int g = (color >> 8) & 0xFF;
        int b = (color >> 0) & 0xFF;
        return Color.argb(a, r, g, b);
    }

    /**
     * Returns the Android ICS holo blue light color.
     *
     * @return
     */
    public static int getHoloBlue() {
        return Color.rgb(51, 181, 229);
    }

    public static int getColorWithAlphaComponent(int color, int alpha) {
        return (color & 0xffffff) | ((alpha & 0xff) << 24);
    }

    /**
     * turn an array of resource-colors (contains resource-id integers) into an
     * array list of actual color integers
     *
     * @param r
     * @param colors an integer array of resource id's of colors
     * @return
     */
    public static List<Integer> createColors(Resources r, int[] colors) {

        List<Integer> result = new ArrayList<Integer>();

        for (int i : colors) {
            result.add(r.getColor(i));
        }

        return result;
    }

    /**
     * Turns an array of colors (integer color values) into an ArrayList of
     * colors.
     *
     * @param colors
     * @return
     */
    public static List<Integer> createColors(int[] colors) {

        List<Integer> result = new ArrayList<Integer>();

        for (int i : colors) {
            result.add(i);
        }

        return result;
    }
}
