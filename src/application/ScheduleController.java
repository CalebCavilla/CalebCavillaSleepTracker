package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.skin.DatePickerSkin;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScheduleController extends MainMenuViewController implements Initializable{

	private DatePicker scheduleDatePicker = new DatePicker(LocalDate.now());

	private Week selectedWeek;
	
	private LocalDate selectedDate = scheduleDatePicker.getValue();
	
    @FXML
    private VBox scheduleVBox;

    @FXML
    private GridPane gridPane;
    
    @FXML
    private TextArea aboutTextArea;
    
    @FXML
    private Label weekRange;
    
    @FXML
    private ImageView blueBoxImage;

    @FXML
    private ImageView greenBoxImage;
    
    @FXML
    private ImageView pinkBoxImage;
    
    @FXML
    private ImageView lightPinkBoxImage;
    
    @FXML
    private Label sundayLabel;

    @FXML
    private Label mondayLabel;

    @FXML
    private Label tuesdayLabel;
    
    @FXML
    private Label wednesdayLabel;
    
    @FXML
    private Label thursdayLabel;
    
    @FXML
    private Label fridayLabel;
    
    @FXML
    private Label saturdayLabel;
    
    void calculateWeek() {
    	String dayOfWeek = scheduleDatePicker.getValue().getDayOfWeek().name();
    	LocalDate startOfWeek = null;
    	if (dayOfWeek.equals("SUNDAY")) {
    		startOfWeek = selectedDate.minusDays(0);
    	} else if (dayOfWeek.equals("MONDAY")){
    		startOfWeek = selectedDate.minusDays(1);
    	} else if (dayOfWeek.equals("TUESDAY")){
    		startOfWeek = selectedDate.minusDays(2);
    	} else if (dayOfWeek.equals("WEDNESDAY")){
    		startOfWeek = selectedDate.minusDays(3);
    	} else if (dayOfWeek.equals("THURSDAY")){
    		startOfWeek = selectedDate.minusDays(4);
    	} else if (dayOfWeek.equals("FRIDAY")){
    		startOfWeek = selectedDate.minusDays(5);
    	} else if (dayOfWeek.equals("SATURDAY")){
    		startOfWeek = selectedDate.minusDays(6);
    	}
    	selectedWeek = new Week(startOfWeek);
    }
    
    @FXML
    void followingWeek(ActionEvent event) {
    	selectedDate = selectedDate.plusWeeks(1);
    	update();
    }

    @FXML
    void previousWeek(ActionEvent event) {
    	selectedDate = selectedDate.minusWeeks(1);
    	update();
    }
    
    void setWeek() {
    	selectedDate = scheduleDatePicker.getValue();
    	update();
    }
    
    @FXML
    void switchMainMenuView(ActionEvent event) {
    	applicationStage.setScene(mainMenuView);
    	applicationStage.setTitle("Main Menu");
    }
    
    
    public void generateButton(Sleep sleep, boolean isAffectedByDebt, int column) {
    	
    	Time bededTime = new Time(sleep.getStartTime());
    	Time awakeTime = new Time(sleep.getEndTime());
    	int bedRowIndex = 0;
    	int awakeRowIndex = 0;
    	Button goalSleepButton = new Button("Somthin broke");
    	int latestDay = 0;
    	Time sleepDebt;
    	// get sleep debt per Day
    	for (Day i : selectedWeek.getDaysOfWeek()) {
    		for (Day j : user.getDiary()) {
    			// If the day of the week has an entry in the users diary
    			if (i.getDate().equals(j.getDate())){
    				latestDay = selectedWeek.getDaysOfWeek().indexOf(i)+1;
    			}
    		}
    	}
    	if (isAffectedByDebt) {
    		sleepDebt = selectedWeek.calculateSleepDebt(user);
    	} else {
    		sleepDebt = new Time(0,0);
    	}
    	int sleepDebtInMinutes = sleepDebt.getHours() * 60 + sleepDebt.getMinutes();
    	int sleepDebtPerDayInMinutes = sleepDebtInMinutes / (7-latestDay);
    	Time sleepDebtPerDay = new Time(sleepDebtPerDayInMinutes/ 60, sleepDebtPerDayInMinutes % 60);
    	
    	
    	// Fix times to be in 24 hour format
    	if (bededTime.getPeriod().equals("pm") && bededTime.getHours() != 12){
    		bededTime.setHours(bededTime.getHours()+12);
    	} else if (bededTime.getPeriod().equals("am") && bededTime.getHours() == 12) {
    		bededTime.setHours(bededTime.getHours()+12);
    	}
    	if (awakeTime.getPeriod().equals("pm") && awakeTime.getHours() != 12){
    		awakeTime.setHours(awakeTime.getHours()+12);
    	} else if (awakeTime.getPeriod().equals("am") && awakeTime.getHours() == 12) {
    		awakeTime.setHours(awakeTime.getHours()+12);
    	}
    	
    	// Calculate bed time Row Index
    	if (bededTime.getHours() >= 12) {
    		bedRowIndex = bededTime.getHours() - 11;
    	} else {
    		bedRowIndex = bededTime.getHours() + 13;
    	}
    	
    	// Calculate awake time Row Index
    	if (awakeTime.getHours() >= 12) {
    		awakeRowIndex = awakeTime.getHours() - 11;
    	} else {
    		awakeRowIndex = awakeTime.getHours() + 13;
    	}
    	
    	// SAVE variables
    	int bedRowSave = bedRowIndex;
    	Time bedTimeSave = new Time(bededTime);
    	// Draw the buttons to the scene
    	// reset variables for each loop
    	bedRowIndex = bedRowSave;
    	bededTime = new Time(bedTimeSave);
    	
    	// We need to do different things depending on if we are after or before the latest day in the users diary
    	// This is where we account for sleep debt if the day is after the latest in the diary.
    	if (column > latestDay) {
    		bededTime.setHours(bededTime.getHours()-sleepDebtPerDay.getHours());
    		bededTime.setMinutes(bededTime.getMinutes()-sleepDebtPerDay.getMinutes());
    		// If sleep is negative that means we went back at least one hour, so we need to adjust hours and minutes so they are not negative
    		if (bededTime.getMinutes() < 0) {
    			bedRowIndex -= sleepDebtPerDay.getHours()+1;
    			bededTime.setHours(bededTime.getHours()-Math.abs(bededTime.getMinutes() /60 + 1));
    			bededTime.setMinutes(bededTime.getMinutes()+(60 * Math.abs(bededTime.getMinutes() /60 + 1)));
    		} else {
    			bedRowIndex -= sleepDebtPerDay.getHours();
    		}
    		
    		goalSleepButton = new Button(bededTime.printTimeFormat(false) + " - \n" + awakeTime.printTimeFormat(false));
    	// sleep debt does not apply if we are before or equal to the latest day in the diary
    	} else if (column <= latestDay) {
    		goalSleepButton = new Button(bededTime.printTimeFormat(false) + " - \n" + awakeTime.printTimeFormat(false));
    	}
    	
    	// margin to account for bed time minutes
    	GridPane.setMargin(goalSleepButton, new Insets(28*((bededTime.getMinutes())/60.0),0,0,0));
    	// set the height of the button based on the awake time hours and minutes,                  					  Here we account for the bedtime margin. 		
    	goalSleepButton.setMinHeight(Math.abs(awakeRowIndex - bedRowIndex) * 28 + ((28*(awakeTime.getMinutes()/60.0))-(28*(bededTime.getMinutes()/60.0))));
    	GridPane.setValignment(goalSleepButton, VPos.TOP);
    	GridPane.setHalignment(goalSleepButton, HPos.LEFT);
    	goalSleepButton.setMinWidth(90);
    	
    	if (sleep.getType().equals("Main Sleep")){
    		goalSleepButton.setStyle("-fx-background-color: #0080FF;");
    	} else if (sleep.getType().equals("Nap")) {
    		goalSleepButton.setStyle("-fx-background-color: #00FFBB;");
    	} else if (sleep.getType().equals("recommended")) {
    		goalSleepButton.setStyle("-fx-background-color: #FF98DD;");
    	}
    	gridPane.add(goalSleepButton, column, bedRowIndex);   	
    }
    
    public void generateSchedule() {
    	int matchingDayFlag = 1;
    	for (Day i : selectedWeek.getDaysOfWeek()) {
    		for (Day j : user.getDiary()) {
    			// If the day of the week has an entry in the users diary
    			if (i.getDate().equals(j.getDate())){
    				matchingDayFlag = selectedWeek.getDaysOfWeek().indexOf(i)+2;
    				System.out.println("up here");
    				for (Sleep sleepPeriod : j.getSleepPeriods()) {
    					generateButton(sleepPeriod, false, selectedWeek.getDaysOfWeek().indexOf(i)+1);
    				}
    			}
    		}
    	}
    	
    	for (int x = matchingDayFlag; x < 8; x++) {
			generateButton(new Sleep("recommended", user.getGoalBedTime(), user.getGoalAwakeTime(), "neutral"), true, x);
    	}
    }
    
    
    public void update() {
    	
    	calculateWeek();	
		weekRange.setText(selectedWeek.toString());
		scheduleDatePicker.setValue(selectedDate);
    	sundayLabel.setText("Sunday " + selectedWeek.getDaysOfWeek().get(0).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(0).getDate().getDayOfMonth());
    	mondayLabel.setText("Monday " + selectedWeek.getDaysOfWeek().get(1).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(1).getDate().getDayOfMonth());
    	tuesdayLabel.setText("Tueday " + selectedWeek.getDaysOfWeek().get(2).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(2).getDate().getDayOfMonth());
    	wednesdayLabel.setText("Wednesday " + selectedWeek.getDaysOfWeek().get(3).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(3).getDate().getDayOfMonth());
    	thursdayLabel.setText("Thursday " + selectedWeek.getDaysOfWeek().get(4).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(4).getDate().getDayOfMonth());
    	fridayLabel.setText("Friday " + selectedWeek.getDaysOfWeek().get(5).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(5).getDate().getDayOfMonth());
    	saturdayLabel.setText("Saturday " + selectedWeek.getDaysOfWeek().get(6).getDate().getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(6).getDate().getDayOfMonth());
    	
    	for (Node node : gridPane.getChildren()) {
    		if (node instanceof VBox) {
    			node.setStyle(null);
    			node.setStyle("-fx-border-color: #E8E8E8;");
    			int dayofWeekValue = 0;
    			
    			if (selectedDate.getDayOfWeek().getValue() == 7) {
    				dayofWeekValue = 1;
    			} else {
    				dayofWeekValue = selectedDate.getDayOfWeek().getValue()+1;
    			}
    			
    			if (GridPane.getColumnIndex(node) == dayofWeekValue) {
        			node.setStyle("-fx-background-color: #e6fcff; -fx-border-color: #E8E8E8;");
        			node.setViewOrder(1);
        		}
    		} 
    		if (node instanceof Button) {
    			node.setOpacity(0);
    		}
    	}
    	generateSchedule();
    	// Code for fixing blur in about text area
    	aboutTextArea.setCache(false);
		ScrollPane sp = (ScrollPane) aboutTextArea.getChildrenUnmodifiable().get(0);
		sp.setCache(false);
		for (Node n : sp.getChildrenUnmodifiable()) {
		    n.setCache(false);
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		scheduleDatePicker.setOnAction(event -> setWeek());
		DatePickerSkin datePickerSkin = new DatePickerSkin(scheduleDatePicker);
		Node popupContent = datePickerSkin.getPopupContent();
		
		LocalDate x = scheduleDatePicker.getValue();
		scheduleVBox.getChildren().add(0,popupContent);
		
		for (int i = 0; i < gridPane.getColumnCount(); i++) {
			for (int j = 0; j < gridPane.getRowCount(); j++) {
				VBox vbox = new VBox();
				vbox.setStyle("-fx-border-color: #E8E8E8;");
				gridPane.add(vbox, i, j);
			}
		}
		
		
		calculateWeek();	
		weekRange.setText(selectedWeek.toString());
		
		try {
			// Blue Box
			FileInputStream blueBoxInputStream = new FileInputStream("src/bluebox.jpg");
			Image bluebox = new Image(blueBoxInputStream);
			blueBoxImage.setImage(bluebox);
			
			// Green Box
			FileInputStream greenBoxInputStream = new FileInputStream("src/greenbox.jpg");
			Image greenbox = new Image(greenBoxInputStream);
			greenBoxImage.setImage(greenbox);
			
			// Pink Box
				FileInputStream pinkBoxInputStream = new FileInputStream("src/pinkbox.jpg");
				Image pinkbox = new Image(pinkBoxInputStream);
				pinkBoxImage.setImage(pinkbox);
				
			// light Pink Box
			FileInputStream lightPinkBoxInputStream = new FileInputStream("src/lightpinkbox.jpg");
			Image lightPinkbox = new Image(lightPinkBoxInputStream);
			lightPinkBoxImage.setImage(lightPinkbox);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		
		
	}

    
}
