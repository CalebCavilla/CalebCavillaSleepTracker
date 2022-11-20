package application;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class DiaryController implements Initializable {

	LocalDate selectedDate = LocalDate.now();
	Stage applicationStage;
	Scene mainMenuView;
	Scene diaryView;
	
    @FXML
    private DatePicker diaryDatePicker;
    
    @FXML
    private Label hoursSoFarLabel;
    
    @FXML
    private Label MinutesSoFarLabel;

    @FXML
    private Label remainingTimeLabel;

    @FXML
    private Label timeGoalLabel;
    

    @FXML
    void decreaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(-1);
    	diaryDatePicker.setValue(selectedDate);
    }

    @FXML
    void increaseDate(ActionEvent event) {
    	selectedDate = selectedDate.plusDays(1);
    	diaryDatePicker.setValue(selectedDate);
    }
    @FXML
    void setDate(ActionEvent event) {
    	selectedDate = diaryDatePicker.getValue();
    	System.out.println("Hello");
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		diaryDatePicker.setValue(selectedDate);
	}

}
