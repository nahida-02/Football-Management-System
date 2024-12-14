package dto;

import playerinfosearch.Player;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class PlayerListResponseMessage implements Serializable{


private int number;
        private List<Player> clientList;
public int getNumber()
{return number;}

        public void setNumber(int number)
        {this.number=number;}
        public List<Player> getClientList() {
            return clientList;
        }


        public void setClientList(List<Player> clientList) {
            this.clientList = clientList;

        }

    }
