package com.example.quickapp.controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.quickapp.UI.GridModel;
import com.example.quickapp.UI.RectangleView;

/**
 * This class defines the activity to show the main interface
 * when the phone is booted. It draws the 4*4 board, controls
 * the activity to unlock the phone by patterns.
 * 
 * @author Group12
 *
 */
public class GridActivity extends Activity {
	private GridModel gridModel;
	private RectangleView rv;
	
	/*@Override
	public void onAttachedToWindow() {
		
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG|WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onAttachedToWindow();
	}*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//setContentView(R.layout.activity_grid);
		
		gridModel = new GridModel();
		rv = new RectangleView(this, gridModel);
		rv.setOnTouchListener(new View.OnTouchListener() {
			int num = rv.NUM;
			int width = rv.WIDTH;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				int action = event.getAction();
				if(action==MotionEvent.ACTION_DOWN){
					int s = ((int)event.getX()-rv.left)/width;
					int t = ((int)event.getY()-rv.top)/width;
					if(s>=0&&s<num&&t>=0&&t<num){
						gridModel.add(s, t);
						rv.invalidate();
					}
				}
				if(action==MotionEvent.ACTION_MOVE){
					int s = ((int)event.getX()-rv.left)/width;
					int t = ((int)event.getY()-rv.top)/width;
					if(s>=0&&s<num&&t>=0&&t<num){
						gridModel.add(s, t);
						rv.invalidate();
						if(gridModel.isMatch()) finish();
					}else{
						gridModel.clear();
						rv.invalidate();
					}
				}
				if(action==MotionEvent.ACTION_UP){
					gridModel.clear();
					rv.invalidate();
				}
				return true;
			}
		});
		
		LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		Button btn = new Button(this);
		btn.setText("Back");
		btn.setLayoutParams(params);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(GridActivity.this, DrawingActivity.class);
				finish();
				startActivity(i);
				
			}
		});
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		layout.addView(rv);
		layout.addView(btn);
		LayoutParams layoutParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.addContentView(layout, layoutParam);
	}
}
