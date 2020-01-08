/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Thomas Lewis & Donovan Schaafsma
 */
public class Location {

	int x; 
	int y;
	double travelTime = 1.5; // 1.5 minutes

	public Location(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public double travelTime(Location from, Location to) {
		int x = from.x - to.x;
		int y = from.y - to.y;
		int difference = Math.abs(x * y);
		return difference * travelTime;
	}
		
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}
