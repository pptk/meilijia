package com.weiwei.meilijia.base;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.View;

public class PinChart extends View {

	static Canvas c;
	private Paint[] mPaints;
	private Paint mTextPaint;
	private RectF mBigOval;
	public float[] mSweep = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private static final float SWEEP_INC = 1;

	public static float[] humidity = { 110, 40, 50, 60, 50, 50 };

	public PinChart(Context context) {
		super(context);

	}

	public PinChart(Context context, AttributeSet atr) {
		super(context, atr);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.TRANSPARENT);// 设置背景颜色(透明)

		mPaints = new Paint[humidity.length];
		for (int i = 0; i < humidity.length; i++) {
			mPaints[i] = new Paint();
			mPaints[i].setAntiAlias(true);
			mPaints[i].setStyle(Paint.Style.FILL);
			mPaints[i].setColor(0x880FF000 + i * 0x019e8860);
		}

		mBigOval = new RectF(40, 20, 220, 200);// 饼图的四周边�?
		mTextPaint = new Paint();// 绘制文本
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(12F);// 设置温度值的字体大小
		float start = 0;
		for (int i = 0; i < humidity.length; i++) {
			canvas.drawArc(mBigOval, start, mSweep[i], true, mPaints[i]);
			start += humidity[i];
			if (mSweep[i] < humidity[i]) {
				mSweep[i] += SWEEP_INC;
			}
			canvas.drawRect(new RectF(300, 30 + 15 * i, 312, 42 + 15 * i),
					mPaints[i]);
			canvas.drawText("测试数据" + i, 320, 40 + 15 * i, mTextPaint);
		}
		invalidate();
	}

}
