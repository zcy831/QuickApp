package com.example.quickapp.UI;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import com.example.quickapp.model.Position;

import android.content.Intent;

/**
 * 
 * This is the model for the grid. It saves the positions user is currently drawing into positions and
 * the saved drawing patterns for matching in resTemp.
 * 
 * @author Group 12
 *
 */
public class GridSettingModel implements Serializable {
	/**
	 * The drawn positions 
	 */
	private Set<Position> drawn;

	

	
	/**
	 * resTemp saves the pattern for confirmation
	 */
	private Set<Position> resTemp;
	
	/**
	 * isSecond checks whether the pattern created is the second pattern
	 */
	private boolean isSecond;
	
	/**
	 * The saved patterns.(In this version it's just one pattern)
	 */
	private Set<Set> savedPattern;
	
	private Set<Intent> savedIntent;
	
	public GridSettingModel(){
		drawn = new HashSet<Position>();
		resTemp = new HashSet<Position>();
		savedIntent = new HashSet<Intent>();
		isSecond = false;

		savedPattern = new HashSet<Set>();

		Position pa = new Position(0, 1);
		Position pb = new Position(1, 1);
		Position pc = new Position(2, 1);
		Position pd = new Position(3, 1);
		resTemp.add(pa); resTemp.add(pb);
		resTemp.add(pc); resTemp.add(pd);
		
		savedPattern.add(resTemp);
	}
	
	/**
	 * The check method checks the status of certain position on the grid
	 * @param s The "x" axis parameter of the position
	 * @param t The "y" axis parameter of the position
	 * @return the boolean variable representing whether this position should turn red
	 */
	public boolean check(int s, int t){
		return this.drawn.contains(new Position(s, t));
	}
	
	/**
	 * The add method adds a new position to the list of "drawn" position
	 * @param s The "x" axis parameter of the position
	 * @param t The "y" axis parameter of the position
	 */
	public void add(int s, int t){
		this.drawn.add(new Position(s, t));
	}
	
	/**
	 * It clears the positions saved in "drawn" list
	 */
	public void clear(){
		this.drawn.clear();
	}
	
	
	
	/**
	 * It checks if the drawn pattern matches the previous drawn pattern
	 * @return
	 */
	public boolean isMatch(){
		boolean output = true;
		if(this.drawn.size()!=this.resTemp.size()) {
			output = false;
			}
		for(Position p: this.drawn){
			if(!this.resTemp.contains(p)){
				output = false;}
		}
		return output;
	}
	
	/**
	 * add a new drawn pattern to resTemp
	 */
	public void setResTemp(){
		resTemp.clear();
		for(Position p : drawn){
			resTemp.add(p);
		}
	}	
	
	
	

	
	/**
	 * This method saves the new res into the memory
	 */
	public void save(){
		savedPattern.add(resTemp);
	}
	
	public void addIntent(Intent i){
		this.savedIntent.add(i);
	}
	
	
	public boolean getIsSecond() {
		return isSecond;
	}
	
	public void setIsSecond(boolean state){
		isSecond = state;
	}
	

}
