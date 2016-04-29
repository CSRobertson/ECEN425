package lego_assignment_2;

import java.io.IOException;
import java.util.*;
import lejos.robotics.geometry.Line;
import lejos.robotics.geometry.Rectangle;
import lejos.robotics.mapping.LineMap;

public class mapMaker {
private LineMap mapped;
private puck[] pucks;
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
			//lines around the outside
			lines.add(new Line(0,0,190,0));
			lines.add(new Line(190,0,190,290));
			lines.add(new Line(0,290,190,290));
			lines.add(new Line(0,0,0,290));
		
			line = lines.toArray(new Line[lines.size()]);
		
			rect = new Rectangle(0,0,190,290);
		
		LineMap map = new LineMap(line,rect);
		mapped=map;
		
		
	}
	/*
	 * Return the  map generated in the constructor 
	 */
	public LineMap getMap(){
		return this.mapped;
	}
	/*
	 * will generate the pucks for map
	 */
	public puck[] getpucks(){
		
		puck[] pucks = {new puck(1,2,5), new puck(3,4,5)};
		return pucks;
	}
	
	/*returns the cloest puck with no consideration for the vlaue of the puck
	*@param x The current X of the robot
	*@param y The current Y of the robot
	*/
	public int[] findClosestPuck(int x, int y){
		int deltaX;
		int deltaY;
		int minDist=2000;
		puck puckF=null;
		
		for(puck p: this.pucks){
			deltaX = Math.abs(p.getX()-x);
			deltaY = Math.abs(p.getY()-y);
			int dist = (int) Math.sqrt(deltaX^2+deltaY^2);
			if(dist<minDist){
				puckF=p;
				minDist=dist;
			}
		}
		int[] answ = {puckF.getX(),puckF.getY()};
		return answ;	
	}
	
	
	
	/*returns the cloest puck with a consideration of the value of the puck 
	*@param x The current X of the robot
	*@param y The current Y of the robot
	*/
	public int[] findClosestPuckWeighted(int x, int y){
		int deltaX;
		int deltaY;
		int minDist=2000;
		puck puckF=null;
		
		for(puck p: this.pucks){
			deltaX = Math.abs(p.getX()-x);
			deltaY = Math.abs(p.getY()-y);
			int dist = (int) Math.sqrt(deltaX^2+deltaY^2);
			dist = dist/p.getValue();
			if(dist<minDist){
				puckF=p;
				minDist=dist;
			}
		}
		int[] answ = {puckF.getX(),puckF.getY()};
		return answ;
}	
}






