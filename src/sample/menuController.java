package sample;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import playerinfosearch.*;
import dto.*;
import Network.NetworkUtil;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class menuController {
private Main main;
private String clubname;
private String userName;
private NetworkUtil n;
    @FXML
    private MenuItem Player_name;

    @FXML
    private MenuItem Country_Name;

    @FXML
    private MenuItem Position;
    @FXML
    private MenuItem salaryrange;
    @FXML
    private Button countryplayercount;
    @FXML
    private MenuItem height;

    @FXML
    private MenuItem totalsalary;

    @FXML
    private MenuItem salary;

    @FXML
    private MenuItem age;
    @FXML
    private Button logout;
    @FXML
    private Button sellbutton;

    @FXML
    private Button BuyButton;
    @FXML
    private Button playerlistButton;

    @FXML
    private ImageView image;
    @FXML
    private Label menutext;

    public void init(String c) {
        menutext.setText(c);

    }





    @FXML
    void playerlistButtonAction(ActionEvent event) {


       /* try {

            playerListMessage msg = new playerListMessage();
            msg.setName(clubname);
            msg.setNumber(1);
            main.getNetworkUtil().write(msg);
        } catch (Exception e) {
            System.out.println(e);
        }*/
        try {
            GetListMessage getListMessage = new GetListMessage();
            getListMessage.setName(clubname);
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
                @FXML
                void sellAction (ActionEvent event){
                    try {

                        main.showSellTableView();
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

                @FXML
                void BuyButtonAction (ActionEvent event){
                    new Thread(() -> {

                        BuyListMessage buyListMessage = new BuyListMessage();
                        buyListMessage.setName(clubname);
                        try {

                            main.getNetworkUtil().write(buyListMessage);


                        } catch (Exception e) {
                            System.out.println(e);
                        }
                    }).start();
                }
                @FXML
                void logoutAction (ActionEvent event){

                    try {

                        main.showLoginPage();
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
                @FXML
                void salaryAction (ActionEvent event)  {
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Club clubdata=new Club();
                    List<Player> Player_list=new ArrayList<>();
                    Player_list = clubdata.search_max_sal(clubname);
                    try {
                        main.showTablePage( Player_list );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }



                }
                @FXML
                void ageAction (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Club clubdata=new Club();
                    List<Player> Player_list=new ArrayList<>();
                    Player_list = clubdata.search_max_age(clubname);
                    try {
                        main.showTablePage( Player_list );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }


                @FXML
                void heightAction (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Club clubdata=new Club();
                    List<Player> Player_list=new ArrayList<>();
                    Player_list = clubdata.search_max_height(clubname);
                    try {
                        main.showTablePage( Player_list );
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }
                @FXML
                void countryplayercountAction (ActionEvent event){

                            try {

                                playerListMessage msg=new playerListMessage();
                                msg.setName(clubname);
                                msg.setNumber(0);
                                main.getNetworkUtil().write(msg);


                            } catch (Exception e) {
                                System.out.println(e);
                            }


                        }

                @FXML
                void salaryrangeAction (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {

                        main.showTablePage("Salary Range (High,Low)");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                @FXML
                void searchCountryName (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {

                        main.showTablePage("Country Name");
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }

                @FXML
                void searchPlayerName (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }

                    try {

                        main.showTablePage("Player Name");
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }

                @FXML
                void searchPosition (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    try {

                        main.showTablePage("Position");
                    } catch (Exception e) {
                        System.out.println(e);
                    }

                }


                @FXML
                void totalsalaryAction (ActionEvent event){
                    try {
                        GetListMessage getListMessage = new GetListMessage();
                        getListMessage.setName(clubname);
                        main.getNetworkUtil().write(getListMessage);

                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    Club clubdata=new Club();

                    double total= clubdata.search_total_ys(clubname);

                    main.showAlert1(total);



                }

                void setMain (Main main, String clubname){
                    this.main = main;
                    this.clubname = clubname;
                }
                void setNetworkutil (NetworkUtil n)
                {
                    this.n = n;
                }

            }
