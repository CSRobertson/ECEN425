package lego_assignment_2;

import lejos.robotics.RegulatedMotor;

public class claw {
private Boolean clawUp=false;
private Boolean clawOpen=true;
private RegulatedMotor clawCloser;
private RegulatedMotor clawLifter;
// TODO updates this angles
final int angleToOpen = 360;

/*
 * This is a claw for the robot, it still needs some tuning 
 */


	/*
	 * @param the motor for the claw closer
	 * @param The motor for the claw lifter
	 */
	public claw(RegulatedMotor clawCloser, RegulatedMotor clawLifter) {
		this.clawCloser = clawCloser;
		this.clawLifter = clawLifter;
		
		// This sets the speed of the motors to the max possible based on the battery level
		this.clawLifter.setSpeed((int) this.clawLifter.getMaxSpeed());
		this.clawCloser.setSpeed((int) this.clawCloser.getMaxSpeed());
	}
	
	/*
	 * Opens the claw
	 */
	public void openClaw(){
		if(!clawOpen){
			this.clawCloser.rotate(angleToOpen);
			clawOpen = true;
		}
	}
	/*
	 * Closes the claw
	 */
	public void closeClaw(){
		if(clawOpen){
			this.clawCloser.rotate(angleToOpen);
			clawOpen = false;
		}
	}
	
	/*
	 * Raises the claw
	 */
	public void raiseClaw(){
		if(!clawUp){
			this.clawLifter.backward(); 
			while(!this.clawLifter.isStalled()); //is stalled is a LeJos method
			this.clawLifter.stop(); //stops the motor and puts it in break mode
			clawUp = !clawUp;
		}
	}
	
	/*
	 * lowers the claw to pick pucks up
	 */
	public void lowerClaw(){
		if(clawUp){
			this.clawLifter.forward(); 
			while(!this.clawLifter.isStalled()); //is stalled is a LeJos method
			this.clawLifter.stop(); //stops the motor and puts it in break mode
			clawUp = !clawUp;
		}		
	}
	
	
	/*
	 * Aims the claw and then opens the claw to fire the puck
	 */
	public void fireClaw(){
		// TODO update this method with the right shit
	}
	
	/*
	 * returns true if the claw is open
	 */
	public Boolean isOpen(){
		return this.clawOpen;
	}
	/*
	 * returns true if the claw is up
	 */
	public Boolean isUp(){
		return this.clawUp;
	}
	
	public void close(){
		this.clawCloser.close();
		this.clawLifter.close();
	}
	

}
