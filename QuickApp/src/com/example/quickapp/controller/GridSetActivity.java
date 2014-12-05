package com.example.quickapp.controller;

import com.example.quickapp.UI.GridSettingModel;
import com.example.quickapp.UI.TempView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * This is the Activity of the first step of setup part. For now user will draw a pattern on the screen 
 * and the pattern will be saved for matching an action.
 * @author Group 12
 *
 */

public class GridSetActivity extends Activity {
	/**
	 * The model for the grid
	 */
	private GridSettingModel gridModel;
	/**
	 * The view representing the grid
	 */
	private TempView rv;
	
	private LinearLayout mLinearLayout;

	/*@Override
	public void onAttachedToWindow() {
		
		this.getWindow().setType(WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG|WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onAttachedToWindow();
	}*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON|WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED|WindowManager.LayoutParams.FLAG_FULLSCREEN);
//		setContentView(R.layout.activity_grid);

		mLinearLayout = new LinearLayout(this);
		gridModel = new GridSettingModel();
		rv = new TempView(this, gridModel);
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
					}else{
//						gridModel.clear();
						rv.invalidate();
					}
				}
				if(action==MotionEvent.ACTION_UP){
//					gridModel.clear();
					rv.invalidate();
				}
				return true;
			}
		});
		Button b1 = new Button(this);
//		Button b1 = (Button) findViewById(R.id.config1);
		b1.setText("confirm");
		b1.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(gridModel.getIsSecond()){
					if(gridModel.isMatch()){
						gridModel.save();
						gridModel.clear();
						rv.invalidate();
						gridModel.setIsSecond(false);
						Toast.makeText(GridSetActivity.this, "Pattern Saved", Toast.LENGTH_SHORT).show();
						Intent openGetApp = new Intent(GridSetActivity.this, GetAppActivity.class);
						openGetApp.putExtra("TheModel", gridModel);
						startActivity(openGetApp);
					}
					else{
						gridModel.clear();
						gridModel.setIsSecond(false);
						Toast.makeText(GridSetActivity.this, "Patterns doesn't match", Toast.LENGTH_SHORT).show();
						rv.invalidate();

					}
					

				} else{
					gridModel.setIsSecond(true);
					gridModel.setResTemp();
					gridModel.clear();
					rv.invalidate();
				}
				
			}
		});
		rv.setBackgroundColor(Color.WHITE);
		mLinearLayout.setOrientation(1);
		mLinearLayout.addView(b1);

		mLinearLayout.addView(rv);
		
		setContentView(mLinearLayout);
		
		
		//Toast.makeText(this, String.valueOf(rv.windowWidth), Toast.LENGTH_SHORT).show();
	}
}
