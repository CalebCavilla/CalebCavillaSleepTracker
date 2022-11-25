package application;

import java.text.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddSleepPeriodController {

	private Stage applicationStage;
	private Scene diaryView;
	private User user;
	
    @FXML
    private ChoiceBox<String> moodChoiceBox;

    @FXML
    private TextField targetAwakeTimeText;

    @FXML
    private TextField targetBedTimeText;

    @FXML
    void confirmSleepPeriod(ActionEvent event) throws ParseException {
    	Day day = new Day("2022-11-25");
    	System.out.println(day);
    }

    @FXML
    void switchDiaryView(ActionEvent event) {
    	applicationStage.setScene(diaryView);
    	applicationStage.setTitle("Diary");
    }

	public Stage getApplicationStage() {
		return applicationStage;
	}

	public void setApplicationStage(Stage applicationStage) {
		this.applicationStage = applicationStage;
	}

	public Scene getDiaryView() {
		return diaryView;
	}

	public void setDiaryView(Scene diaryView) {
		this.diaryView = diaryView;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
