// ======================================================================================
// FILE: Simulation.java
// CREATION DATE: OCT 20, 2016
// ABOUT: Contains all knowledge about each simulation run. 
// ======================================================================================

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import utilities.Constants;

public class Simulation 
{
	// ----------------------------------------------------------------------------------
	// Properties
	
	private int m_numOfSensors;
	
	private double m_sensorRadius;
	
	private String m_algorithmName;
	
	private ArrayList<Sensor> m_sensors;
	
	private boolean m_hasAnimation;
	
	// ----------------------------------------------------------------------------------
	// Constructor
	
	public Simulation(
		int i_numOfSensors,
		double i_sensorRadius,
		String i_algorithm
		)
	{
		m_numOfSensors = i_numOfSensors;
		m_sensorRadius = i_sensorRadius;
		m_algorithmName = new String(i_algorithm);
		
		animationGoOrNoGo(m_numOfSensors);
		
		m_sensors = new ArrayList<Sensor>();
		
		generateSensors();
		
		callAlgorithm(m_algorithmName);
	}
	
	// ----------------------------------------------------------------------------------
	// Methods
	
	public int getNumOfSensors()
	{
		return m_numOfSensors;
	}
		
	
	
	public double getSensorRadius()
	{
		return m_sensorRadius;
	}
	
	
	
	public String getAlgorithmName()
	{
		return m_algorithmName;
	}
	
	
	
	public ArrayList<Sensor> getSensors()
	{
		return m_sensors;
	}
	
	
	
	public boolean hasAnimation()
	{
		return m_hasAnimation;
	}
	
	// ----------------------------------------------------------------------------------
	// Helper Functions
	
	private void animationGoOrNoGo(
		int i_numOfSensors
		)
	{
		if (i_numOfSensors <= Constants.MAX_SENSORS_ANIME)
		{
			m_hasAnimation = true;
		}
	}
	
	private void generateSensors()
	{
		SensorFactory sensorFactory = new SensorFactory();
				
		for (int i = 0; i < m_numOfSensors; i++)
		{
			Sensor sensor = sensorFactory.createSensor(m_sensorRadius, m_hasAnimation);
			m_sensors.add(sensor);
		}
		
		System.out.println("-- " + m_numOfSensors + " sensors created!");
	}
	
	//not finished, needs clarification...
	public void generateGraphData(){
		HashMap<Double,Double> dataSeries = new HashMap<Double, Double>(); 
		for(double interval : generateGraphIntervals(this.m_sensorRadius)){
			//goes through all the indexes of the array and calculates the movement of each radius size
			//add it to the hashmap
		}
	}
	
    public ArrayList<Double> generateGraphIntervals(double rad){
    	ArrayList<Double> list = new ArrayList<Double>();  	
    	double interval = ((rad*2)-rad)/10;  	
    	list.add(rad); 	
    	for(int i=0; i<10;i++){
    		if(rad-(interval*(i+1)) > 0.0){
    			list.add(rad-(interval*(i+1)));
    		}
    		if(rad+(interval*(i+1)) < 1.0){
    			list.add(rad+(interval*(i+1)));
    		}
    	}
    	Collections.sort(list);
    	return list;
    }   
	
	private void callAlgorithm(
		String i_algorithmName
		)
	{
		
		AlgorithmController ac;
		
		if(i_algorithmName.equals(AlgorithmType.SIMPLE_COVERAGE.toString())) {
			SimpleAlgorithm algo = new SimpleAlgorithm(this);
			algo.run();
		}
		else if(i_algorithmName.equals(AlgorithmType.RIGID_COVERAGE.toString())) {
			RigidAlgorithm algo = new RigidAlgorithm(this);
			algo.run();
		}
		else if(i_algorithmName.equals(AlgorithmType.OVERLAP_COVERAGE.toString())) {
			OverlapAlgorithm algo = new OverlapAlgorithm(this);
			algo.run();
		}
		else if (i_algorithmName.equals(AlgorithmType.SPLIT_COVERAGE.toString())) {
			SplitAlgorithm algo = new SplitAlgorithm(this);
			algo.run();
		}
		else {
			System.out.println("-- Selected Invalid Algorithm");
			//an algorithm was requested that does not exist
		}
		
		
		
	}
}
