package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class PersonalSettingsController extends SettingsController implements Initializable {;
	

    @FXML
    private ChoiceBox<String> genderChoice;
    
    @FXML
    private Spinner<Integer> ageSpinner;

    @FXML 
    private Spinner<Integer> heightSpinner;

    @FXML
    private Spinner<Integer> weightSpinner;

    
    @FXML
    void switchSettingsView(ActionEvent event) {
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    }
    @FXML
    void confirmPersonalInformation(ActionEvent event) {
    	
    	user.setGender(genderChoice.getValue());
    	user.setAge(ageSpinner.getValue());
    	user.setWeight(weightSpinner.getValue());
    	user.setHeight(heightSpinner.getValue());
    	
    
    	
    	applicationStage.setScene(settingsView);
    	applicationStage.setTitle("Settings");
    	
    	System.out.println(user.getAge() + user.getGender() + user.getWeight() + user.getHeight());
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		
		// Age spinner setup
		SpinnerValueFactory<Integer> ageValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 125, 18);
		ageSpinner.setValueFactory(ageValueFactory);
		
		// Weight spinner setup
		SpinnerValueFactory<Integer> weightValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(50, 228, 80);
		weightSpinner.setValueFactory(weightValueFactory);
		
		// Height spinner setup
		SpinnerValueFactory<Integer> heightValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(100, 250, 170);
		heightSpinner.setValueFactory(heightValueFactory);
				
				
		
	}
}
