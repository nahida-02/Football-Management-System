package dto;
import sample.*;
import playerinfosearch.Player;
import java.io.Serializable;
import java.util.List;

public class GetListResponseMessage implements Serializable {
    private List<Player> clientList;
    private List<Player> sellList;

    public List<Player> getClientList1() {
        return clientList;
    }
    public List<Player> getClientList2() {
        return sellList;
    }
    public void setClientList(List<Player> clientList,List<Player> sellList) {
        this.clientList = clientList;
        this.sellList = sellList;

    }
}
