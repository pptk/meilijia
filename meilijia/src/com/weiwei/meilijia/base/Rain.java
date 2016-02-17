package com.weiwei.meilijia.base;

import java.math.BigDecimal;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class Rain extends View {

	private int tick = 10; // 时间间隔(ms)
	private int bottom = 150; // 坐标系地段距离框架顶端的距离
	private int top = 10; // 坐标系顶端距离框架顶端框的距�?
	private int lift = 38; // 坐标系左边距离框架左边框的距�?为容�?�?
	public static int right; // 坐标系右边距离框架左边的距离(!)
	public static int gapX; // 两根竖线间的间隙(!)
	private int gapY = 20; // 两根横线间的间隙

	private static float[] rainNum = { 0.6f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f,
			0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f };
	// private static float[] rainNum = { 10, 0.3f, 8, 10, 11, 10, 11, 10, 11,
	// 12,
	// 15, 17, 18, 19, 20, 17, 16, 15, 14, 14, 11, 12, 5, 13 };// 24个雨量�?

	private String[] houres = { "00", "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23" };// �?��的时�?4H

	public Rain(Context context) {
		super(context);
	}

	public Rain(Context context, AttributeSet att) {
		super(context, att);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		if (canvas == null) {
			return;
		}

		float max = rainNum[0];
		float temMax = 0;
		float min = rainNum[0];
		float temMin = 0;
		float space = 0f;// 数组平均�?
		float spacePX = 0f;// 平均像素�?
		for (int i = 1; i < rainNum.length; i++) {
			if (max < rainNum[i]) {
				max = rainNum[i];
			}
			if (min > rainNum[i]) {
				min = rainNum[i];
			}
			temMax = max;
			temMin = min;
		}
		space = (temMax - temMin) / 7;
		spacePX = 140 / (temMax - temMin);
		Log.i("系统消息", "space = " + space + "  spacePX = " + spacePX);

		Paint mbackLinePaint = new Paint();// 用来画坐标系�?
		mbackLinePaint.setColor(Color.WHITE);
		mbackLinePaint.setAntiAlias(true);
		mbackLinePaint.setStrokeWidth(1);
		mbackLinePaint.setStyle(Style.FILL);

		Paint mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(12F);// 设置温度值的字体大小
		// 绘制坐标�?

		for (int i = 0; i < 8; i++) {
			canvas.drawLine(lift, top + gapY * i, lift + gapX * 24, top + gapY
					* i, mbackLinePaint);
			// canvas.drawText(temMin+space*i, 10, bottom-20*i, mTextPaint);
			// Math.round(((temMin + space * i) * 100) / 100.0);
			mTextPaint.setTextAlign(Align.RIGHT);
			// float result = Math.round(((temMin + space * i) * 1000) /
			// 1000.0);
			if (temMax < 0.8) {
				float result = 0.1f * i;
				canvas.drawText("" + result, lift - 2, bottom + 3 - 20 * i,
						mTextPaint);
			} else {
				float result = temMin + space * i;// 精确的各个节点的�?
				BigDecimal b = new BigDecimal(result);// 新建�?��BigDecimal
				float displayVar = b.setScale(1, BigDecimal.ROUND_HALF_UP)
						.floatValue();// 进行小数点一位保留处理现实在坐标系上的数�?
				canvas.drawText("" + displayVar, lift - 2, bottom + 3 - 20 * i,
						mTextPaint);
				Log.i("系统信息", "result = " + result);
				Log.i("系统信息", "displayVar = " + displayVar);
			}

		}

		for (int i = 0; i < 25; i++) {
			canvas.drawLine(lift + gapX * i, top, lift + gapX * i, bottom,
					mbackLinePaint);

		}
		for (int i = 0; i < rainNum.length; i++) {
			mTextPaint.setTextAlign(Align.CENTER);
			canvas.drawText(houres[i], lift + gapX * i + gapX / 2, bottom + 14,
					mTextPaint);
		}

		Paint mPointPaint = new Paint();
		mPointPaint.setAntiAlias(true);
		mPointPaint.setColor(Color.YELLOW);

		Paint mLinePaint = new Paint();// 用来画折�?
		mLinePaint.setColor(Color.YELLOW);
		mLinePaint.setAntiAlias(true);
		mLinePaint.setStrokeWidth(2);
		mLinePaint.setStyle(Style.FILL);

		float cx = 0f;
		float cy = 0f;
		float dx = 0f;
		float dy = 0f;// 当最大�?小于0.8时计算柱状体高度
		Log.i("系统消息", "right= " + right);
		Log.i("系统消息", "gapX= " + gapX);
		for (int j = 0; j < rainNum.length; j++) {
			cx = lift + gapX * j;
			cy = bottom - (rainNum[j] - temMin) * spacePX;
			dx = lift + gapX * (j + 1);
			dy = bottom - (rainNum[j] - temMin) * gapY * 10;
			if (temMax < 0.8) {
				if (rainNum[j] == 0) {
					canvas.drawRect(new RectF(cx, bottom - 2, dx - 2, bottom),
							mLinePaint);// 当雨量�?�?时，绘制2px的矩形，表示这里有�?
				} else {
					canvas.drawRect(new RectF(cx, dy, dx - 2, bottom),
							mLinePaint);
				}

			} else {
				if (rainNum[j] == 0) {
					canvas.drawRect(new RectF(cx, bottom - 2, dx - 2, bottom),
							mLinePaint);// 当雨量�?�?时，绘制2px的矩形，表示这里有�?
				} else {
					canvas.drawRect(new RectF(cx, cy, dx - 2, bottom),
							mLinePaint);
				}
			}

		}

	}
}
