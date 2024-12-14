package dto;

import java.io.Serializable;

public class playerListMessage implements Serializable {
    String name;
    int number;

    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }


    public void setName(String name) {
        this.name = name;
    }
    public void setNumber(int number) {
        this.number = number;
    }
}
