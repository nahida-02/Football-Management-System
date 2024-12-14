package sample;
import dto.GetListMessage;
import javafx.application.Platform;
import javafx.scene.control.Label;
import playerinfosearch.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import java.util.ArrayList;
import java.util.List;

public class TablePlayerCountController {
    private Main main;
    private String clubname;
    @FXML
    private TableView <Country> Viewtableplayer;
    @FXML
    private TableColumn<Country, String> countryname;

    @FXML
    private TableColumn<Country, Integer> playercountno;
    @FXML
    private Button backbutton;
    @FXML
    private Button refreshbutton;
    @FXML
    private Label label;


    void init(String s){label.setText(s+" Player Count");}

    @FXML
    void backbuttonAction(ActionEvent event) {
        try {

            main.showHomePage();
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    @FXML
    void refreshbuttonAction(ActionEvent event) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GetListMessage getListMessage = new GetListMessage();
                    getListMessage.setName(clubname);
                    main.getNetworkUtil().write(getListMessage);

                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    main.showTablePlayerCount();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }




public void initiate(List<Player> playerList)
{Database data=new Database();
    List<Country> Plt=new ArrayList<>();
    Plt= data.search_countrywiseplayer(playerList);

        System.out.println();
    initializeColumns(Plt);
}
    public void initializeColumns(List<Country> lis) {
        ObservableList<Country> list = FXCollections.observableArrayList(lis);



        countryname.setCellValueFactory(new PropertyValueFactory<>("countryname"));
        playercountno.setCellValueFactory(new PropertyValueFactory<>("playercountno"));



        Viewtableplayer.setItems(list);

    }

    public void setMain(Main main,String name) {this.main=main;
    clubname=name;
    }








}

