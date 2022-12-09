package application;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * GoalSettingsController is a standard controller class responsible for controlling the functionality of the 
 * goal settings scene. 
*/
public class GoalSettingsController extends SettingsController implements Initializable{
	
	boolean haveGoalsBeenSet = false;
	
	@FXML
	private VBox goalsVBox;
	
    @FXML
    private ComboBox<String> awakeTimeHours;

    @FXML
    private ComboBox<String> awakeTimeMinutes;

    @FXML
    private ChoiceBox<String> awakeTimePeriod;

    @FXML
    private ComboBox<String> bedTimeHours;

    @FXML
    private ComboBox<String> bedTimeMinutes;

    @FXML
    private ChoiceBox<String> bedTimePeriod;

    @FXML
    private Label calculatedSleepLabel;

    @FXML
    private Button goalSettingsBack;
    
    @FXML
    private Label errorLabel;

    /**
   	* switches to the settings view
   	* @param event the event thrown when 'back' button is pressed
   	*/
    @FXML
    void switchSettingsView(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    /**
   	* sets the users goals for bed/awake time, and calculates and sets users total sleep goal.
   	* @param event the event thrown when 'confirm sleep' button is pressed
   	*/
    @FXML
    void setGoals(ActionEvent event) {
    	
    	// if the user filled out all of the required fields
    	if (updateSleepTime() != null) {
    		// confirm goals and switch scenes to settings
    		user.setGoalTotalSleep(updateSleepTime());
        	applicationStage.setScene(settingsView);
        	applicationStage.setTitle("Settings");
    	} else {
    		// other wise set the error label to let the user know they need to finish
    		errorLabel.setText("Please fill in all feild(s)");
    	}
    }
    /**
   	* calculates the users sleep time goals and the time difference between them. Then displays that difference in the GUI
   	* @return if the user filled in all fields, it returns the time duration of the difference between bed/awake time
   	* otherwise returns null
   	*/
    @FXML
    Time updateSleepTime() {
    	// reset error label
    	errorLabel.setText("");
    	// try to calculate the users goals, if the user left a choice box blank this code throws null pointer or number format exception
    	try {
    		Time bedTime = new Time(Integer.parseInt(bedTimeHours.getValue()), Integer.parseInt(bedTimeMinutes.getValue()), bedTimePeriod.getValue());
        	user.setGoalBedTime(bedTime);
        	Time awakeTime = new Time(Integer.parseInt(awakeTimeHours.getValue()), Integer.parseInt(awakeTimeMinutes.getValue()), awakeTimePeriod.getValue());
        	user.setGoalAwakeTime(awakeTime);
        	Time timeDifference = bedTime.difference(awakeTime);
        	calculatedSleepLabel.setText(timeDifference.printDifferenceFormat());
        	return timeDifference;
        // catch the errors and tell the user they need to fill in all of the fields
    	} catch (NumberFormatException | NullPointerException nfe) {
    		errorLabel.setText("Please fill in all feild(s)");
    		return null;
    	}
    }

    /**
   	*  /**
   	* constructs the initial scene/GUI to be displayed to the user
   	*/
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		// reset the error label
		errorLabel.setText("");
		
		/* if the user has already set their goals, set the default values for all the choice boxes
		 * to the current goal the user has already specified
		 */
		
		if (user.getGoalTotalSleep() != null) {
			bedTimeHours.setValue(user.getGoalBedTime().getHours() + "");
			
			System.out.println(user.getGoalBedTime().getMinutes());
			if (user.getGoalBedTime().getMinutes() < 10) {
				bedTimeMinutes.setValue("0" + user.getGoalBedTime().getMinutes());
			} else bedTimeMinutes.setValue(user.getGoalBedTime().getMinutes() + "");
			
			bedTimePeriod.setValue(user.getGoalBedTime().getPeriod() + "");
			awakeTimeHours.setValue(user.getGoalAwakeTime().getHours() + "");
			
			if (user.getGoalAwakeTime().getMinutes() < 10) {
				awakeTimeMinutes.setValue("0" +user.getGoalAwakeTime().getMinutes());
			} else awakeTimeMinutes.setValue(user.getGoalAwakeTime().getMinutes() + "");
			
			awakeTimePeriod.setValue(user.getGoalAwakeTime().getPeriod() + "");
		}
		
		// add options to the bed/awake time minute choice boxes, since there are 60 options much quicker to use loops rather than FXML
		for (int i = 0; i < 60; i++) {
			// in time format 1 is 01, 2 is 02, and so on
			if (i < 10) {
				bedTimeMinutes.getItems().add("0" + i);
				awakeTimeMinutes.getItems().add("0" + i);
			}else {
				bedTimeMinutes.getItems().add(""+i);
				awakeTimeMinutes.getItems().add(""+i);
			}
		}
		
	}
    	

}
