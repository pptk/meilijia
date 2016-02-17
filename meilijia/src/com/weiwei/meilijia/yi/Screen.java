package com.weiwei.meilijia.yi;

import android.app.Activity;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
/**
 * window width and height
 * @author pxh
 *
 */
public class Screen {

    private int width;
    private int height;
    public Screen(Activity context) {
        DisplayMetrics metrics = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metrics );
        width = metrics.widthPixels;
        height = metrics.heightPixels;
    }
    /**
     * @return ��Ļ��� in pixel
     */
    public int getWidth() {
        return width;
    }
    /**
     *
     * @return ��Ļ�߶� in pixel
     */
    public int getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Screen{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }
}