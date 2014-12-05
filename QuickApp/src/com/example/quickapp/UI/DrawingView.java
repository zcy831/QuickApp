package com.example.quickapp.UI;

import com.example.quickapp.model.DrawingModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

public class DrawingView extends View{	
	public final int WIDTH = 2;
	private int viewWidth;
	private int viewHeight;
	public int left;
	public int top;
	DrawingModel model;
	private Paint paint;
	private Path margin;
	public Path path;
	public boolean press;
	public long timestamp;
	
	public DrawingView(Context context, DrawingModel model) {
		super(context);
		this.model = model;
		this.paint = new Paint();
		left = WIDTH;
		top = WIDTH;
		WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		viewWidth = display.getWidth();
		viewHeight = top+WIDTH*model.N+50;
		this.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, viewHeight));
		
		paint = new Paint();
		paint.setColor(Color.BLACK);
		paint.setStrokeWidth(WIDTH);
		paint.setAntiAlias(true);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeJoin(Paint.Join.ROUND);
		paint.setStrokeCap(Paint.Cap.ROUND);
		margin = new Path();
		margin.moveTo(0, 0); margin.lineTo(left+WIDTH*model.N, 0); margin.lineTo(left+WIDTH*model.N, top+WIDTH*model.N);
		margin.lineTo(0, WIDTH*model.N); margin.lineTo(0, 0);
		path = new Path();
		press = false;
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawPath(margin, paint);
		canvas.drawPath(path, paint);
	}
}
