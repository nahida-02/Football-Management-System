package sample;
import Network.*;
import playerinfosearch.*;
import java.io.IOException;
import java.text.DecimalFormat;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.Region;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import static Network.Server.readFromFile;
import static Network.Server.selllist;


public class Main extends Application {
    public static List<Player> playerList = new ArrayList();
    public static List<Player> buyList = new ArrayList();

    public static List<Player> sellplayerList= new ArrayList() ;

    String cl;


    public void setCl(String cl) {
        this.cl = cl;
    }

    private Stage stage;

    public Stage getStage() {
        return stage;
    }
    private NetworkUtil networkUtil;

public void setlist(List<Player>playerList,List<Player>sellList)
{
    this.playerList=playerList;
    sellplayerList=sellList;
   // System.out.println(sellList.size());


}

    public void setplayerlist(List<Player>playerList)
    {
        this.playerList=playerList;



    }
    public void setbuylist(List<Player> clientList) {
    this.buyList=clientList;

}


    public NetworkUtil getNetworkUtil() {
        return networkUtil;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        connectToServer();
        showLoginPage();


    }
    private void connectToServer() throws IOException {
        String serverAddress = "127.0.0.1";
        int serverPort = 33333;
        networkUtil = new NetworkUtil(serverAddress, serverPort);
        new ReadThread(this);
    }


    public List<Player> getList() {
        return playerList;
    }



    public void showLoginPage() throws Exception {
        // XML Loading using FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Login1.fxml"));
        Parent root = loader.load();

        // Loading the controller
        LoginController controller = loader.getController();
        controller.setMain(this);
        controller.setNetworkutil(networkUtil);
        // Set the primary stage
        stage.setTitle("Login");
        stage.setScene(new Scene(root, 530, 579));
        stage.setResizable(false);
        stage.show();
    }

    public void showHomePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/menu.fxml"));
        Parent root = loader.load();

        // Loading the controller
        menuController controller = loader.getController();
        controller.init(cl);
        controller.setMain(this,cl);

        // Set the primary stage
        stage.setTitle("Home");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }
    public void showPlayerListTablePage() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/PlayerlistTableView.fxml"));
        Parent root = loader.load();
        PlayerlistTableViewController controller = loader.getController();
      controller.setMain(this,cl);

        controller.init(cl);
        controller.initializeColumns(playerList);
        stage.setTitle("Table View Player List");
        Scene scene=new Scene(root, 1100, 639);

        stage.setScene(scene);

        stage.show();
    }
    public void showTablePage(String user) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/tableview.fxml"));
        Parent root = loader.load();
        TableViewController controller = loader.getController();
        controller.setMain(this);

        controller.init(user);
        stage.setTitle("Table View");
        stage.setScene(new Scene(root, 1100, 505));
        stage.show();
    }

    public void showTablePage(List<Player> p) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/tableview.fxml"));
        Parent root = loader.load();
        TableViewController controller = loader.getController();
        controller.setMain(this);
        //controller.load();
        controller.initializeColumns(p);
        stage.setTitle("Table View");
        stage.setScene(new Scene(root, 1100, 505));
        stage.show();
    }


    public void showTablePlayerCount() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/tablePlayerCount.fxml"));
        Parent root = loader.load();
        TablePlayerCountController controller = loader.getController();

        controller.setMain(this,cl);
        controller.init(cl);
        controller.initiate(playerList);

        stage.setTitle("Table View Player Count");
        stage.setScene(new Scene(root, 633, 464));
        stage.show();
    }
    public void showSellTableView() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/SellTableView.fxml"));
        Parent root = loader.load();
        SellTableViewController controller = loader.getController();

        controller.setMain(this);


        controller.initializeColumns(sellplayerList);
        controller.init(cl);
        stage.setTitle("Table View Sell");
        stage.setScene(new Scene(root, 1100, 639));
        stage.show();
    }

    public void showBuyTableView() throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/BuyTableView.fxml"));
        Parent root = loader.load();
        BuyTableViewController controller = loader.getController();

        controller.setMain(this,cl);


        controller.initializeColumns(buyList);

        stage.setTitle("Table View Buy");
        stage.setScene(new Scene(root, 1100, 700));
        stage.show();
    }

    public void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("The username and password you provided is not correct.");
        alert.showAndWait();
    }
    public void showAlert1() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("No Player in the Buy List");
        alert.showAndWait();
    }
    public void showAlert2() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText("Incorrect Credentials");
        alert.setContentText("Input price of the player");
        alert.showAndWait();
    }


    public void showAlert1(double total) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"Content here", ButtonType.OK);
        //alert.setTitle("Incorrect Credentials");
        alert.setHeaderText(" Total Yearly Salary");
      //  alert.getDialogPane().setPrefSize(480,320);
        DecimalFormat formatter=new DecimalFormat("0.00");
        String d = formatter.format(total);
        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
        alert.setContentText("Total Yearly Salary : "+d);
        alert.showAndWait();
    }
    public void showDetail(Player g,int m) throws Exception {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/sample/DetailView.fxml"));
        Parent root = loader.load();
        DetailViewController controller = loader.getController();

        controller.setMain(this);
        controller.setno(m);
        controller.init(g,m);



        stage.setTitle("Table View");
        stage.setScene(new Scene(root, 600, 700));
        stage.show();
    }

    public static void main(String[] args) {
        // This will launch the JavaFX application
        launch(args);


    }


}
