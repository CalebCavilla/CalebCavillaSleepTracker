package application;

import java.text.ParseException;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSleepPeriodController extends DiaryController {

	DiaryController diaryController;
	
    @FXML
    private ChoiceBox<String> moodChoiceBox;
    
    @FXML
    private ChoiceBox<String> sleepTypeChoiceBox;

    @FXML
    private TextField awakeTimeText;

    @FXML
    private TextField bedTimeText;

    @FXML
    void confirmSleepPeriod(ActionEvent event) throws ParseException {
    	int repeatDayFlag = 0;
    	String bedtime = bedTimeText.getText();
    	Time bedTime = new Time(Integer.parseInt(bedtime.substring(0, bedtime.indexOf(":"))), Integer.parseInt(bedtime.substring(bedtime.indexOf(":")+1, bedtime.indexOf(":")+3)), bedtime.substring(bedtime.length()-2));
    	String awaketime = awakeTimeText.getText();
    	Time awakeTime = new Time(Integer.parseInt(awaketime.substring(0, awaketime.indexOf(":"))), Integer.parseInt(awaketime.substring(awaketime.indexOf(":")+1, awaketime.indexOf(":")+3)), awaketime.substring(awaketime.length()-2));
    	Sleep sleepPeriod = new Sleep(sleepTypeChoiceBox.getValue(),bedTime, awakeTime, moodChoiceBox.getValue());
    	
    	for (Day i : user.getDiary()) {
    		if (i.getDate().equals(selectedDate)){
    			i.getSleepPeriods().add(sleepPeriod);
    			repeatDayFlag = 1;
    		}
    	}
    	if (repeatDayFlag == 0) {
    		Day day = new Day(selectedDate);
			day.getSleepPeriods().add(sleepPeriod);
			user.getDiary().add(day);
    	}
  
    	applicationStage.setScene(diaryView);
    	diaryController.updateView();
    	applicationStage.setTitle("Diary");
    	
    }

    @FXML
    void switchDiaryView(ActionEvent event) {
    	applicationStage.setScene(diaryView);;
    	applicationStage.setTitle("Diary");
    }

}
