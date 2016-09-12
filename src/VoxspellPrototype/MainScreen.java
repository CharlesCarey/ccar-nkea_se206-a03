package VoxspellPrototype;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class MainScreen extends Parent {

	private Window _window;
	private final int BUTTON_SEPERATION = 6; 
	private final int MENU_BAR_PADDING = 10;
	private final double MENUBAR_SCREENWIDTH_RATIO = 0.333;
	
	private String _displayText = "Hello World!";
	private final String BTN_NEW_TEXT = "New Quiz";
	private final String BTN_REVIEW_TEXT = "Review Mistakes";
	private final String BTN_STATS_TEXT = "View Stats";
	private final String BTN_CLEAR_TEXT = "Clear Stats";
	private final String BTN_QUIT_TEXT = "Quit";
	
	private final int TXT_FONT_SIZE = 28;
	private final int BTN_FONT_SIZE = 22;
	private final String BTN_COLOR = "#9ad3de";
	private final String BACK_COLOR = "#89bdd3";
	private final String BTN_FONT_COLOR = "#e3e3e3";
	private final String TXT_FONT_COLOR = "#e3e3e3";
	
	private final int TEXT_CEILING_SEPERATION = 160;
	
	
	public MainScreen(Window window) {
		super();
		
		this._window = window;
		
		// Create root node
		HBox root = new HBox(0);
		
		// Set root node size
		root.setPrefWidth(_window.GetWidth());
		root.setPrefHeight(_window.GetHeight());
		
		// Set root node color
		root.setStyle("-fx-background-color: " + BACK_COLOR + ";");
		
		// Create menu bar
		double menuBarWidth = _window.GetWidth() * MENUBAR_SCREENWIDTH_RATIO;
		Pane menuPane = buildMenuBar(menuBarWidth);
		
		Text welcomeText = new Text(_displayText);
		
		// Set text area width to that remaining of windows width after
		// menu bar width and padding is removed
		welcomeText.setWrappingWidth( root.getPrefWidth()
				- menuPane.getPrefWidth() 
				- menuPane.getPadding().getLeft() 
				- menuPane.getPadding().getRight());
		
		// Center align text
		welcomeText.setTextAlignment(TextAlignment.CENTER);
		
		// Set text y translation (distance from top of window to text)
		welcomeText.setTranslateY(TEXT_CEILING_SEPERATION);
		
		welcomeText.setStyle("-fx-font: " + TXT_FONT_SIZE + " arial;" +
				" -fx-fill: " + TXT_FONT_COLOR + ";");
		
		// Add menu bar and text to root node
		root.getChildren().addAll(menuPane, welcomeText);
		
		this.getChildren().add(root);
		
		this.setStyle("-fx-background-color: " + BACK_COLOR + ";");
	}
	
	private Pane buildMenuBar(double desiredWidth) {
		Button btnNew, btnReview, btnStats, btnClear, btnQuit;

		// Create vbox with specific dimensions
		VBox menuButtons = new VBox(BUTTON_SEPERATION);
		menuButtons.setPrefWidth(desiredWidth);
		menuButtons.setPrefHeight(_window.GetHeight());

		// Create buttons
		btnNew = new Button(BTN_NEW_TEXT);
		btnReview = new Button(BTN_REVIEW_TEXT);
		btnStats = new Button(BTN_STATS_TEXT);
		btnClear = new Button(BTN_CLEAR_TEXT);
		btnQuit = new Button(BTN_QUIT_TEXT);
		
		// Set button style properties
		btnNew.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnReview.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnStats.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnClear.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		btnQuit.setStyle("-fx-font: " + BTN_FONT_SIZE + " arial;" + 
				" -fx-base: " + BTN_COLOR + ";" + 
				" -fx-text-fill: " + BTN_FONT_COLOR + ";");
		
		// Set width and height of buttons
		btnNew.setMinWidth(menuButtons.getPrefWidth()); 
		btnNew.setPrefHeight(Integer.MAX_VALUE);
		
		btnReview.setMinWidth(menuButtons.getPrefWidth()); 
		btnReview.setPrefHeight(Integer.MAX_VALUE);
		
		btnStats.setMinWidth(menuButtons.getPrefWidth()); 
		btnStats.setPrefHeight(Integer.MAX_VALUE);
		
		btnClear.setMinWidth(menuButtons.getPrefWidth()); 
		btnClear.setPrefHeight(Integer.MAX_VALUE);
		
		btnQuit.setMinWidth(menuButtons.getPrefWidth()); 
		btnQuit.setPrefHeight(Integer.MAX_VALUE);
		
		// Add buttons to pane
		menuButtons.getChildren().addAll(btnNew, btnReview, btnStats, btnClear, btnQuit);
		
		// Add padding around vbox (so buttons don't touch screen edge)
		menuButtons.setPadding(new Insets(MENU_BAR_PADDING));
		
		// Define button actions
		btnNew.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new LevelSelectionScreen(_window), _window.GetWidth(), _window.GetHeight()));
			}	
		});
		
		btnReview.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				_window.SetWindowScene(new Scene(new LevelSelectionScreen(_window), _window.GetWidth(), _window.GetHeight()));
			}	
		});
		
		btnQuit.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				Platform.exit();
			}	
		});
		
		return menuButtons;
	}
}