package playerinfosearch;
import sample.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class Database {
    Main obj = new Main();
    List<Player> list = obj.getList();

    public List<Player> Namesearch(String searchName) {
        int searchIndex = -1;
List<Player> pl=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Player t = list.get(i);
            if (t.getName().equalsIgnoreCase(searchName)) {


                pl.add(t);
                return pl;

            }
        }
        if (searchIndex == -1)
            System.out.println("No such player with this name");

        System.out.println();
        return pl;
    }


    public List<Player> Country(String searchCountry) {

        List<Player> pl=new ArrayList<>();
            int searchIndex = -1;
            for (int i = 0; i < list.size(); i++) {
                Player t = list.get(i);
                if (t.getCountry().equalsIgnoreCase(searchCountry)) {

                  pl.add(t);
                    searchIndex = 1;
                }
            }

            if (searchIndex == -1) {
                System.out.println("No such player with this country");
            }
            return pl;
        }





    public List<Player> Position_search(String searchPosition) {
        List<Player> pl=new ArrayList<>();
        int searchIndex = -1;

        for (int i = 0; i < list.size(); i++) {
            Player t = list.get(i);

            if (t.getPosition().equalsIgnoreCase(searchPosition)) {

            pl.add(t);
                searchIndex = 1;
            }

        }

        if (searchIndex == -1) {
            System.out.println("No such player with this position");
        }
       return pl;
    }


    public List<Player> Salary_search(Double high, Double low) {
        int searchIndex = -1;
        List<Player> pl=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Player t = list.get(i);

            if (t.getWeekly_salary() >= low && t.getWeekly_salary() <= high) {

                searchIndex = 1;
                pl.add(t);


            }

        }
        if (searchIndex == -1) {
            System.out.println("No such player with this Weekly salary range");

        }

        return pl;
    }


    public  List<Country> search_countrywiseplayer(List<Player> list) {
        List<Country> pl=new ArrayList<>();
        int oc;
        List<String> play = new ArrayList();
        play.add(list.get(0).getCountry());

        for (Player s : list) {
            oc = 0;
            for (String t : play) {
                if (s.getCountry().equalsIgnoreCase(t))
                    oc++;
            }

            if (oc == 0)
                play.add(s.getCountry());
        }

        for (String t : play) {
            int co = 0;
            for (Player s : list) {
                if (s.getCountry().equalsIgnoreCase(t))
                    co++;
            }

            Country c=new Country(t,co);
            pl.add(c);
        }
        System.out.println();
        return pl;
    }

}