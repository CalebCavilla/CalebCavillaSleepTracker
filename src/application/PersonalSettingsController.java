package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PersonalSettingsController extends SettingsController {

	Stage applicationStage;
	Scene settingsView;
	
	int age;
	String gender;
	
    @FXML
    private TextField ageText;

    @FXML
    private ChoiceBox<String> genderChoice;

    @FXML
    void switchSettingsView(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    @FXML
    void confirmPersonalInformation(ActionEvent event) {
    	age = Integer.parseInt(ageText.getText());
    	gender = genderChoice.getValue();
    	
    	System.out.println(age + " " + gender);
    }
}
