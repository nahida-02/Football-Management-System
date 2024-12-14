package sample;
import dto.GetListMessage;
import javafx.application.Platform;
import javafx.scene.control.Label;
import playerinfosearch.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;

public class PlayerlistTableViewController {
private Main main;
private String club;
    @FXML
    private Label playerclubLabel;

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
    private Button detailButton;



    @FXML
    private Button backbutton;

    @FXML
    void refreshbuttonAction(ActionEvent event) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                try {
                    GetListMessage getListMessage = new GetListMessage();
                    getListMessage.setName(club);
                    main.getNetworkUtil().write(getListMessage);

                } catch (Exception e) {
                    System.out.println(e);
                }
                try {
                    main.showPlayerListTablePage();
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

void init(String s)
{
    playerclubLabel.setText(s);
}

    @FXML
    void detailButtonAction(ActionEvent event) {

        ObservableList<Player> sellplayer= FXCollections.observableArrayList();
        sellplayer = tableview.getSelectionModel().getSelectedItems();
        Player g = sellplayer.get(0);
        try {
            main.showDetail(g,0);
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

    public void setMain(Main main,String name) {
        this.main=main;club=name;
    }

}


