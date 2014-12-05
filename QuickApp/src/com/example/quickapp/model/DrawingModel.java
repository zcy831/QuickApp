package com.example.quickapp.model;

public class DrawingModel {
	public final int N = 200;
	private double[][] b;
	
	public DrawingModel(){
		b = new double[N][N];
	}
	
	public double get(int x, int y){
		return b[x][y];
	}
	
	public void set(int x, int y){
		b[x][y] = 1.0;
	}
	
	public void clear(){
		b = new double[N][N];
	}
}
