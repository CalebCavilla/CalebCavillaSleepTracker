package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    
    public void update() {
    	
    	calculateWeek();	
		weekRange.setText(selectedWeek.toString());
		scheduleDatePicker.setValue(selectedDate);
    	sundayLabel.setText("Sunday " + selectedWeek.getDaysOfWeek().get(0).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(0).getDayOfMonth());
    	mondayLabel.setText("Monday " + selectedWeek.getDaysOfWeek().get(1).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(1).getDayOfMonth());
    	tuesdayLabel.setText("Tueday " + selectedWeek.getDaysOfWeek().get(2).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(2).getDayOfMonth());
    	wednesdayLabel.setText("Wednesday " + selectedWeek.getDaysOfWeek().get(3).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(3).getDayOfMonth());
    	thursdayLabel.setText("Thursday " + selectedWeek.getDaysOfWeek().get(4).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(4).getDayOfMonth());
    	fridayLabel.setText("Friday " + selectedWeek.getDaysOfWeek().get(5).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(5).getDayOfMonth());
    	saturdayLabel.setText("Saturday " + selectedWeek.getDaysOfWeek().get(6).getMonthValue() + "/" + selectedWeek.getDaysOfWeek().get(6).getDayOfMonth());
    	
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
    	}
    	
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
		System.out.println(x.getDayOfWeek());
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

    
}
