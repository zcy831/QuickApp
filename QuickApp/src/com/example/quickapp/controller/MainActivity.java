package com.example.quickapp.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.quickapp.R;

/**
 * MainActivity is the first activity of our app's setting part;
 * Now this setting part only support setting up the grid pattern to be recognized and 
 * select an app to be opened.
 * @author Group 12
 *
 */
public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button gridButton = (Button) findViewById(R.id.GridButton);
//		Button shakeButton = (Button) findViewById(R.id.ShakeButton);
//		Button soundButton = (Button) findViewById(R.id.SoundButton);
		ToggleButton serviceOnOff = (ToggleButton) findViewById(R.id.controllService);
		
		gridButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intentToGb = new Intent(MainActivity.this,GridSetActivity.class);
				startActivity(intentToGb);
				
			}
		});
		
		serviceOnOff.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				Toast.makeText(MainActivity.this, "The Service is set " + isChecked, Toast.LENGTH_SHORT).show();
				if(isChecked){
					startService(new Intent(MainActivity.this, MyService.class));
				}else{
					stopService(new Intent(MainActivity.this, MyService.class));
				}
			}
		});

	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
