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
	

}
