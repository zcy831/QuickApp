package com.example.quickapp.model;

import java.util.Comparator;

public class AppList{
	/** application list singleton*/
	private static final AppList appList = new AppList();
	/** the size of the list */
	private int size;
	/** the max id of the applications*/
	private int maxID;
	
	/**
	 * private construction for singleton
	 */
	private AppList(){}
	
	/**
	 * get singleton class
	 * @return singleton
	 */
	public static AppList getInstance(){
		return appList;
	}
	
	/**
	 * add a new application into the list
	 */
	public void addNewApp(){
		maxID++;
		App app = new App(maxID);
		//Todo add app to list
	}
	
	/**
	 * Given an application index, get an application model.
	 * @param id
	 * @return an application model.
	 */
	public App get(int id){
		//Todo
		return null;
	}
	
	/**
	 * compare any two application
	 * @author niuxiang
	 *
	 */
	private class AppComparator implements Comparator<App>{
		@Override
		public int compare(App app1, App app2) {
			if(app1.getScore()<app2.getScore()) {return -1;}
			else if(app1.getScore()>app2.getScore()) {return 1;}
			else {return 0;}
		}
	}
}
