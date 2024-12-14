package sample;

import dto.BuyListMessage;
import dto.BuyObject;
import playerinfosearch.Player;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BuyTableViewController {
    private Main main;
    private String club;
    BuyObject obj=new BuyObject();
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
    private TableColumn<Player, Double> price;
    @FXML
    private Button BuyButton;
    @FXML
    private Button back;
    @FXML
    private Button RefreshButton;
    @FXML
    private Label labelButton;
    @FXML
    void detailButtonAction(ActionEvent event) {

        ObservableList<Player> sellplayer= FXCollections.observableArrayList();
        sellplayer = tableview.getSelectionModel().getSelectedItems();
        Player g = sellplayer.get(0);
        try {
            main.showDetail(g,1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void BuyButtonAction(ActionEvent event) {


        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                ObservableList<Player> sellplayer= FXCollections.observableArrayList();



                sellplayer = tableview.getSelectionModel().getSelectedItems();
                Player g = sellplayer.get(0);


                removeplayer(g);
                System.out.println(club);
                g.setClubname(club);
                System.out.println(g.getName()+"   "+g.getClubname());
                Main.playerList.add(g);
                Main.sellplayerList.add(g);
                obj.setBuyObject(g);

              try {
                    main.showBuyTableView();
                } catch (Exception e) {
                    e.printStackTrace();
                }


                try {
                    main.getNetworkUtil().write(obj);



                } catch (IOException e) {
                    e.printStackTrace();
                }

            }});}



    private synchronized void removeplayer(Player g) {
        List<Player> toRemove=new ArrayList<>();
        for(Player p: Main.buyList)
            if(g.getName().equalsIgnoreCase(p.getName()))

                toRemove.add(p);
        Main.buyList.removeAll(toRemove);

    }



   @FXML
     void RefreshButtonAction(ActionEvent event)
   {
       new  Thread(()->{

           BuyListMessage buyListMessage = new BuyListMessage();
           buyListMessage.setName(club);
           try {

               main.getNetworkUtil().write(buyListMessage);


           } catch (Exception e) {
               System.out.println(e);
           }
       }).start();
     }



    @FXML
    void backAction(ActionEvent event) {

        try {

            main.showHomePage();
        } catch (Exception e) {
            System.out.println(e);
        }
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

        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        tableview.setItems(list);

    }
    public void setMain(Main main,String club) {
        this.main=main;
        this.club=club;
    }
}
