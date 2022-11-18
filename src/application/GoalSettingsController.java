package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GoalSettingsController {

	Stage applicationStage;
	Scene settingsView;
	
    @FXML
    private TextField bedTimeText;

    @FXML
    private Button goalSettingsBack;

    @FXML
    private TextField targetSleep;

    @FXML
    private TextField wakeUpText;

    @FXML
    void SetMainSettingsScene(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }

}
