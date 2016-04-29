package lego_assignment_2;

import lejos.hardware.ev3.LocalEV3;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3IRSensor;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.hardware.sensor.NXTUltrasonicSensor;
import lejos.robotics.RangeReadings;
import lejos.robotics.RangeScanner;
import lejos.robotics.SampleProvider;

public class RangeFinder implements RangeScanner {
	//stuff for the sensors
	private static EV3UltrasonicSensor sensorF = new EV3UltrasonicSensor(LocalEV3.get().getPort("S1"));
	private static NXTUltrasonicSensor sensorL = new NXTUltrasonicSensor(LocalEV3.get().getPort("S3"));
	private static EV3IRSensor sensorR = new EV3IRSensor(LocalEV3.get().getPort("S2"));
	
	private static SampleProvider distanceF = sensorF.getMode("Distance");
	private static SampleProvider distanceL = sensorL.getMode("Distance");
	private static SampleProvider distanceR = sensorR.getMode("Distance");
	
	float[] smaplesF = new float[distanceF.sampleSize()];
	float[] smaplesL = new float[distanceL.sampleSize()];
	float[] smaplesR = new float[distanceR.sampleSize()];
	
	private static RangeReadings readings = new RangeReadings(3);
	
	public RangeFinder(){
	}

	
	
	@Override
	public RangeReadings getRangeValues() {
		takeReadings();
		return readings;
	}
	
	private void takeReadings(){
		distanceL.fetchSample(smaplesR, 0);
		distanceF.fetchSample(smaplesF, 0);
		distanceR.fetchSample(smaplesR, 0);
		readings.setRange(0, -90,smaplesL[0]);
		readings.setRange(1, 0, smaplesF[0]);
		readings.setRange(2, 90, smaplesR[0]);	
	}

	@Override
	public void setAngles(float[] angles) {
		// TODO Auto-generated method stub
		
	}
	
	public RangeReadings getReadings(){
		return readings;
	}

	@Override
	public lejos.robotics.RangeFinder getRangeFinder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void close(){
		sensorF.close();
		sensorR.close();
		sensorL.close();
	}

}
