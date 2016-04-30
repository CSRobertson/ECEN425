package lego_assignment_2;

public class puck{
	 private int value;
	 private int x;
	 private int y;
	 /*
	  * Constructor for the puck class 
	  * @param value  the point value for the Puck
	  * @param x the X coordinate of the puck
	  * @param y the Y coordinate of the puck
	  */
	 
	 public puck(int value, int x, int y){
		 this.value=value;
		 this.x=x;
		 this.y=(int)y/5; 	// this scales it so the lowest value of puck(5) 
		 					//has a divider of 1 in the findClosestPuckWeighted  method
	 }
	 /*
	  * Returns the x or the puck
	  */
	 public int getX(){
		 return this.x;
	 }
	 
	 /*
	  * Returns the y of the puck
	  */
	 public int getY(){
		 return this.y;
	 }
	 
	 /*
	  * returns the value of the current puck
	  */
	 public int getValue(){
		 return this.value;
	 }
}
