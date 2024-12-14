package Network;
import sample.*;
import dto.*;
import playerinfosearch.*;

import javafx.application.Platform;
//import util.LoginDTO;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class ReadThread implements Runnable {
    private final Thread thr;
    private final Main main;
    List <Player> selllist=new ArrayList<>();

    public ReadThread(Main main) {
        this.main = main;
        this.thr = new Thread(this);
        thr.start();
    }

    public void run() {
        try {
            while (true) {
                Object o = main.getNetworkUtil().read();
                if (o != null) {
                    if (o instanceof LoginDTO) {
                        LoginDTO loginDTO = (LoginDTO) o;
                        System.out.println(loginDTO.getUserName());
                      // System.out.println(loginDTO.isStatus());
                        // the following is necessary to update JavaFX UI components from user created threads
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                              if (loginDTO.isStatus()) {
                                    try {
                                        main.showHomePage();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                              } else {
                                  main.showAlert();
                              }

                            }
                        });
                    }
                    if (o instanceof GetListResponseMessage) {
                        new Thread(()->{
                        GetListResponseMessage obj = (GetListResponseMessage) o;
                        List<Player> l = obj.getClientList2();

                        main.setlist(obj.getClientList1(),obj.getClientList2());
                    }).start();}


                    if (o instanceof PlayerListResponseMessage) {


                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                PlayerListResponseMessage obj = (PlayerListResponseMessage) o;
                                List<Player> l = obj.getClientList();
                               int n=obj.getNumber();


                                try {
                                    main.setplayerlist(obj.getClientList());


                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                if(n==0)
                                {  try {
                                    main.showTablePlayerCount();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }}
                           /*     if(n==1)
                                {
                                    try {
                                        main.showPlayerListTablePage();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                    }
                            */

                            }
                        });
                    }





                    if (o instanceof BuyListResponseMessage) {


                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {

                                BuyListResponseMessage obj = (BuyListResponseMessage) o;
                                List<Player> l = obj.getClientList();


                                if (l.isEmpty())
                                    main.showAlert1();


                                try {
                                    main.setbuylist(obj.getClientList());

                                    main.showBuyTableView();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }


                            }
                        });
                    }



                }
            }
        }catch(StreamCorruptedException e){}
        catch(EOFException e){}
        catch (OptionalDataException e){}
        catch(UTFDataFormatException e){}
        catch (SocketException e){}
        catch (Exception e) {
            System.out.println(e);
        }
        finally {
           try {
              main.getNetworkUtil().closeConnection();
           } catch (SocketException e){}
           catch (IOException e) {
                 e.printStackTrace();
            }
         }}


    }

