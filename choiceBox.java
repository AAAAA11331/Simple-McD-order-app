import javafx.application.Application;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
/*
 * colName.setCellValueFactory(new PropertyValueFactory<Player, String>("Name"));
 ***this must match the name of the field***
 */
public class choiceBox extends Application {

	ChoiceBox<String> choice;
	ChoiceBox<Baller> player;
	Label label,lblplayer;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		choice= new ChoiceBox<String>();
		choice.getItems().addAll("school1","school2","school3","school4");
		choice.setValue("school1");
		
		Baller jokic=new Baller("Nicola","Jokic");
		Baller young=new Baller("Trea","Young");
		Baller harden=new Baller("James","Harden");
		
		player=new ChoiceBox<Baller>();
		player.getItems().addAll(jokic,young,harden);
		
		// create a button
		Button btnOK=new Button("OK");
		btnOK.setOnAction(e -> btnOK_Click());
		
		label=new Label("Not selected");
		lblplayer=new Label("");
		
		HBox pane = new HBox(10,choice,btnOK,label,player,lblplayer);
		
		Scene scene=new Scene(pane, 350,200);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("Who knows");
		primaryStage.show();

	}

	private void btnOK_Click() {
		label.setText(choice.getValue());
		label.setText(player.getValue().toString());
	}

	public static void main(String[] args) {
		launch(args);

	}

}
