package sample;


import playerinfosearch.*;
import Network.*;
import dto.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.util.ArrayList;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class TableViewController {
    private Main main;
    List<Player> lis=new ArrayList<>();
    private boolean init = true;
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
    private Button backbutton;
    @FXML
    private Label searchboxLabel;

    @FXML
    private TextField SearchBox;

    @FXML
    private Button searchButton;

    @FXML
    private Button reset;
    @FXML
    public void searchboxAction(ActionEvent actionEvent) {
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
    void resetAction(ActionEvent event) {
        SearchBox.setText(null);
    }



    @FXML
    void searchButtonAction(ActionEvent event) {
        Database data=new Database();
        Club clubdata=new Club();
        List<Player> Player_list=new ArrayList<>();
    String userName = SearchBox.getText();

        if(searchboxLabel.getText().equalsIgnoreCase("Player Name")) {
            Player_list = data.Namesearch(userName);

        }

        if(searchboxLabel.getText().equalsIgnoreCase("Country Name")) {
            Player_list = data.Country(userName);

        }

        if(searchboxLabel.getText().equalsIgnoreCase("Position")) {
            Player_list = data.Position_search(userName);

        }

        if(searchboxLabel.getText().equalsIgnoreCase("Salary Range (High,Low)")) {
            String[] tokens = userName.split(",");
            Double m1=Double.parseDouble(tokens[0].trim());
            Double m2=Double.parseDouble(tokens[1].trim());
            Player_list = data.Salary_search(m1,m2);

        }
       // if(searchboxLabel.getText().equalsIgnoreCase("Country-wise Player Count")) {
        //    Player_list = data.Position_search(userName);

       // }
     /*   if(searchboxLabel.getText().equalsIgnoreCase("Club Name for Salary")) {
            Player_list = clubdata.search_max_sal(userName);

        }
        if(searchboxLabel.getText().equalsIgnoreCase("Club Name for Age")) {
            Player_list = clubdata.search_max_age(userName);

        }
        if(searchboxLabel.getText().equalsIgnoreCase("Club Name for Height")) {
            Player_list = clubdata.search_max_height(userName);

        }

        if(searchboxLabel.getText().equalsIgnoreCase("Club Name for Total Yearly Salary")) {
            {  double total= clubdata.search_total_ys(userName);

            main.showAlert1(total);

            }

        }*/
        initializeColumns(Player_list);
    }




    void setMain(List<Player> main) {
        this.lis = main;
        for(Player p:lis)
            System.out.println(p.getName());
    }

    public void init(String msg) {
        searchboxLabel.setText(msg);
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




    }

