// ======================================================================================
// FILE: InputVerifier.java
// CREATION DATE: OCT 20, 2016
// ABOUT: Responsible for verifying user input from the GUI
// ======================================================================================

package main;

import CustomExceptions.InvalidChoiceException;
import CustomExceptions.InvalidInputException;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import utilities.Constants;

public class InputVerifier 
{
	// ----------------------------------------------------------------------------------
	// Properties
	
	private int m_verifiedNumOfSensors;
	private double m_verifiedSensorRadius;
	private String m_verifiedAlgorithmChoice;
	
	// ----------------------------------------------------------------------------------
	// Methods
	
	public int getVerifiedNumOfSensors()
	{
		return m_verifiedNumOfSensors;
	}
	
	public double getVerifiedSensorRadius()
	{
		return m_verifiedSensorRadius;
	}
	
	public String getVerifiedAlgorithmChoice()
	{
		return m_verifiedAlgorithmChoice;
	}
	
	public boolean isInt(
		TextField i_textField,
		String i_inputValue
		) throws InvalidInputException
	{
		try
		{
			int parsedInt = Integer.parseInt(i_inputValue);
			
			if (parsedInt <= 0)
			{
				String errorMessage = "Number of sensors must be gt 0";
				showInvalidInputAlert(errorMessage);
				throw new InvalidInputException(errorMessage);
			}
			else
			{
				m_verifiedNumOfSensors = parsedInt;
				
				System.out.println("-- " + m_verifiedNumOfSensors + " sensors");
			}
			
			return true;
		} 
		catch(NumberFormatException e) 
		{
			String errorMessage = "Number of sensors must be an int";
						
			showInvalidInputAlert(errorMessage);
			
			return false;
		}
	}
		
	public boolean isDouble(
		TextField i_textField,
		String i_inputValue
		) throws InvalidInputException
	{
		try
		{
			double parsedDouble = Double.parseDouble(i_inputValue);
			
			if (parsedDouble <= 0 || parsedDouble > Constants.INTERVAL_MAX)
			{
				String errorMessage = "Radius(r) must be 0 < r < 1";
				showInvalidInputAlert(errorMessage);
				throw new InvalidInputException(errorMessage);
			}
			else
			{
				m_verifiedSensorRadius = parsedDouble;
				
				System.out.println("-- radius is: " + m_verifiedSensorRadius);
			}
			return true;
		}
		catch(NumberFormatException e)
		{
			String errorMessage = "Radius must be a double";
						
			showInvalidInputAlert(errorMessage);
			
			return false;
		}
	}
	
	public void verifyChoiceValidity(
		ToggleGroup i_toggleGroup
		) throws InvalidChoiceException
	{
		Object choice = i_toggleGroup.getSelectedToggle().getUserData();
		
		if (!(choice.equals(AlgorithmType.RIGID_COVERAGE) || 
				choice.equals(AlgorithmType.SIMPLE_COVERAGE) ||
				choice.equals(AlgorithmType.OVERLAP_COVERAGE) ||
				choice.equals(AlgorithmType.SPLIT_COVERAGE)))
		{
			throw new InvalidChoiceException("ERROR: Invalid choice!");
		}
		
		m_verifiedAlgorithmChoice = new String(choice.toString());
	}
		
	private void showInvalidInputAlert(
		String i_message
		)
	{
		System.out.println("-- " + i_message);
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Invalid Input");
		alert.setHeaderText("Invalid Input");
		alert.setContentText(i_message);
		alert.showAndWait();
	}
}
