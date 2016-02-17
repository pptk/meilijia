package com.weiwei.meilijia.base;

import android.graphics.ColorMatrixColorFilter;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;

/**
 * 按钮点击变化
 * 
 * @author huyong
 * 
 */
public class Snippet {
	/**
	 * 按下这个按钮进行的颜色过�?
	 */
	public final static float[] BT_SELECTED = new float[] { 1, 0, 0, 0, -80, 0,
			1, 0, 0, -80, 0, 0, 1, 0, -80, 0, 0, 0, 1, 0 };

	/**
	 * 按钮恢复原状的颜色过�?
	 */
	public final static float[] BT_NOT_SELECTED = new float[] { 1, 0, 0, 0, 0,
			0, 1, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0 };

	/**
	 * 按钮触碰按下效果
	 */
	public final static OnTouchListener buttonOnTouchListener = new OnTouchListener() {

		int x, y;

		@SuppressWarnings("deprecation")
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			if (event.getAction() == MotionEvent.ACTION_DOWN) {
				x = (int) event.getX();
				y = (int) event.getY();

				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			} else if (event.getAction() == MotionEvent.ACTION_UP) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_NOT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
				if (Math.abs(event.getX() - x) > 5
						&& Math.abs(event.getY() - y) > 5) {
					v.getBackground().setColorFilter(
							new ColorMatrixColorFilter(BT_NOT_SELECTED));
					v.setBackgroundDrawable(v.getBackground());
				}
			}
			return false;
		}
	};

	/**
	 * 按钮触碰按下效果
	 */
	public final static OnFocusChangeListener buttonOnFocusListener = new OnFocusChangeListener() {

		@SuppressWarnings("deprecation")
		@Override
		public void onFocusChange(View v, boolean hasFocus) {
			if (hasFocus == true) {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_NOT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
			} else {
				v.getBackground().setColorFilter(
						new ColorMatrixColorFilter(BT_SELECTED));
				v.setBackgroundDrawable(v.getBackground());
				
			}
		}

	};

	/**
	 * 设置图片按钮获取焦点改变状�?
	 * 
	 * @param inImageButton
	 */
	public final static void setButtonFocusChanged(View inView) {
		inView.setOnTouchListener(buttonOnTouchListener);
		inView.setOnFocusChangeListener(buttonOnFocusListener);
	}
}
