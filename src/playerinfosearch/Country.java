package playerinfosearch;

import java.io.Serializable;

public class Country implements Serializable {
    String countryname;
    int playercountno;

    Country(String countryname,int playercountno)
    {
        this.countryname=countryname;
        this.playercountno=playercountno;
    }

    public void setCountry(String countryname){this.countryname=countryname;}
    public void setplayercount(int playercountno){this.playercountno=playercountno;}


   public String getCountryname()
    {return countryname;}
   public int getPlayercountno()
    {return playercountno;}



}
