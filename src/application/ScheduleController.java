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
    private Label MondayLabel;

    @FXML
    private Label tuesdayLabel;
    
    @FXML
    private Label wednesdayLabel;
    
    @FXML
    private Label thurdayLabel;
    
    @FXML
    private Label FridayLabel;
    
    @FXML
    private Label saturdayLabel;
    
    @FXML
    void followingWeek(ActionEvent event) {
    	System.out.println(gridPane.getChildren().size());
    }

    @FXML
    void previousWeek(ActionEvent event) {

    }
    
    public void update() {
    	aboutTextArea.setCache(false);
		ScrollPane sp = (ScrollPane) aboutTextArea.getChildrenUnmodifiable().get(0);
		sp.setCache(false);
		for (Node n : sp.getChildrenUnmodifiable()) {
		    n.setCache(false);
		}
    }
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		DatePicker scheduleDatePicker = new DatePicker(LocalDate.now());
		DatePickerSkin datePickerSkin = new DatePickerSkin(scheduleDatePicker);
		Node popupContent = datePickerSkin.getPopupContent();
		scheduleVBox.getChildren().add(0,popupContent);
		
		for (int i = 0; i < gridPane.getColumnCount(); i++) {
			for (int j = 0; j < gridPane.getRowCount(); j++) {
				VBox vbox = new VBox();
				vbox.setStyle("-fx-border-color: #E8E8E8;");
				gridPane.add(vbox, i, j);
			}
		}
			
		
		
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
