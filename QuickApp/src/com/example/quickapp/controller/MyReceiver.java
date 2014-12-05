package com.example.quickapp.controller;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * This is the model used for the app. Every user has a model.
 * It consists of the action list corresponding to gestures and
 * patterns.
 * 
 * @author Group12
 *
 */
public class MyReceiver extends BroadcastReceiver {
	public static boolean wasScreenOn = true;

	@Override
	public void onReceive(Context context, Intent intent) {
		
		if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
			/*wasScreenOn = false;
			Intent intent11 = new Intent(context, DrawingActivity.class);
			intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent11);*/
		}else if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
			wasScreenOn = true;
			Intent intent11 = new Intent(context, DrawingActivity.class);
			intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent11);
		}else if(intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)){
			Intent intent11 = new Intent(context, DrawingActivity.class);
			intent11.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			context.startActivity(intent11);
		}
	}

}
