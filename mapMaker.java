package lego_assignment_2;

import java.io.IOException;
import java.util.*;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;

public class mapMaker {
private LineMap map;
	
// Creates the map for the test mase in AM219	
	public mapMaker() {
		ArrayList<Line> lines = new ArrayList<Line>();
		Line[] line;
		Rectangle rect;
			lines.add(new Line(0,240,0,290));
			lines.add(new Line(0,240,60,240));
			lines.add(new Line(60,190,60,240));
		
			lines.add(new Line(80,190,80,260));
			lines.add(new Line(20,260,80,260));
			lines.add(new Line(20,260,20,290));
		
			lines.add(new Line(110,190,110,260));
			lines.add(new Line(110,260,170,260));
			lines.add(new Line(170,260,170,290));
		
			lines.add(new Line(130,190,130,240));
			lines.add(new Line(130,240,190,240));
			lines.add(new Line(190,240,190,290));
		
			line = lines.toArray(new Line[lines.size()]);
		
			rect = new Rectangle(0,0,190,290);
		
		LineMap map = new LineMap(line,rect);
		
		
		try {
			map.createSVGFile("map2.svg");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public LineMap getMap(){
		return this.map;
	}
	
	public Puck[] getpucks(){
		
	}
	
	//returns the cloest puck with no consideration for the vlaue of the puck
	//@param x The current X of the robot
	//@param y The current Y of the robot
	public int[] findClosestPuck(int x, int y){
		int deltaX;
		int deltaT;
		int minDist=2000;
		
		for(Puck P: this.pucks){
			deltaX = Math.abs(p.getX()-x);
			deltaY = Math.abs(p.getY()-y);
			int dist = (int) Math.sqrt(deltaX^2+deltaY^2);
			minDist = Math.min(minDist, dist);
		}
		int[] answ = {p.getX(),p.getY()};
		return answ;	
	}
	
	
	
	
	public int[] findClosestPuckWeighted(int x, int y){
		int deltaX;
		int deltaT;
		int minDist=2000;
		for(Puck P: this.pucks){
			deltaX = Math.abs(p.getX()-x);
			deltaY = Math.abs(p.getY()-y);
			int dist = (int) Math.sqrt(deltaX^2+deltaY^2);
			dist = dist/p.getValue();
			minDist = Math.min(minDist, dist);
		}
		int[] answ = {p.getX(),p.getY()};
		return answ;	
	}
}	



private class Puck{
	 private int value;
	 private int x;
	 private int y;
	 
	 public Puck(int value, int x, int y){
		 this.value=value;
		 this.x=x;
		 this.y=(int)y/5; 	// this scales it so the loest value of puck(5) 
		 					//has a devider of 1 in the findClosestPuckWeighted  method
	 }
	 
	 public.getX(){
		 return this.x;
	 }
	 
	 public.getY(){
		 return this.y;
	 }
	 public getValue(){
		 return this.value;
	 }
}



