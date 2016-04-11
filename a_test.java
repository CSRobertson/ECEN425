/**
 * 
 */
package main;


import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.robotics.RegulatedMotor;

/**
 * @author Nick
 * @version 0.0.1
 *
 */
public class a_test {

	static double dist =10;
	static double wheelDia=4;
	
	static claw theClaw;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		RegulatedMotor driveL = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor driveR = new EV3LargeRegulatedMotor(MotorPort.D);
		
		theClaw = new claw(new EV3LargeRegulatedMotor(MotorPort.C),new EV3MediumRegulatedMotor(MotorPort.B));
		
		double wheelCur = Math.PI*a_test.wheelDia;
		
		//init the motor's paramaters
		driveL.setAcceleration(400);
		driveR.setAcceleration(400);
		driveL.setSpeed(400);
		driveR.setSpeed(400);
		
		//work out the degrees to travel the required distance
		int degs = (int) Math.round((dist/wheelCur)*360);
		
		//travel the distance
		driveL.rotate(degs,true); //the true, means imitate return to execute the run for the second motor
		driveR.rotate(degs);
		
		driveL.close();
		driveR.close();

	}

}
