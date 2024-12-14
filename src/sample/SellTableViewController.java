package sample;
import Network.NetworkUtil;
import javafx.scene.control.*;
import playerinfosearch.Player;
import Network.Server;
import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.*;

public class SellTableViewController {
    private Main main;
    private NetworkUtil networkUtil;
    @FXML
    private TableView<Player> tableview;

    @FXML
    private TableColumn<Player, String> name;

    @FXML
    private TableColumn<Player, String> clubname;

    @FXML
    private TableColumn<Player, String> country;

    @FXML
    private TableColumn<Player, Integer> age;

    @FXML
    private TableColumn<Player, Double> height;

    @FXML
    private TableColumn<Player, String> position;

    @FXML
    private TableColumn<Player, Integer> number;

    @FXML
    private TableColumn<Player, Double> weekly_salary;

    @FXML
    private Button SellButton;

    @FXML
    private Button backbutton;

    @FXML
    private Label labelButton;
    @FXML
    private TextField selllabelButton;

    void init(String s)
    {
        labelButton.setText(s+" "+"SELL REQUEST");
    }
    @FXML
    void detailButtonAction(ActionEvent event) {

        ObservableList<Player> sellplayer= FXCollections.observableArrayList();
        sellplayer = tableview.getSelectionModel().getSelectedItems();
        Player g = sellplayer.get(0);
        try {
            main.showDetail(g,2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void backbuttonAction(ActionEvent event) {

        try {

            main.showHomePage();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @FXML
    void SellButtonAction(ActionEvent event) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
    ObservableList<Player> sellplayer= FXCollections.observableArrayList();


String i=selllabelButton.getText();
if(i.isEmpty())
    main.showAlert2();

    sellplayer = tableview.getSelectionModel().getSelectedItems();
    Player g = sellplayer.get(0);
    g.setPrice(Double.parseDouble(i));
    removeplayer(g);
    try {
        main.showSellTableView();
    } catch (Exception e) {
        e.printStackTrace();
    }

    try {
        main.getNetworkUtil().write(g);
    } catch (IOException e) {
        e.printStackTrace();
    }

}});}


    private synchronized void removeplayer(Player g) {
        List<Player> toRemove=new ArrayList<>();
        for(Player p: Main.sellplayerList)
            if(g.getName().equalsIgnoreCase(p.getName()))

                toRemove.add(p);
            Main.sellplayerList.removeAll(toRemove);

    }

    public void initializeColumns(List<Player> lis) {
        ObservableList<Player> list = FXCollections.observableArrayList(lis);


        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        clubname.setCellValueFactory(new PropertyValueFactory<>("clubname"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        age.setCellValueFactory(new PropertyValueFactory<>("age"));
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        number.setCellValueFactory(new PropertyValueFactory<>("number"));
        weekly_salary.setCellValueFactory(new PropertyValueFactory<>("weekly_salary"));


        tableview.setItems(list);

}

    public void setMain(Main main) {
        this.main=main;
    }
    public void setNetworkutil(NetworkUtil networkUtil) {
        this.networkUtil=networkUtil;
    }
}

