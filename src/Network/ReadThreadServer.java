package Network;
import dto.*;
import sample.*;
import playerinfosearch.*;
//import util.LoginDTO;
//import util.NetworkUtil;

import javafx.application.Platform;
import playerinfosearch.*;

import java.io.*;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static Network.Server.*;


public class ReadThreadServer implements Runnable {
    private final Thread thr;
    private final NetworkUtil networkUtil;
    public HashMap<String, String> userMap;
    List<Player> editedselllist = new ArrayList<>();
    List<Player> m1=new ArrayList<>();
    List<Player> temp=new ArrayList<>();

    Server obj = new Server();

    public ReadThreadServer(HashMap<String, String> map, NetworkUtil networkUtil) {
        this.userMap = map;
        this.networkUtil = networkUtil;
        this.thr = new Thread(this);
        thr.start();

    }

    public void run() {
        try {
            while (true) {
                Object o = networkUtil.read();


                if (o != null) {
                    if (o instanceof LoginDTO) {
                        new Thread(() -> {

                            LoginDTO loginDTO = (LoginDTO) o;
                            String password = userMap.get(loginDTO.getUserName());
                            loginDTO.setStatus(loginDTO.getPassword().equals(password));
                            try {
                                networkUtil.write(loginDTO);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                           //clientinfo.add(networkUtil);
                        }).start();
                    }
                    if (o instanceof GetListMessage) {
                        new Thread(() -> {
                            String s = ((GetListMessage) o).getName();
                            List<Player> clientList = search(s);
                            List<Player> temp = searchonsell(s);



                            GetListResponseMessage getListResponseMessage = new GetListResponseMessage();
                            getListResponseMessage.setClientList(clientList, temp);

                            try {
                                networkUtil.write(getListResponseMessage);
                            }catch(SocketException e){}
                            catch(ClassCastException e)
                            {}
                            catch(EOFException e){

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }
                    if (o instanceof playerListMessage) {
                        new Thread(() -> {
                            String s = ((playerListMessage) o).getName();
                            int n=((playerListMessage) o).getNumber();
                            List<Player> clientList = search(s);




                            PlayerListResponseMessage Message = new PlayerListResponseMessage();
                            Message.setClientList(clientList);
                            Message.setNumber(n);

                            try {
                                networkUtil.write(Message);
                            } catch(EOFException e){

                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }).start();
                    }

                if (o instanceof BuyListMessage) {



                    new Thread(() -> {
                        BuyListMessage buy = (BuyListMessage) o;
                        String s = buy.getName();
                        editedselllist = Listsearch(s);
                        BuyListResponseMessage buyListResponseMessage = new BuyListResponseMessage();

                        buyListResponseMessage.setClientList(editedselllist);


                        try {

                            networkUtil.write(buyListResponseMessage);

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }).start();


                }








                if (o instanceof Player) {
                    Player p = (Player) o;


                    selllist.add(p);


                }




                if (o instanceof BuyObject) {


                    List<Player> p = ((BuyObject) o).getBuyObject();
                    Player k = p.get(0);


                    String s = k.getClubname();

                    UddateDatabase(k, s);


                    Removefromsell( k);




                }

               }}

        }catch(StreamCorruptedException e){}
        catch(EOFException e){}
        catch (OptionalDataException e){}
        catch(UTFDataFormatException e){}
        catch (SocketException e){}
        catch (Exception e) {
            System.out.println(e);

        }finally {
            try {
                networkUtil.closeConnection();
            }catch (SocketException e){}
            catch (IOException e) {
                e.printStackTrace();

    }}}
public synchronized void Removefromsell(Player k)
    {
         for(int i=0;i< Server.selllist.size();i++)
    {    Player t= Server.selllist.get(i);
        if(t.getName().equalsIgnoreCase(k.getName()))
            Server.selllist.remove(t);
    }}


    private synchronized void UddateDatabase(Player p,String s) {

        for(Player player: playerList)
        {if(player.getName().equalsIgnoreCase(p.getName()))
            player.setClubname(s);

        }

    }


    public synchronized List<Player> searchonsell(String s) {
        List <Player>m1=new ArrayList<>();
        List<Player> m=search(s);

if(Server.selllist.isEmpty())
    return m;



     for (int i=0;i<m.size();i++) {
          Player p=m.get(i);
          int j;
            for(j=0; j<Server. selllist.size(); j++) {
               Player g= Server.selllist.get(j);
                if ((p.getName().equalsIgnoreCase(g.getName())))
                    break;


            }  if (j == Server.selllist.size())
             m1.add(p);

        }


        return m1;


    }

    private synchronized List<Player> Listsearch(String s) {
        List<Player> editedlist=new ArrayList<>();
        for(Player p: selllist)
            if(!(p.getClubname().equalsIgnoreCase(s)))
                editedlist.add(p);

return editedlist;

    }


    public synchronized List<Player> search(String cl_ub) {
       int playercount = 0;
       int clubfound = -1;
        List<Player> m=new ArrayList<>();

        for (int i = 0; i < obj.getPlayerList().size(); i++) {
            Player t = obj.getPlayerList().get(i);
            if (t.getClubname().equalsIgnoreCase(cl_ub)) {

                m.add(t);
                clubfound = 1;
            }
        }
        return m;
    }


}




