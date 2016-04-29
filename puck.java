package lego_assignment_2;

public class puck{
	 private int value;
	 private int x;
	 private int y;
	 /*
	  * Constuctor for the puck class 
	  * @param value  the point value for the Puck
	  * @param x the X cordinate of the puck
	  * @param y the Y cordinate of the puck
	  */
	 
	 public puck(int value, int x, int y){
		 this.value=value;
		 this.x=x;
		 this.y=(int)y/5; 	// this scales it so the loest value of puck(5) 
		 					//has a devider of 1 in the findClosestPuckWeighted  method
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
	  * returns the value of the curent puck
	  */
	 public int getValue(){
		 return this.value;
	 }
}
