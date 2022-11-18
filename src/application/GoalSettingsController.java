package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class GoalSettingsController {

	Stage applicationStage;
	Scene settingsView;
	
	String targetBedTime;
	String targetAwakeTime;
	String targetSleep;
	
	boolean haveGoalsBeenSet = false;
	
	@FXML
	private Label calculatedSleepLabel;
	
    @FXML
    private Button setGoalsButton;
	
    @FXML
    private TextField targetBedTimeText;

    @FXML
    private Button goalSettingsBack;

    @FXML
    private TextField targetSleepText;

    @FXML
    private TextField targetAwakeTimeText;

    @FXML
    void switchSettingsView(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    @FXML
    void setGoals(ActionEvent event) {
    	
    	targetBedTime = targetBedTimeText.getText();
    	targetAwakeTime = targetAwakeTimeText.getText();
    	
    	calculateSleepTime();
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    
    @FXML
    void calculateSleepTime() {
    	
    	// DefaultVariables for the goal sleep time
    	String bedTime = "12:00 am";
    	int bedTimeHours = 0;
    	int bedTimeMinutes = 0;
    	
    	
    	// Default Variable for the goal awake time
    	String awakeTime = "12:00 am";
    	int awakeTimeHours = 0;
    	int awakeTimeMinutes = 0;
    	
    		

    	
    	bedTime = targetBedTimeText.getText();
    	awakeTime = targetAwakeTimeText.getText();
    	
    	// update the bedtime as it is inputed
    	if (bedTime.length() > 0) {
       		bedTimeHours = Integer.parseInt(bedTime.substring(0, bedTime.indexOf(":")));
          		bedTimeMinutes = Integer.parseInt(bedTime.substring(bedTime.indexOf(":")+1, bedTime.indexOf(":")+3));
       	}
    	
    	// update the awake time as it is inputed
    	if (awakeTime.length() > 0) {
          		awakeTimeHours = Integer.parseInt(awakeTime.substring(0, awakeTime.indexOf(":")));
          		awakeTimeMinutes = Integer.parseInt(awakeTime.substring(awakeTime.indexOf(":")+1, awakeTime.indexOf(":")+3));
       	}
    	
    	// calculate the users total goal for hours of sleep
    	
    	// Going from the morning to the morning
    	if (bedTime.contains("am") && awakeTime.contains("am")){
			if (bedTimeMinutes > awakeTimeMinutes) {
    			awakeTimeHours -= 1;
    			awakeTimeMinutes += 60;
    			targetSleep = (awakeTimeHours - bedTimeHours) + " hours " + (awakeTimeMinutes - bedTimeMinutes) + " minutes";
    			calculatedSleepLabel.setText(targetSleep);

    		} else if (bedTimeMinutes <= awakeTimeMinutes) {
    			targetSleep = (awakeTimeHours - bedTimeHours) + " hours " + (awakeTimeMinutes - bedTimeMinutes) + " minutes";
    			calculatedSleepLabel.setText(targetSleep);
    		}
			
		// Going from the afternoon to the morning
    	} else if (bedTime.contains("pm") && awakeTime.contains("am")){
    		bedTimeHours += 12;
			if (bedTimeMinutes > 0) {
				targetSleep = (((23 - bedTimeHours) + awakeTimeHours) + ((60 - bedTimeMinutes) + awakeTimeMinutes) / 60) + " hours " + ((60 - bedTimeMinutes) + awakeTimeMinutes) % 60 + " minutes";
				calculatedSleepLabel.setText(targetSleep);

    		} else if (bedTimeMinutes == 0) {
    			targetSleep = (24 - bedTimeHours) + awakeTimeHours + " hours " + awakeTimeMinutes + " minutes";
				calculatedSleepLabel.setText(targetSleep);
    		}
		
		// Going from the afternoon to the afternoon
    	} else if (bedTime.contains("pm") && awakeTime.contains("pm")){
    		if (bedTimeMinutes > awakeTimeMinutes) {
    			awakeTimeHours -= 1;
    			awakeTimeMinutes += 60;
    			targetSleep = (awakeTimeHours - bedTimeHours) + " hours " + (awakeTimeMinutes - bedTimeMinutes) + " minutes";
    			calculatedSleepLabel.setText(targetSleep);

    		} else if (bedTimeMinutes <= awakeTimeMinutes) {
    			targetSleep = (awakeTimeHours - bedTimeHours) + " hours " + (awakeTimeMinutes - bedTimeMinutes) + " minutes";
    			calculatedSleepLabel.setText(targetSleep);
    		}
    
    	// Going from morning to the afternoon
    	} else if (bedTime.contains("am") && awakeTime.contains("pm")){
    		awakeTimeHours += 12;
    		if (bedTimeMinutes > awakeTimeMinutes) {
    			awakeTimeHours -= 1;
    			awakeTimeMinutes += 60;
    			targetSleep = (awakeTimeHours - bedTimeHours) + " hours " + (awakeTimeMinutes - bedTimeMinutes) + " minutes";
    			calculatedSleepLabel.setText(targetSleep);

    		} else if (bedTimeMinutes <= awakeTimeMinutes) {
    			targetSleep = (awakeTimeHours - bedTimeHours) + " hours " + (awakeTimeMinutes - bedTimeMinutes) + " minutes";
    			calculatedSleepLabel.setText(targetSleep);
    		}
    	
    	}
    }
    	

}
