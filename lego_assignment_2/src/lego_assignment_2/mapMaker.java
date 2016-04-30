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
		//maze left
		lines.add(new Line(0,340,60,340));
		lines.add(new Line(60,340,60,300));
		lines.add(new Line(20,390,20,360));
		lines.add(new Line(20,360,80,360));
		lines.add(new Line(80,360,80,300));
		//maze mid left
		lines.add(new Line(190,390,190,340));
		lines.add(new Line(190,340,130,340));
		lines.add(new Line(130,340,130,300));
		lines.add(new Line(170,390,170,360));
		lines.add(new Line(170,360,110,360));
		lines.add(new Line(110,360,110,300));
		//maze mid right
		lines.add(new Line(200,390,200,340));
		lines.add(new Line(200,340,260,340));
		lines.add(new Line(260,340,260,300));
		lines.add(new Line(220,390,220,360));
		lines.add(new Line(220,360,280,360));
		lines.add(new Line(280,360,280,300));
		//maze right
		lines.add(new Line(390,340,330,340));
		lines.add(new Line(330,340,330,300));
		lines.add(new Line(370,390,370,360));
		lines.add(new Line(370,360,310,360));
		lines.add(new Line(310,360,310,300));
		//outlines
		lines.add(new Line(0,0,0,390));
		lines.add(new Line(0,390,390,390));
		lines.add(new Line(390,390,390,0));
		lines.add(new Line(390,0,0,0));
	
		line = lines.toArray(new Line[lines.size()]);
	
		rect = new Rectangle(0,0,390,390);		
		LineMap map = new LineMap(line,rect);
		mapped=map;
		
		try {
			map.createSVGFile("svg_final.svg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
		ArrayList<puck> pucks = new ArrayList<puck>();
		//pucks
		//bottom 30 points
		pucks.add(new puck(30, 35, 15));
		pucks.add(new puck(30, 55, 15));
		pucks.add(new puck(30, 235, 15));
		pucks.add(new puck(30, 355, 15));
		//bottom 10 points
		pucks.add(new puck(10, 35, 65));
		pucks.add(new puck(10, 55, 65));
		pucks.add(new puck(10, 235, 65));
		pucks.add(new puck(10, 355, 65));
		//top line 10 points
		pucks.add(new puck(10, 105, 375));
		pucks.add(new puck(10, 125, 375));
		pucks.add(new puck(10, 145, 375));
		pucks.add(new puck(10, 165, 375));	
		pucks.add(new puck(10, 185, 375));
		pucks.add(new puck(10, 205, 375));
		pucks.add(new puck(10, 225, 375));
		pucks.add(new puck(10, 245, 375));	
		pucks.add(new puck(10, 265, 375));
		pucks.add(new puck(10, 285, 375));
		//middle line 5 points
		pucks.add(new puck(5, 105, 345));
		pucks.add(new puck(5, 125, 345));
		pucks.add(new puck(5, 145, 345));
		pucks.add(new puck(5, 165, 345));	
		pucks.add(new puck(5, 185, 345));
		pucks.add(new puck(5, 205, 345));
		pucks.add(new puck(5, 225, 345));
		pucks.add(new puck(5, 245, 345));	
		pucks.add(new puck(5, 265, 345));
		pucks.add(new puck(5, 285, 345));
		//bottom line 10 points
		pucks.add(new puck(10, 105, 315));
		pucks.add(new puck(10, 125, 315));
		pucks.add(new puck(10, 145, 315));
		pucks.add(new puck(10, 165, 315));	
		pucks.add(new puck(10, 185, 315));
		pucks.add(new puck(10, 205, 315));
		pucks.add(new puck(10, 225, 315));
		pucks.add(new puck(10, 245, 315));	
		pucks.add(new puck(10, 265, 315));
		pucks.add(new puck(10, 285, 315));
		return pucks.toArray(new puck[pucks.size()]);
	}
	
	/*returns the cloest puck with no consideration for the vlaue of the puck
	*@param x The current X of the robot
	*@param y The current Y of the robot
	*/
	public int[] findClosestPuck(float x, float y){
		float deltaX;
		float deltaY;
		float minDist=2000;
		puck puckF=null;
		
		for(puck p: this.pucks){
			deltaX = Math.abs(p.getX()-x);
			deltaY = Math.abs(p.getY()-y);
			float dist = (float) Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
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
	*@returns the corinnaties of the puck
	*/
	public int[] findClosestPuckWeighted(int x, int y){
		float deltaX;
		float deltaY;
		float minDist=2000;
		puck puckF=null;
		
		for(puck p: this.pucks){
			deltaX = Math.abs(p.getX()-x);
			deltaY = Math.abs(p.getY()-y);
			float dist = (float) Math.sqrt((deltaX*deltaX)+(deltaY*deltaY));
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







