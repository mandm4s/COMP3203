// ======================================================================================
// FILE: SensorFactory.java
// CREATION DATE: OCT 20, 2016
// ABOUT: Responsible for creating new Sensors
// ======================================================================================

package main;

public class SensorFactory 
{
	// ----------------------------------------------------------------------------------
	// Properties
	
	private RandomPositionGenerator m_randomPositionGenerator;
	
	// ----------------------------------------------------------------------------------
	// Constructor
	
	SensorFactory()
	{
		m_randomPositionGenerator = new RandomPositionGenerator();
	}
	
	// ----------------------------------------------------------------------------------
	// Methods
	
	public Sensor createSensor(
		boolean i_hasAnimation
		)
	{	
		double randomPosition = m_randomPositionGenerator.generateRandomPosition();
		
		Sensor sensor = new Sensor(randomPosition, i_hasAnimation);
		
		return sensor;
	}
}
