package com.example.quickapp.UI;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;


/**
 * RectangleView is a kind of View which represents the grid user may want to draw on
 * @author Group 12
 *
 */
public class TempView extends View{
	public final int NUM = 4;
	public final int WIDTH = 100;
	public final int STROKE_WIDTH = 2;
	int windowWidth;
	int windowHeight;
	public int left;
	public int top;
	GridSettingModel model;
	Paint paint;

	public TempView(Context context, GridSettingModel model) {
		super(context);
		this.model = model;
		this.paint = new Paint();
		WindowManager wm = (WindowManager) context.getSystemService(context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		windowWidth = display.getWidth();
		windowHeight = display.getHeight();
		left = (windowWidth-WIDTH*NUM)/2;
		top = (windowHeight-WIDTH*NUM)/2;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		paint.setStrokeWidth(0);
		paint.setColor(Color.BLACK);
		canvas.drawRect(left, top, left+WIDTH*NUM, top+WIDTH*NUM, paint);
		for(int j=0;j<NUM;j++)
			for(int i=0;i<NUM;i++){
				if(!model.check(j, i)){
					paint.setColor(Color.CYAN);
				}else{
					paint.setColor(Color.RED);
				}
				int x = left+WIDTH*j;
				int y = top+WIDTH*i;
				Rect r = new Rect(x+STROKE_WIDTH, y+STROKE_WIDTH, x+WIDTH-STROKE_WIDTH, y+WIDTH-STROKE_WIDTH);
				canvas.drawRect(r, paint);
			}
	}
}
