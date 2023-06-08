import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MenuDemo extends Application {

	MenuBar menuBar;
	BorderPane layout;
	Stage stage;
	@Override
	public void start(Stage primaryStage) throws Exception {
		stage=primaryStage;
		
		Menu foodMenu=new Menu("Food");
		// creaete menu item for the food menu
		foodMenu.getItems().add(new MenuItem("Hamburger"));
		foodMenu.getItems().add(new MenuItem("Cheeseburger"));
		foodMenu.getItems().add(new MenuItem("noburger"));
		foodMenu.getItems().add(new MenuItem("bbburger"));
		
		// main menu bar
		menuBar=new MenuBar();
		menuBar.getMenus().addAll(foodMenu);
		
		layout=new BorderPane();
		layout.setTop(menuBar);
		
		Scene scene=new Scene(layout,450,300);
		stage.setScene(scene);
		stage.setTitle("");
		stage.show();

	}

	public static void main(String[] args) {
		launch(args);

	}

}
