package dto;

import playerinfosearch.Player;

import java.io.Serializable;
import java.util.List;

public class BuyListResponseMessage implements Serializable {
    private List<Player> clientList;

    public List<Player> getClientList() {
        return clientList;
    }

    public void setClientList(List<Player> clientList) {
        this.clientList = clientList;
    }
}
