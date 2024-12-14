package sample;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import playerinfosearch.Player;

import java.io.FileInputStream;

public class DetailViewController {
    private Main main;
    int m;

    @FXML
    private ImageView image;
    @FXML
    private Label Price;

    @FXML
    private Label pricelabel;

    @FXML
    private Label playername;

    @FXML
    private Label club;

    @FXML
    private Label country;

    @FXML
    private Label height;

    @FXML
    private Label age;

    @FXML
    private Label position;

    @FXML
    private Label number;

    @FXML
    private Label weeklysalry;


    @FXML
    void backAction(ActionEvent event ) {
if(m==0) {
    try {
        main.showPlayerListTablePage();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

if(m==1) {
    try {
        main.showBuyTableView();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

if(m==2) {
    try {
        main.showSellTableView();
    } catch (Exception e) {
        e.printStackTrace();
    }
}


    }



    public void setMain(Main main) {
        this.main = main;
    }
    public void setno(int m) {
        this.m = m;
    }

    public void init(Player g, int m) {

        playername.setText(g.getName());
        club.setText(g.getClubname());
        country.setText(g.getCountry());
        height.setText(g.getHeight() + "");
        age.setText(g.getAge() + "");
        position.setText(g.getPosition());
        number.setText(g.getNumber() + "");
        weeklysalry.setText(g.getWeekly_salary() + "");
        if (m == 1) {
            Price.setText("Price");
            pricelabel.setText(g.getPrice() + "");

        }
    }


}