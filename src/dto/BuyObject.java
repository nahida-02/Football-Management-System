package dto;

import playerinfosearch.Player;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class BuyObject implements Serializable {
    private List<Player> object=new ArrayList<>();

    public void setBuyObject(Player p)
    {object=new ArrayList<>();
        object.add(p);}
    public List<Player> getBuyObject()
    {return object;}

}
