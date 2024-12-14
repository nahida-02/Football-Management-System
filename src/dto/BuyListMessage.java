package dto;

import playerinfosearch.Player;

import java.io.Serializable;
import java.util.List;

public class BuyListMessage implements Serializable {
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
