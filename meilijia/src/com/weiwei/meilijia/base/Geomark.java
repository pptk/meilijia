package com.weiwei.meilijia.base;

import java.math.BigDecimal;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Geomark extends SurfaceView implements SurfaceHolder.Callback {
	private int currentX;

	private int oldX;

	private SurfaceHolder sfh;

	private boolean isRunning = true;

	private static float[] temp = { 40, 40.2f, 48, 49, 59, 42, 37,37.9f, 62, 62.6f,
			69, 78, 69, 75, 67, 73, 55, 52, 54.9f, 41, 62, 63.6f, 37.f };// 24个温度�?
	// private static float[] temp = { 29, 26, 25, 20, 20, 20, 26, 32, 36, 22,
	// 25,
	// 24, 28, 22, 20, 27, 23, 28, 29, 32, 21, 22, 24, 37 };
	private String[] houres = { "00", "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18",
			"19", "20", "21", "22", "23" };

	private int tick = 10; 
	private int bottom = 150; 
	private int top = 10; 
	private int lift = 30; 
	public static int right; 
	public static int gapX; 
	private int gapY = 20; 

	public Geomark(Context context) {
		super(context);
	}


	public Geomark(Context context, AttributeSet atr) {
		super(context, atr);

		setZOrderOnTop(true);
		sfh = this.getHolder();
		sfh.addCallback(this);
		sfh.setFormat(PixelFormat.TRANSLUCENT);
	}

	/**
	 * @see android.view.SurfaceHolder.Callback#surfaceCreated(android.view.SurfaceHolder)
	 */
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.i("系统消息", "surfaceCreated");

		
		isRunning = true;
		currentX = 0;
		clearCanvas();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				gridDraw();
				drawChartLine();
			}
		});

		thread.start();
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		Log.i("系统信息", "surfaceChanged");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		Log.i("系统信息", "surfaceDestroyed");

		
		isRunning = false;
	}

	protected void gridDraw() {
		float max = temp[0];
		float temMax = 0;
		float min = temp[0];
		float temMin = 0;
		float space = 0f;
		for (int i = 1; i < temp.length; i++) {
			if (max < temp[i]) {
				max = temp[i];
			}
			if (min > temp[i]) {
				min = temp[i];
			}
			temMax = max;
			temMin = min;
		}
		space = (temMax - temMin) / 7;

		Canvas canvas = sfh.lockCanvas();

		Paint mbackLinePaint = new Paint();
		mbackLinePaint.setColor(Color.WHITE);
		mbackLinePaint.setAntiAlias(true);
		mbackLinePaint.setStrokeWidth(1);
		mbackLinePaint.setStyle(Style.FILL);

		Paint mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(12F);
		
		for (int i = 0; i < 8; i++) {
			canvas.drawLine(lift, top + gapY * i, lift + gapX * 23, top + gapY
					* i, mbackLinePaint);
			// canvas.drawText(temMin+space*i, 10, bottom-20*i, mTextPaint);
			// Math.round(((temMin + space * i) * 100) / 100.0);
			mTextPaint.setTextAlign(Align.RIGHT);
			float result = temMin + space * i;
			BigDecimal b = new BigDecimal(result);
			float displayVar = b.setScale(1, BigDecimal.ROUND_HALF_UP)
					.floatValue();
			canvas.drawText("" + displayVar, lift - 2, bottom + 3 - 20 * i,
					mTextPaint);
		}
		for (int i = 0; i < 24; i++) {
			canvas.drawLine(lift + gapX * i, top, lift + gapX * i, bottom,
					mbackLinePaint);
			mTextPaint.setTextAlign(Align.CENTER);
			canvas.drawText(houres[i], lift + gapX * i, bottom + 14, mTextPaint);
		}

		sfh.unlockCanvasAndPost(canvas);
	}

	protected void GridDraw(Canvas canvas) {
		if (canvas == null) {
			return;
		}
		float max = temp[0];
		float temMax = 0;
		float min = temp[0];
		float temMin = 0;
		float space = 0f;
		for (int i = 1; i < temp.length; i++) {
			if (max < temp[i]) {
				max = temp[i];
			}
			if (min > temp[i]) {
				min = temp[i];
			}
			temMax = max;
			temMin = min;
		}
		space = (temMax - temMin) / 7;
		// textY=Math.round(temMin + space * i);

		Paint mbackLinePaint = new Paint();
		mbackLinePaint.setColor(Color.WHITE);
		mbackLinePaint.setAntiAlias(true);
		mbackLinePaint.setStrokeWidth(1);
		mbackLinePaint.setStyle(Style.FILL);

		Paint mTextPaint = new Paint();
		mTextPaint.setAntiAlias(true);
		// mTextPaint.setTextAlign(Align.RIGHT);
		mTextPaint.setColor(Color.WHITE);
		mTextPaint.setTextSize(12F);
		
		for (int i = 0; i < 8; i++) {
			canvas.drawLine(lift, top + gapY * i, lift + gapX * 23, top + gapY
					* i, mbackLinePaint);
			mTextPaint.setTextAlign(Align.RIGHT);
			float result = temMin + space * i;
			BigDecimal b = new BigDecimal(result);
			float displayVar = b.setScale(1, BigDecimal.ROUND_HALF_UP)
					.floatValue();
			canvas.drawText("" + displayVar, lift - 2, bottom + 3 - 20 * i,
					mTextPaint);
		}
		for (int i = 0; i < 24; i++) {
			canvas.drawLine(lift + gapX * i, top, lift + gapX * i, bottom,
					mbackLinePaint);
			mTextPaint.setTextAlign(Align.CENTER);
			canvas.drawText(houres[i], lift + gapX * i, bottom + 14, mTextPaint);
		}
	}

	private void drawChartLine() {
		while (isRunning) {
			try {
				drawChart(currentX);

				currentX++;

				if (currentX == right) {
					
					clearCanvas();
					currentX = 0;
				}

				try {
					Thread.sleep(tick);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} catch (Exception e) {

			}
		}
	}

	void drawChart(int length) {
		if (length == 0)
			oldX = 0;
		Canvas canvas = sfh.lockCanvas(new Rect(oldX, 0, oldX + length, 180));
		// Log.i("系统消息", "oldX = " + oldX + "  length = " + length);
		Paint mPointPaint = new Paint();
		mPointPaint.setAntiAlias(true);
		mPointPaint.setColor(Color.YELLOW);

		Paint mLinePaint = new Paint();
		mLinePaint.setColor(Color.YELLOW);
		mLinePaint.setAntiAlias(true);
		mLinePaint.setStrokeWidth(2);
		mLinePaint.setStyle(Style.FILL);

		float max = temp[0];
		float temMax = 0;
		float min = temp[0];
		float temMin = 0;
		float spacePX = 0f;
		for (int i = 1; i < temp.length; i++) {
			if (max < temp[i]) {
				max = temp[i];
			}
			if (min > temp[i]) {
				min = temp[i];
			}
			temMax = max;
			temMin = min;
		}
		spacePX = 140 / (temMax - temMin);

		float cx = 0f;
		float cy = 0f;
		float dx = 0f;
		float dy = 0f;
		for (int j = 0; j < temp.length - 1; j++) {
			cx = lift + gapX * j;
			cy = bottom - (temp[j] - temMin) * spacePX;
			dx = lift + gapX * (j + 1);
			dy = bottom - (temp[j + 1] - temMin) * spacePX;
			canvas.drawCircle(cx, cy, 3, mPointPaint);
			canvas.drawLine(cx, cy, dx, dy, mLinePaint);
		}

		sfh.unlockCanvasAndPost(canvas);
	}

	
	private void clearCanvas() {
		Canvas canvas = sfh.lockCanvas();

		canvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);

		GridDraw(canvas);

		sfh.unlockCanvasAndPost(canvas);
	}
}
