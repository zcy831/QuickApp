package com.example.quickapp.controller;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.example.quickapp.R;
import com.example.quickapp.UI.CustomListViewAdapter;
import com.example.quickapp.UI.GridSettingModel;



/**
 * GetAppActivity is the second step of the set up part. It shows the list of the apps
 * installed in the cell phone and allow user to select which app to be opened.
 * @author Group 12
 *
 */
public class GetAppActivity extends Activity {
	/**
	 * The list of app information installed on the cell phone
	 */
	ArrayList<PInfo> appList;
	
	/**
	 *  The Layout of the app info
	 */
	LinearLayout mLinearLayout;
	
	GridSettingModel gridModel;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		mLinearLayout = new LinearLayout(this);
		mLinearLayout.setOrientation(1);
		setContentView(R.layout.activity_get_app);
		Intent i = getIntent();
		gridModel = (GridSettingModel) i.getSerializableExtra("TheModel");

		populateListView();
		
		
	}


	/**
	 * This method get the applist, set up the ListView and the adapter
	 */
	private void populateListView() {
		appList = getPackages();
		ListView lv = (ListView) findViewById(R.id.TheListView);
		List<String> appNames = new ArrayList<String>();
		List<Drawable> appIcons = new ArrayList<Drawable>();
		for(PInfo pi : appList){
			appNames.add(pi.getAppname());
			appIcons.add(pi.getIcon());
		}
		CustomListViewAdapter adapter = new CustomListViewAdapter(this, R.layout.app_name_icon, appNames.toArray(new String[appNames.size()]),appIcons.toArray(new Drawable[appIcons.size()]));
		lv.setAdapter(adapter);
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,
					long id) {
				Toast.makeText(GetAppActivity.this, "You Chose"+appList.get(position).appname, Toast.LENGTH_SHORT).show();
				if(appList.get(position).toOpen!=null){
				gridModel.addIntent(appList.get(position).toOpen);
				startActivity(appList.get(position).toOpen);
				}
			}
			
		});
	}

	/**
	 * This class simply represent the useful information of an app
	 * @author Group 12
	 *
	 */
	class PInfo {
		private String appname = "";
		private String pname = "";
		private String versionName = "";
		private int versionCode = 0;
		private Drawable icon;
		private Intent toOpen;
		public String getAppname() {
			return appname;
		}
		public String getPname() {
			return pname;
		}
		public int getVersionCode() {
			return versionCode;
		}
		public Drawable getIcon() {
			return icon;
		}
	}
	
	

	/**
	 * This method returns the package information within an arraylist with type PInfo
	 * @return
	 */
	private ArrayList<PInfo> getPackages() {
		ArrayList<PInfo> apps = getInstalledApps(false); /* false = no system packages */
		final int max = apps.size();
		for (int i=0; i<max; i++) {
			apps.get(i).getIcon();
		}
		return apps;
	}

	
	/**
	 * This method returns the package information within an arraylist with type PInfo
	 * @param getSysPackages "false represents no system packages"
	 * @return
	 */
	private ArrayList<PInfo> getInstalledApps(boolean getSysPackages) {
		ArrayList<PInfo> res = new ArrayList<PInfo>();        
		List<PackageInfo> packs = getPackageManager().getInstalledPackages(0);
		for(int i=0;i<packs.size();i++) {
			PackageInfo p = packs.get(i);
			if ((!getSysPackages) && isSystemPackage(p)) {
				continue ;
			}
			PInfo newInfo = new PInfo();
			newInfo.appname = p.applicationInfo.loadLabel(getPackageManager()).toString();
			newInfo.pname = p.packageName;
			newInfo.versionName = p.versionName;
			newInfo.versionCode = p.versionCode;
			newInfo.icon = p.applicationInfo.loadIcon(getPackageManager());
			newInfo.toOpen = getPackageManager().getLaunchIntentForPackage(p.packageName);
			res.add(newInfo);
			
		}
		return res; 
	}
	
	private boolean isSystemPackage(PackageInfo pkgInfo) {
	    return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
	            : false;
	}
	
//	private ArrayList<PInfo> getInstalledApps2(){
//		ArrayList<PInfo> res = new ArrayList<PInfo>();        
//		List<ApplicationInfo> packs = getPackageManager().getInstalledApplications(PackageManager.GET_META_DATA);
//		for(int i=0;i<packs.size();i++) {
//			ApplicationInfo p = packs.get(i);
//
//			PInfo newInfo = new PInfo();
//			newInfo.appname = p.loadLabel(getPackageManager()).toString();
//			newInfo.pname = p.packageName;
//			newInfo.icon = p.loadIcon(getPackageManager());
//			Intent intent = getPackageManager().getLaunchIntentForPackage(p.packageName);
//			res.add(newInfo);
//		}
//		return res; 
//	}
	
	
	
}
