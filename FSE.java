import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import javafx.application.Application;
import javafx.collections.*;
import javafx.geometry.*;
import javafx.geometry.Pos;
import javafx.stage.*;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.control.cell.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.*;

public class FSE extends Application {

	boolean found = false;
	BorderPane paneB;
	BorderPane mainpane;
	Button goback = new Button("<back");
	Button signin = new Button("Sign in");
	Button logout = new Button("Logout");
	Button order_detail = new Button("Order Detail");
	Button createacc;
	ComboBox<String> food;
	ComboBox<Integer> firesqu, hamqu, cheesequ, cokequ, sprqu, cusburqu;
	CheckBox cbcucu, cbtomato, cbonion, cblettuce;
	double total = 0;
	DecimalFormat df2 = new DecimalFormat("#.##");
	Image cheeseburImg = new Image(
			"https://raw.githubusercontent.com/AAAAA11331/pictures/AAAAA11331-patch-1/cheeseburger.jpeg");
	ImageView viewcb = new ImageView(cheeseburImg);
	Image cokeImg = new Image("https://raw.githubusercontent.com/AAAAA11331/pictures/AAAAA11331-patch-1/coke.jpeg");
	ImageView cokeview = new ImageView(cokeImg);
	Image firesImg = new Image("https://raw.githubusercontent.com/AAAAA11331/pictures/AAAAA11331-patch-1/fries.jpeg");
	ImageView firesview = new ImageView(firesImg);
	Image hamburImg = new Image(
			"https://raw.githubusercontent.com/AAAAA11331/pictures/AAAAA11331-patch-1/hamhurger.jpeg");
	ImageView hamburview = new ImageView(hamburImg);
	Image McLogoImg = new Image("https://raw.githubusercontent.com/AAAAA11331/pictures/AAAAA11331-patch-1/mcdo.png");
	ImageView McLogoview = new ImageView(McLogoImg);
	Image spriteImg = new Image("https://raw.githubusercontent.com/AAAAA11331/pictures/AAAAA11331-patch-1/sprite.jpeg");
	ImageView spriteview = new ImageView(spriteImg);
	int userId;
	Label totalCost;
	MenuBar menubar;
	ObservableList<Order> data = FXCollections.observableArrayList();
	RadioButton rbbeef, rbchicken, rbsausage, rbcheese;
	String[] userInfo = new String[7];
	String welcome;
	Scene infoScene;
	Stage stage;
	Scene mainScene;
	Scene signInScene;
	Scene burgerScene;
	Scene sidesScene;
	Scene checkOutScene;
	TableView<Order> table = new TableView<Order>();
	TextField firesneed, hamneed, cheeseneed, cokeneed, sprneed;
	TextField username;
	TextField password;
	TextField newuname, newpsword;
	TextField txfname, txlname, txpn, txaddrnum, txaddrst;
	TextField txtName, txtnum, txtsn;
	VBox checkOutPane;
	VBox p;

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;

		Label mcd = new Label("Welcome to McDonald");
		mcd.setFont(new Font("Monocpaced", 25));

		// create a "menu"
		Menu foodmenu = new Menu("__Menu");

		// burger
		MenuItem burger = new MenuItem("Burgers");
		burger.setOnAction(e -> burgerPage());
		foodmenu.getItems().add(burger);

		// beverage
		MenuItem Beverage = new MenuItem("Beverages");
		Beverage.setOnAction(e -> beveragePage());
		foodmenu.getItems().add(Beverage);

		// sides
		MenuItem sides = new MenuItem("Sides");
		sides.setOnAction(e -> sidesPage());
		foodmenu.getItems().add(sides);

		// Create menu bar
		menubar = new MenuBar();
		menubar.getMenus().add(foodmenu);

		checkLogin();

		// set order_detail button-- go to check out page
		order_detail.setOnAction(e -> checkOutPage());

		// set mc logo
		McLogoview.setFitWidth(300);
		McLogoview.setPreserveRatio(true);

		VBox vb = new VBox(10, mcd, McLogoview);
		// set menubar on the top
		mainpane = new BorderPane();
		mainpane.setTop(menubar);
		mainpane.setCenter(vb);
		p = new VBox(5, order_detail, signin);
		mainpane.setRight(p);

		// main Scene
		mainScene = new Scene(mainpane, 450, 350);
		primaryStage.setScene(mainScene);
		// show confirmation box when click exit
		primaryStage.setOnCloseRequest(e -> {
			e.consume();
			// call the confirmation box
			boolean confirm = showConf("Are you sure?", "Confirmation", "Yes", "No");
			if (confirm) {
				primaryStage.close();
			}
		});
		primaryStage.show();

	}

	// create sign in page
	private void signInPage() {
		Button login = new Button("Login");
		login.setOnAction(e -> {
			try {
				login();
			} catch (Exception ex) {
				showmess("You entered bad data. Run the program again", "Error");
			}

		});
		Button signUp = new Button("Sign up");
		signUp.setOnAction(e -> signup());

		goback.setOnAction(e -> toMainScene());
		Label lblusername = new Label("User name");
		Label lblpassword = new Label("Password");
		username = new TextField();
		username.setMinWidth(200);
		username.setMaxWidth(200);
		username.setPromptText("Enter your username here");
		password = new TextField();
		password.setMinWidth(200);
		password.setMaxWidth(200);
		password.setPromptText("Enter your password here");

		BorderPane pane = new BorderPane();
		pane.setLeft(goback);
		HBox boxusername = new HBox(20, lblusername, username);
		HBox boxpassword = new HBox(20, lblpassword, password);
		VBox button = new VBox(10, login, signUp);
		VBox signin = new VBox(50, boxusername, boxpassword, button);
		pane.setCenter(signin);

		signInScene = new Scene(pane, 400, 300);
		stage.setScene(signInScene);
		stage.show();

	}

	// create sign up page
	private void signup() {
		goback.setOnAction(e -> toMainScene());
		createacc = new Button("Create");
		createacc.setOnAction(e -> {
			try {
				checkInfo();
			} catch (IOException e1) {
				showmess("You entered bad data. Run the program again", "Error");
			}
		});
		newuname = new TextField();
		newuname.setPromptText("Create your username, numbers only");
		newuname.setMinWidth(300);
		Label lbl1 = new Label("Username");
		HBox b1 = new HBox(10, lbl1, newuname);
		newpsword = new TextField();
		newpsword.setPromptText("Create your password, at least 8 digits");
		newpsword.setMinWidth(300);
		Label lbl2 = new Label("Password");
		HBox b2 = new HBox(10, lbl2, newpsword);
		txfname = new TextField();
		txfname.setPromptText("Enter your first name");
		txfname.setMinWidth(300);
		Label lbl3 = new Label("First name");
		HBox b3 = new HBox(10, lbl3, txfname);
		txlname = new TextField();
		txlname.setPromptText("Enter Your last name");
		txlname.setMinWidth(300);
		Label lbl4 = new Label("Last name");
		HBox b4 = new HBox(10, lbl4, txlname);
		txpn = new TextField();
		txpn.setPromptText("Enter your phone number. 10 digits, only number, no '-' ");
		txpn.setMinWidth(300);
		Label lbl5 = new Label("Pnone number");
		HBox b5 = new HBox(10, lbl5, txpn);
		txaddrnum = new TextField();
		txaddrnum.setPromptText("Enter you House number");
		txaddrnum.setMinWidth(300);
		Label lbl6 = new Label("House number");
		HBox b6 = new HBox(10, lbl6, txaddrnum);
		txaddrst = new TextField();
		txaddrst.setPromptText("Enter the street name of your address");
		txaddrst.setMinWidth(300);
		Label lbl7 = new Label("Street name");
		HBox b7 = new HBox(10, lbl7, txaddrst);

		VBox signup = new VBox(20, goback, b1, b2, b3, b4, b5, b6, b7, createacc);
		Scene scene = new Scene(signup, 500, 500);
		stage.setScene(scene);
		stage.show();

	}

	// generate confirm page and info before checkout
	double allsum;
	Stage stage2 = new Stage();

	private void checkOutP() {
		String info;
		Label infop = new Label();
		Button confirm = new Button("Confirm");
		confirm.setOnAction(e -> {
			try {
				confirm();
			} catch (IOException e1) {
				showmess("Something goes wrong", "Oops");
			}
		});
		if (!found) {
			showmess("You need to login first", "Sorry");
			signInPage();
			stage.setScene(signInScene);
		} else if (data.isEmpty())
			showmess("You have nothing in your order", "Oh No");
		else {
			double sum = 0.0;
			info = "Dear " + userInfo[2] + " " + userInfo[3] + ": \n" + "You have ordered: \n\n";
			for (Order o : data) {
				info += o.getQuantity() + " x " + o.getOrder_Name() + "  " + o.getSpecial_Need() + "\n";
				sum += o.getMoney();
			}
			double total2 = Double.parseDouble(df2.format(sum * 0.13));
			double total1 = Double.parseDouble(df2.format(sum));
			allsum = Double.parseDouble(df2.format(total2 + total1));
			info += "\nTotal: $" + total1 + "\nTaxes: $" + total2 + "\nTotal after taxes: $" + allsum;
			infop.setText(info);
			VBox vb = new VBox(20, infop, confirm);
			Scene scene = new Scene(vb, 400, 600);
			stage2.setScene(scene);
			stage2.show();

		}

	}

	// generate a info page
	private void info() throws IOException {
		boolean work = false;
		Scanner inFile = new Scanner(new BufferedReader(new FileReader("userInfo.txt")));
		while (!work) {
			userInfo = inFile.nextLine().split(" ");
			if (Integer.parseInt(userInfo[0]) == userId) {
				work = true;
				break;
			}
		}
		inFile.close();

		goback.setOnAction(e -> toMainScene());
		Label lblname = new Label("Name: " + userInfo[2] + " " + userInfo[3]);
		lblname.setFont(new Font("Arial", 20));
		Label lblphnum = new Label("Phone Number: " + userInfo[4]);
		lblphnum.setFont(new Font("Arial", 20));
		Label lbladdr = new Label("Address: " + userInfo[5] + " " + userInfo[6]);
		lbladdr.setFont(new Font("Arial", 20));

		VBox infom = new VBox(20, goback, lblname, lblphnum, lbladdr);
		infoScene = new Scene(infom, 500, 300);
		stage.setScene(infoScene);
		stage.setTitle("User Profolio");
		stage.show();

	}

	// check out Page
	private void checkOutPage() {
		// creating labels, buttons
		totalCost = new Label("Total: $" + df2.format(total));
		totalCost.setFont(new Font("Arial", 20));
		totalCost.setTextFill(Color.RED);
		checkTotal();
		Button bncheckout = new Button("Check out");
		bncheckout.setOnAction(e -> checkOutP());
		goback.setOnAction(e -> toMainScene());
		Label lblHeading = new Label("Order detail");
		lblHeading.setFont(new Font("Arial", 20));

		// creating table
		table.setItems(data);
		TableColumn<Order, String> colName = new TableColumn("Order_Name");
		colName.setMinWidth(300);
		colName.setCellValueFactory(new PropertyValueFactory<Order, String>("Order_Name"));
		TableColumn<Order, Integer> colnum = new TableColumn("Quantity");
		colnum.setMinWidth(50);
		colnum.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Quantity"));
		TableColumn<Order, String> colsn = new TableColumn("Special_Need");
		colsn.setMinWidth(380);
		colsn.setCellValueFactory(new PropertyValueFactory<Order, String>("Special_Need"));
		TableColumn colmon = new TableColumn("Money");
		colmon.setMinWidth(100);
		colmon.setCellValueFactory(new PropertyValueFactory<Order, Integer>("Money"));
		table.getColumns().addAll(colName, colnum, colsn, colmon);

		// add stuff
		food = new ComboBox<String>();
		food.getItems().addAll("Custome Burger", "Hamburger", "Cheeseburger", "Coke", "Sprite", "Fries");
		food.setPromptText("Choose one");
		txtnum = new TextField();
		txtnum.setMaxWidth(100);
		txtnum.setPromptText("Quantity");
		txtsn = new TextField();
		txtsn.setMaxWidth(100);
		txtsn.setPromptText("Special need");
		Button btnAdd = new Button("Add");
		btnAdd.setMinWidth(60);
		btnAdd.setOnAction(e -> {
			try {
				btnAdd_Clicked();
			} catch (Exception e1) {
				showmess("You entered bad data. Run the program again", "Error");
			}
		});
		Button btnDelete = new Button("Delete");
		btnDelete.setMinWidth(60);
		btnDelete.setOnAction(e -> {
			try {
				btnDelete_Clicked();
			} catch (Exception e1) {
				showmess("Something goes wrong, try again", "Oops");
			}
		});

		// boxes
		HBox paneAdd = new HBox(8, food, txtnum, txtsn, btnAdd, btnDelete);
		HBox paneAdd1 = new HBox(30, paneAdd, totalCost, bncheckout);
		VBox paneMain = new VBox(10, lblHeading, table, paneAdd1);
		paneMain.setPadding(new Insets(10, 10, 10, 10));
		checkOutPane = new VBox(10, goback, lblHeading, table, paneMain);
		checkOutPane.setPadding(new Insets(10, 10, 10, 10));
		checkOutScene = new Scene(checkOutPane, 900, 500);
		stage.setScene(checkOutScene);
		stage.show();

	}

	// show beverageScene when click "__Mune"-->"Beverage"
	private void beveragePage() {
		Label lblcoke=new Label("Coke  $2.99 each");
		lblcoke.setFont(new Font("Monospaced",15));
		Label lblspr=new Label("Sprite  $2.99 each");
		lblspr.setFont(new Font("Monospaced",15));
		cokequ = new ComboBox<Integer>();
		cokequ.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cokequ.setPromptText("Quantity");
		cokeneed = new TextField();
		cokeneed.setPromptText("Special need");
		sprqu = new ComboBox<Integer>();
		sprqu.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		sprqu.setPromptText("Quantity");
		sprneed = new TextField();
		sprneed.setPromptText("Special need");
		// set go back button
		goback.setOnAction(e -> toMainScene());
		Button ordercoke = new Button("Order");
		ordercoke.setOnAction(e -> {
			try {
				ordercoke();
			} catch (Exception e1) {
				showmess("You need to choose a quantity", "Error");
			}
		});
		Button orderspr = new Button("Order");
		orderspr.setOnAction(e -> {
			try {
				orderspr();
			} catch (Exception e1) {
				showmess("You need to choose a quantity", "Error");
			}
		});
		cokeview.setFitWidth(300);
		cokeview.setFitHeight(250);
		spriteview.setFitWidth(200);
		spriteview.setFitHeight(250);
		HBox co = new HBox(10, ordercoke, cokequ);
		VBox coke = new VBox(10, cokeview,lblcoke, co, cokeneed);
		HBox sp = new HBox(10, orderspr, sprqu);
		VBox sprite = new VBox(10, spriteview,lblspr, sp, sprneed);

		HBox beverbox = new HBox(150, coke, sprite);
		BorderPane pane = new BorderPane();
		pane.setCenter(beverbox);
		pane.setLeft(goback);
		pane.setRight(p);
		sidesScene = new Scene(pane, 800, 500);
		stage.setScene(sidesScene);
		stage.show();
	}

	// show sidesScene when click "__Mune"-->"sides"
	private void sidesPage() {
		Label lblfr=new Label("Fries  $1.99 each");
		lblfr.setFont(new Font("Monospaced",15));
		firesqu = new ComboBox<Integer>();
		firesqu.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		firesqu.setPromptText("Quantity");
		firesneed = new TextField();
		firesneed.setPromptText("Special need");
		firesneed.setMaxWidth(300);
		// set go back button
		goback.setOnAction(e -> toMainScene());
		Button orderfires = new Button("Order");
		orderfires.setOnAction(e -> {
			try {
				orderFires();
			} catch (Exception e1) {
				showmess("You need to choose a quantity", "Error");
			}
		});
		firesview.setFitWidth(300);
		firesview.setFitHeight(200);
		HBox bu = new HBox(10, orderfires, firesqu);
		VBox buu = new VBox(10, bu, firesneed);
		VBox fires = new VBox(10, firesview,lblfr, buu);
		BorderPane pane = new BorderPane();
		pane.setCenter(fires);
		pane.setLeft(goback);
		pane.setRight(p);
		sidesScene = new Scene(pane, 800, 500);
		stage.setScene(sidesScene);
		stage.show();
	}

	// show burgerScene when click "__Mune"-->"Burger"
	private void burgerPage() {
		Label lblham=new Label("Hamburger  $5.99 each");
		lblham.setFont(new Font("Monospaced",15));
		Label lblchee=new Label("Cheeseburger  $5.99 each");
		lblchee.setFont(new Font("Monospaced",15));
		hamqu = new ComboBox<Integer>();
		hamqu.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		hamqu.setPromptText("Quantity");
		hamneed = new TextField();
		hamneed.setPromptText("Special need");
		cheesequ = new ComboBox<Integer>();
		cheesequ.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		cheesequ.setPromptText("Quantity");
		cheeseneed = new TextField();
		cheeseneed.setPromptText("Special need");
		// set go back button
		goback.setOnAction(e -> toMainScene());
		Button orderhambur = new Button("Order");
		orderhambur.setOnAction(e -> {
			try {
				orderhambur();
			} catch (Exception e1) {
				showmess("You need to choose a quantity", "Error");
			}
		});
		Button ordercheesebur = new Button("Order");
		ordercheesebur.setOnAction(e -> {
			try {
				ordercheesebur();
			} catch (Exception e1) {
				showmess("You need to choose a quantity", "Error");
			}
		});
		Button orderotherbur = new Button("Custome your own burger");
		orderotherbur.setOnAction(e -> cusbur());
		hamburview.setFitWidth(300);
		hamburview.setFitHeight(250);
		viewcb.setFitHeight(250);
		viewcb.setFitWidth(250);
		HBox bb = new HBox(10, orderhambur, hamqu);
		VBox hambur = new VBox(10, hamburview,lblham, bb, hamneed);
		HBox cb = new HBox(10, ordercheesebur, cheesequ);
		VBox cheesebur = new VBox(10, viewcb,lblchee, cb, cheeseneed);
		HBox burgerbox = new HBox(100, hambur, cheesebur);
		paneB = new BorderPane();
		paneB.setLeft(goback);
		paneB.setRight(p);
		paneB.setCenter(burgerbox);
		paneB.setBottom(orderotherbur);
		burgerScene = new Scene(paneB, 800, 500);
		stage.setScene(burgerScene);
		stage.show();

	}

	// custome burger page
	private void cusbur() {
		Button cusbur = new Button("Order");
		cusbur.setOnAction(e -> ordercusbur());
		// set go back button
		goback.setOnAction(e -> toBurgerPage());
		cusburqu = new ComboBox<Integer>();
		cusburqu.setPromptText("Quantity");
		cusburqu.getItems().addAll(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		Label cusburger = new Label("Custume your own Burger");
		Label type = new Label("Type");
		cusburger.setFont(new Font("Arial", 20));
		rbbeef = new RadioButton("Beef");
		rbchicken = new RadioButton("Chicken");
		rbsausage = new RadioButton("Sausage");
		rbcheese = new RadioButton("Cheese");
		ToggleGroup typeGroup = new ToggleGroup();
		rbbeef.setToggleGroup(typeGroup);
		rbchicken.setToggleGroup(typeGroup);
		rbsausage.setToggleGroup(typeGroup);
		rbcheese.setToggleGroup(typeGroup);
		rbbeef.setSelected(true);

		Label extra = new Label("Extra");
		cbcucu = new CheckBox("Cucumber");
		cbtomato = new CheckBox("Tomato");
		cbonion = new CheckBox("Onion");
		cblettuce = new CheckBox("Lettuce");

		VBox extras = new VBox(40, extra, cbcucu, cbtomato, cbonion, cblettuce);
		VBox typegroup = new VBox(40, type, rbbeef, rbchicken, rbsausage, rbcheese);
		HBox allbox = new HBox(80, typegroup, extras);
		VBox typegr = new VBox(30, goback, cusburger, allbox, cusburqu, cusbur);
		BorderPane other = new BorderPane();
		other.setLeft(typegr);
		other.setRight(p);
		Scene otherbur = new Scene(other, 400, 500);
		stage.setScene(otherbur);
		stage.show();

	}

	// add fires to orderpage
	private void orderFires() throws Exception {
		data.add(new Order("Fries", firesqu.getValue(), firesneed.getText(),
				Double.parseDouble(df2.format(2.99 * firesqu.getValue()))));
		orderAdded();
	}

	// order cheese burger
	private void ordercheesebur() throws Exception {
		data.add(new Order("Cheeseburger", cheesequ.getValue(), cheeseneed.getText(),
				Double.parseDouble(df2.format(5.99 * cheesequ.getValue()))));
		orderAdded();
	}

	// order hamburger
	private void orderhambur() throws Exception {
		data.add(new Order("Hamburger", hamqu.getValue(), hamneed.getText(),
				Double.parseDouble(df2.format(5.99 * hamqu.getValue()))));
		orderAdded();
	}

	// order customer burger
	private void ordercusbur() {
		String type = "";
		String extra = "";
		if (rbbeef.isSelected())
			type = "Beef Burger";
		else if (rbchicken.isSelected())
			type = "Chicken Burger";
		else if (rbsausage.isSelected())
			type = "Sausage Burger";
		else if (rbcheese.isSelected())
			type = "Cheese Burger";
		else
			showmess("You need to select a type", "Error");

		if (cbcucu.isSelected())
			extra += "Cucumber ";
		if (cbtomato.isSelected())
			extra += "Tomato ";
		if (cbonion.isSelected())
			extra += "Onion ";
		if (cblettuce.isSelected())
			extra += "Lettuce ";
		data.add(new Order("Custome Burger", cusburqu.getValue(), type + " with " + extra,
				Double.parseDouble(df2.format(7.99 * cusburqu.getValue()))));
		orderAdded();
	}

	// order sprite
	private void orderspr() throws Exception {
		data.add(new Order("Sprite", sprqu.getValue(), sprneed.getText(),
				Double.parseDouble(df2.format(1.99 * sprqu.getValue()))));
		orderAdded();
	}

	// order coke
	private void ordercoke() throws Exception {
		data.add(new Order("Coke", cokequ.getValue(), cokeneed.getText(),
				Double.parseDouble(df2.format(1.99 * cokequ.getValue()))));
		orderAdded();
	}

	// for goback button when they need to go to the main Scene
	private void toMainScene() {
		if (!mainpane.getChildren().contains(p))
			mainpane.setRight(p);
		stage.setScene(mainScene);
		stage.show();
	}

	// return to burger page
	private void toBurgerPage() {
		if (!paneB.getChildren().contains(p)) {
			paneB.setRight(p);
			paneB.setLeft(goback);
		}
		burgerPage();
		stage.setScene(burgerScene);
		stage.show();
	}

	// print message
	private void orderAdded() {
		showmess("Your item has been successfully added into your order", "Congrats");
	}

	// update totalCost
	private void checkTotal() {
		double total = 0.0;
		for (Order o1 : data)
			total += o1.getMoney();
		totalCost.setText("Total is $" + df2.format(total));
	}

	// generate info and write a new file
	private void confirm() throws IOException {
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("orders.txt", true)));
		Date date = new Date();
		String output;
		output = "Machine: 0001 \n" + date.toString() + "\nUser ID: " + userInfo[0] + "\nUser name: " + userInfo[2]
				+ " " + userInfo[3] + "\n";
		for (Order o : data) {
			output += o.getQuantity() + " x " + o.getOrder_Name() + "  " + o.getSpecial_Need() + "\n";
		}
		output += "Total (after taxes): $" + allsum;
		outFile.println(output + "\n------------------------------------------");
		outFile.close();
		showmess("You are all set", "Congrats");
		data.clear();
		stage2.close();
		checkTotal();

	}

	// check if new info is correct
	private void checkInfo() throws IOException {
		boolean work = false;
		Scanner inFile = new Scanner(new BufferedReader(new FileReader("userInfo.txt")));
		PrintWriter outFile = new PrintWriter(new BufferedWriter(new FileWriter("userInfo.txt", true)));
		while (inFile.hasNextLine()) {
			userInfo = inFile.nextLine().split(" ");
			if (userInfo[0].equals(newuname.getText())) {
				work = false;
				showmess("Username has been used. Enter another one", "Error");
				newuname.clear();
			}
		}
		inFile.close();

		if (!(newuname.getText().matches("[0-9]+"))) {
			work = false;
			newuname.clear();
			showmess("Username: Accept number only", "Error");
		} else if (newpsword.getText().length() < 8) {
			work = false;
			newpsword.clear();
			showmess("Password: You need at least 8 digits password", "Error");
		} else if (!(txpn.getText().length() == 10) && !(txpn.getText().matches("[0-9]+"))) {
			work = false;
			txpn.clear();
			showmess("Phone number: You have bad data", "Error");
		} else if (!(txaddrnum.getText().matches("[0-9]+"))) {
			work = false;
			txaddrnum.clear();
			showmess("House Number: You have bad data", "Error");
		} else if (!(txaddrst.getText().matches("[a-zA-Z]+"))) {
			work = false;
			txaddrst.clear();
			showmess("Street Name: Accept letter only", "Error");
		} else
			work = true;

		if (work) {
			showmess("You have successfully create your account", "Congrats");
			outFile.println(newuname.getText() + " " + newpsword.getText() + " " + txfname.getText() + " "
					+ txlname.getText() + " " + txpn.getText() + " " + txaddrnum.getText() + " " + txaddrst.getText());
			userId = Integer.parseInt(newuname.getText());
			welcome = txfname.getText();
			signin.setText("Welcome " + welcome);
			found = true;
			p.getChildren().add(logout);
			logout.setOnAction(e -> {
				e.consume();
				boolean confirm = showConf("Do you want to logout?", "Logout", "Yes", "No");
				if (confirm) {
					found = false;
					signin.setText("Sign in");
					signin.setOnAction(e1 -> signInPage());
					p.getChildren().remove(logout);
					showmess("You are successfully logged out.", "Good");
				} else
					found = true;
			});
			stage.setScene(mainScene);

		}
		outFile.close();
	}

	// check if account is exist
	private void login() throws IOException {
		Scanner inFile = new Scanner(new BufferedReader(new FileReader("userInfo.txt")));

		while (!found && inFile.hasNextLine()) {
			userInfo = inFile.nextLine().split(" ");
			String user = (userInfo[0].toString());
			String pass = (userInfo[1].toString());
			if (user.equals((username.getText()).toString()) && pass.equals(password.getText())) {
				found = true;
				welcome = userInfo[2];
				userId = Integer.parseInt(userInfo[0]);
				showmess("You are successfully signed in", "Congrats");
				stage.setScene(mainScene);
			}
		}

		if (!found) {
			showmess("You entered incorrect information", "Wrong");
			username.clear();
			password.clear();
		} else {
			signin.setText("Welcome " + welcome);
			signin.setOnAction(e -> {
				try {
					info();
				} catch (IOException e1) {
					showmess("Something went wrong, try again", "Oops");
				}
			});
			p.getChildren().add(logout);
			logout.setOnAction(e -> {
				e.consume();
				boolean confirm = showConf("Do you want to logout?", "Logout", "Yes", "No");
				if (confirm) {
					signin.setText("Sign in");
					signin.setOnAction(e1 -> signInPage());
					found = false;
					p.getChildren().remove(logout);
					showmess("You are successfully logged out.", "Good");
				}
			});
		}
	}

	// check out page add button
	private void btnAdd_Clicked() throws Exception {
		double money = 0;
		if (food.getValue() == "Custome Burger")
			money = 7.99;
		else if (food.getValue() == "Hamburger")
			money = 5.99;
		else if (food.getValue() == "Cheeseburger")
			money = 5.99;
		else if (food.getValue() == "Coke")
			money = 1.99;
		else if (food.getValue() == "Sprite")
			money = 1.99;
		else if (food.getValue() == "Fries")
			money = 2.99;
		Order o = new Order(food.getValue(), Integer.parseInt(txtnum.getText()), txtsn.getText(),
				money * Integer.parseInt(txtnum.getText()));
		table.getItems().add(o);
		checkTotal();
		food.setPromptText("Choose another one");
		txtnum.clear();
		txtsn.clear();
	}

	// check out page delete button
	private void btnDelete_Clicked() throws Exception {
		ObservableList<Order> sel, items;
		items = table.getItems();
		sel = table.getSelectionModel().getSelectedItems();
		for (Order o : sel) {
			total = total - o.getMoney();
			items.remove(o);
			checkTotal();
		}
	}

	// MessageBox
	private static void showmess(String message, String title) {
		Stage stage2 = new Stage();
		stage2.initModality(Modality.APPLICATION_MODAL);
		stage2.setTitle(title);
		stage2.setMinWidth(250);
		Label lbl = new Label();
		lbl.setText(message);
		Button btnOK = new Button();
		btnOK.setText("OK");
		btnOK.setOnAction(e -> stage2.close());
		VBox pane = new VBox(20);
		pane.getChildren().addAll(lbl, btnOK);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage2.setScene(scene);
		stage2.showAndWait();
	}

	// confirmation box
	static Stage stage1;
	static boolean btnYesClicked;

	public static boolean showConf(String message, String title, String textYes, String textNo) {
		btnYesClicked = false;
		stage1 = new Stage();
		stage1.initModality(Modality.APPLICATION_MODAL);
		stage1.setTitle(title);
		stage1.setMinWidth(250);
		Label lbl = new Label();
		lbl.setText(message);
		Button btnYes = new Button();
		btnYes.setText(textYes);
		btnYes.setOnAction(e -> btnYes_Clicked());
		Button btnNo = new Button();
		btnNo.setText(textNo);
		btnNo.setOnAction(e -> btnNo_Clicked());
		HBox paneBtn = new HBox(200, btnYes, btnNo);
		VBox pane = new VBox(20, lbl, paneBtn);
		pane.setAlignment(Pos.CENTER);
		Scene scene = new Scene(pane);
		stage1.setScene(scene);
		stage1.showAndWait();
		return btnYesClicked;
	}

	private static void btnYes_Clicked() {
		stage1.close();
		btnYesClicked = true;
	}

	private static void btnNo_Clicked() {
		stage1.close();
		btnYesClicked = false;
	}

	private void checkLogin() {
		// set sign in button-- go to sign in page
		if (!found)
			signin.setOnAction(e -> signInPage());
		if (found)
			signin.setOnAction(e -> {
				try {
					info();
				} catch (IOException e1) {
					showmess("Something goes wrong", "Oops");
				}
			});
	}

	public static void main(String[] args) {
		launch(args);

	}

}
