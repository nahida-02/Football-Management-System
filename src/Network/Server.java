package Network;

//import util.NetworkUtil;
import sample.*;
import playerinfosearch.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.List;

public class Server {
   public static List<Player>selllist=new ArrayList<>();//players  on sell
    public static List<Player> playerList = new ArrayList();/// all player
    public static List<Player> clubplayerList = new ArrayList();
    private ServerSocket serverSocket;
    public HashMap<String, String> userMap;
    public static List<NetworkUtil> clientinfo=new ArrayList<>();
    public List<NetworkUtil>getClientinfo()
    {return clientinfo;}

    public List<Player>getPlayerList()
    {return playerList;}

    Server() {
        userMap = new HashMap<>();
        userMap.put("Manchester United", "1");
        userMap.put("Liverpool", "1");
        userMap.put("Manchester City", "1");
        userMap.put("Chelsea", "1");
        userMap.put("Arsenal", "1");
        try {
            serverSocket = new ServerSocket(33333);
            this.playerList = readFromFile();

            while (true) {


                Socket clientSocket = serverSocket.accept();
                serve(clientSocket);
            }
        } catch (Exception e) {
            System.out.println("Server starts:" + e);
        }
    }

    public void serve(Socket clientSocket) throws IOException {
        NetworkUtil networkUtil = new NetworkUtil(clientSocket);
        new ReadThreadServer(userMap, networkUtil);
    }

    public static void main(String[] args) {
        new Server();
    }

    private static final String INPUT_FILE_NAME = "players.txt";
    private static final String OUTPUT_FILE_NAME = "players.txt";

    public static List<Player> readFromFile() throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(INPUT_FILE_NAME));

        while (true) {
            String line = br.readLine();
            if (line == null) break;
            String[] tokens = line.split(",");
            Player s = new Player();
            s.setName(tokens[0].trim());
            s.setCountry(tokens[1].trim());
            s.setAge(Integer.parseInt(tokens[2].trim()));
            s.setHeight(Double.parseDouble(tokens[3].trim()));
            s.setClubname(tokens[4].trim());
            s.setPosition(tokens[5].trim());
            s.setNumber(Integer.parseInt(tokens[6].trim()));
            s.setWeekly_salary(Double.parseDouble(tokens[7].trim()));


            playerList.add(s);

        }

        br.close();
        return playerList;
    }


}