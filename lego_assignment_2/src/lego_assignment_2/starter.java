/**
 * 
 */
package lego_assignment_2;

import lejos.hardware.BrickFinder;
import lejos.hardware.Key;
import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.motor.*;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.SensorModes;
import lejos.robotics.MirrorMotor;
import lejos.robotics.RangeScanner;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.SampleProvider;
import lejos.robotics.chassis.*;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.mapping.LineMap;
import lejos.robotics.mapping.RangeMap;
import lejos.robotics.navigation.MovePilot;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.Navigator;
import lejos.robotics.navigation.Pose;
import lejos.robotics.pathfinding.DijkstraPathFinder;
import lejos.robotics.pathfinding.PathFinder;
import lejos.utility.Delay;
import lejos.robotics.localization.MCLParticleSet;
import lejos.robotics.localization.MCLPoseProvider;

/**
 * @author Nick
 *
 */
public class starter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Creating motors");
		RegulatedMotor right = new EV3LargeRegulatedMotor(MotorPort.A);
		RegulatedMotor left = new EV3LargeRegulatedMotor(MotorPort.D);
		RegulatedMotor clawLifter = new EV3MediumRegulatedMotor(MotorPort.C);
		RegulatedMotor claw = new NXTRegulatedMotor(MotorPort.B);
		System.out.println("Created Motors");
		//claw c = new claw(claw, clawLifter);
		//c.raiseClaw();
		
		
		
		
		System.out.println("Starting chassis");
		Wheel wheelL = WheeledChassis.modelWheel(left,41.5).offset(75.00);
		System.out.println("Creating wheelL");
		Wheel wheelR = WheeledChassis.modelWheel(right,41.5).offset(-75.00).invert(true);
		System.out.println("Creating wheelR");
		Chassis chassis = new WheeledChassis(new Wheel[]{wheelL,wheelR}, WheeledChassis.TYPE_DIFFERENTIAL);	
		System.out.println("Creating chassis");
		MovePilot pilot = new MovePilot(chassis);
		System.out.println("Creating Pilot");
		
		
		pilot.setAngularSpeed(pilot.getMaxAngularSpeed());
		pilot.setLinearSpeed(pilot.getMaxLinearSpeed());
		pilot.setLinearAcceleration(1000);
		
		System.out.println("Setting up Map");
		mapMaker bob = new mapMaker();
		LineMap map = bob.getMap();
		RangeFinder finder=null;
		try{
		System.out.println("Setting up RangeFinder");
		finder = new RangeFinder();
		}
		catch(Exception e){
			System.out.println("Shit Failed Yo");
		}
		
		if(finder == null)System.out.println("Finder is null");
		if(map == null)System.out.println("Map is null");
		MCLPoseProvider mcl=null;
		try{
		System.out.println("Setting up pose provider");
		RangeScanner bobb = (RangeScanner) finder;
		mcl = new MCLPoseProvider(pilot,bobb,map,200,10);
		System.out.println("MLC created");
		
		}
		catch(Exception e){
				System.out.println(e);
			}
		
		if(mcl==null){
			System.out.println("MCL is null bitches");
		}
		mcl.setDebug(true);
		MCLParticleSet set= mcl.getParticles();
		if(set==null){
			System.out.println("Set is null Bitches");
		}
		
		System.out.println("Setting up Path Finder");
		//DijkstraPathFinder pf = new DijkstraPathFinder(map);
		//pf.lengthenLines(8f);
		//System.out.println("Setting up Navigator");
		//Navigator Navman = new Navigator(pilot,mlc); 
		
		
		System.out.println("Trying to see where i am");
		Pose Guess = mcl.getPose();
		
		System.out.println("i'm at X: " + Guess.getX() +" y: "+Guess.getY());
		

		
		Delay.msDelay(4000);
//		System.out.println("Init Brick");
//		Key escape = BrickFinder.getDefault().getKey("Escape");
		
//		
//		System.out.println("Start Following");
//		while(!escape.isDown()){
//			distanceR.fetchSample(smaplesR, 0);//read the sensor im M
//			double curS = (double) smaplesR[0]*100; //Convert to MM
//			System.out.println("Sample: " + curS);  //Prints the Value
//			if(curS>7 && curS<15){
//				System.out.println("Move Towards");
//				pilot.travelArc(200, 500,true);
//				Delay.msDelay(300);
//			}
//			else if(curS>15){
//				System.out.println("Miles away cunt");
//				pilot.travelArc(80, 500, true);
//				Delay.msDelay(600);
//			}
//			else if(curS<5){
//				System.out.println("Move Away");
//				pilot.travelArc(-200, 500,true);
//				Delay.msDelay(300);
//			}
//			else{
//				System.out.println("Bang On");
//				pilot.forward();
//				Delay.msDelay(150);
//				
//			}
//			
//		}
		
		
		
//		left.stop();
//		right.stop();
		
		finder.close();
		claw.close();
		left.close();
		right.close();

	}
}
