package com.example.quickapp.controller;

import java.util.Calendar;

import com.example.quickapp.UI.DrawingView;
import com.example.quickapp.UI.ShakeDetector;
import com.example.quickapp.UI.ShakeDetector.OnShakeListener;
import com.example.quickapp.model.DrawingModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class DrawingActivity extends Activity {
	private SensorManager mSensorManager;
	private Sensor mAccelerometer;
	private ShakeDetector mShakeDetector;
	
	private DrawingModel drawingModel;
	private DrawingView dv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		drawingModel = new DrawingModel();
		dv = new DrawingView(this, drawingModel);
		dv.setOnTouchListener(new View.OnTouchListener() {
			int n = drawingModel.N;
			int width = dv.WIDTH;
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				long now = Calendar.getInstance().getTimeInMillis();
				if(dv.press&&dv.timestamp+3000<now){
					dv.press = false;
					dv.path.reset();
					drawingModel.clear();
					dv.postInvalidate();
					return true;
				}
				int s = ((int)event.getX()-dv.left)/width;
				int t = ((int)event.getY()-dv.top)/width;
				int action = event.getAction();
				if(action==MotionEvent.ACTION_DOWN){
					if(s>0&&s<n&&t>=0&&t<n){
						if(!dv.press){
							dv.press = true;
							dv.timestamp = now;
						}
						dv.path.moveTo(event.getX(), event.getY());
						drawingModel.set(s, t);
					}
				}
				if(action==MotionEvent.ACTION_MOVE){
					if(s>=0&&s<n&&t>=0&&t<n){
						if(!dv.press){
							dv.press = true;
							dv.timestamp = now;
						}
						if(dv.path.isEmpty()) dv.path.moveTo(event.getX(), event.getY());
						else dv.path.lineTo(event.getX(), event.getY());
						drawingModel.set(s, t);
					}
				}
				dv.postInvalidate();
				return true;
			}
		});
		
		LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		Button btn = new Button(this);
		btn.setText("Go!");
		btn.setLayoutParams(params);
		btn.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent i = new Intent(DrawingActivity.this, GridActivity.class);
				finish();
				startActivity(i);
			}
		});
		LinearLayout layout = new LinearLayout(this);
		layout.setOrientation(LinearLayout.VERTICAL);
		layout.setGravity(Gravity.CENTER);
		layout.addView(dv);
		layout.addView(btn);
		LayoutParams layoutParam = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		this.addContentView(layout, layoutParam);
		
		//set ShakeDetector
		mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
		mShakeDetector = new ShakeDetector();
		mShakeDetector.setOnShakeListener(new OnShakeListener(){
			@Override
			public void onShake(int count) {
				finish();
			}
		});
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		mSensorManager.registerListener(mShakeDetector, mAccelerometer, SensorManager.SENSOR_DELAY_UI);
	}
	
	@Override
	protected void onPause() {
		mSensorManager.unregisterListener(mShakeDetector);
		super.onPause();
	}
}
