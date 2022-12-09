package application;

import java.net.URL;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * AddSleepPeriodController is a standard controller class responsible for controlling the functionality of the 
 * AddSleepPeriod scene. 
*/
public class AddSleepPeriodController extends DiaryController implements Initializable{

	DiaryController diaryController;
		
		@FXML
		private Label errorLabel;
		
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
	    private ChoiceBox<String> moodChoiceBox;

	    @FXML
	    private ChoiceBox<String> sleepTypeChoiceBox;

	/**
	* creates a sleep class based on information received from the GUI.
	* @param event the event thrown when 'confirm sleep' button is pressed
	*/
    @FXML
    void confirmSleepPeriod(ActionEvent event) throws ParseException {
    	errorLabel.setText("");
    	
    	// makes sure user entered values for all the choice boxes
    	try {
    		// forces user to put in responses for mood and type
    		if (sleepTypeChoiceBox.getValue() == null || moodChoiceBox.getValue() == null) {
    			throw new NullPointerException("null type or mood");
    		}
    		
    		// creates the sleep period to be logged for the current day
    		int repeatDayFlag = 0;
        	Time bedTime = new Time(Integer.parseInt(bedTimeHours.getValue()), Integer.parseInt(bedTimeMinutes.getValue()), bedTimePeriod.getValue());
        	Time awakeTime = new Time(Integer.parseInt(awakeTimeHours.getValue()), Integer.parseInt(awakeTimeMinutes.getValue()), awakeTimePeriod.getValue());
        	Sleep sleepPeriod = new Sleep(sleepTypeChoiceBox.getValue(),bedTime, awakeTime, moodChoiceBox.getValue());
        	
        	// if there is already the selected day in the diary, just add a sleep period to that day
        	for (Day i : user.getDiary()) {
        		if (i.getDate().equals(selectedDate)){
        			i.getSleepPeriods().add(sleepPeriod);
        			repeatDayFlag = 1;
        		}
        	}
        	// if there was no day in the diary, create a new day and add the sleep period to it.
        	if (repeatDayFlag == 0) {
        		Day day = new Day(selectedDate);
    			day.getSleepPeriods().add(sleepPeriod);
    			user.getDiary().add(day);
        	}
      
        	// switch view back to the diary
        	applicationStage.setScene(diaryView);
        	diaryController.updateView();
        	applicationStage.setTitle("Diary");
        	
    	} catch (NumberFormatException | NullPointerException nfe) {
    		// error message
    		errorLabel.setText("Please fill in all feild(s)");
    	}
    	
    	
    }

    /**
	* switches scene to diaryView.
	* @param event the event thrown when 'back' button is pressed
	*/
    @FXML
    void switchDiaryView(ActionEvent event) {
    	applicationStage.setScene(diaryView);;
    	applicationStage.setTitle("Diary");
    }
    /**
   	* constructs the scene/GUI to be displayed to the user
   	*/
    @Override
	public void initialize(URL location, ResourceBundle resources) {
		
    	// default error label text
    	errorLabel.setText("");
    	
    	// adds options to the bed/awake time minutes choice boxes
		for (int i = 0; i < 60; i++) {
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
