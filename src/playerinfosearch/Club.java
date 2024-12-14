package playerinfosearch;
import sample.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Club implements Serializable {
    private String name;
    private Player[] pla_yer;
    private int clubfound;
    private int playercount = 0;
    Main obj = new Main();


    public String getname() { return name; }

    public void setname() { this.name = name; }

    public int getplayercount() { return playercount; }

    public void setplayercount() { this.playercount = playercount; }


    public List<Player> search(String cl_ub) {
        playercount = 0;
        clubfound = -1;
        List<Player> m=new ArrayList<>();

        for (int i = 0; i < obj.getList().size(); i++) {
            Player t = obj.getList().get(i);
            if (t.getClubname().equalsIgnoreCase(cl_ub)) {

                m.add(t);
                clubfound = 1;
            }
        }
        return m;
    }

    public List<Player> search_max_sal(String cl_ub) {
        List<Player> play=new ArrayList<>();
        List<Player> pl= search(cl_ub) ;
        if (clubfound == 1) {
            Double max = pl.get(0).getWeekly_salary();
            for (int j = 1; j < pl.size(); j++)
                if (max < pl.get(j).getWeekly_salary())
                    max = pl.get(j).getWeekly_salary();
            for (int k = 0; k < pl.size(); k++) {
                if (pl.get(k).getWeekly_salary() == max)

            play.add(pl.get(k));

            }
        }
        else
            System.out.println("No such club with this name");
        System.out.println();
        return play;
    }


    public List<Player> search_max_age(String cl_ub) {
        List<Player> play=new ArrayList<>();
       List<Player> pl= search(cl_ub);
        if (clubfound == 1) {
            int max = pl.get(0).getAge();
            for (int j = 1; j < pl.size(); j++)
                if (max < pl.get(j).getAge())
                    max = pl.get(j).getAge();
            for (int k = 0; k < pl.size(); k++) {
                if (pl.get(k).getAge() == max)

           play.add(pl.get(k));

            }
        }
        else
            System.out.println("No such club with this name");
        System.out.println();

        return play;
    }


    public List<Player> search_max_height(String cl_ub) {
        List<Player> play=new ArrayList<>();
       List<Player> pl= search(cl_ub);
        if (clubfound == 1) {
            Double max = pl.get(0).getHeight();
            for (int j = 1; j < pl.size(); j++)
                if (max < pl.get(j).getHeight())
                    max = pl.get(j).getHeight();
            for (int k = 0; k < pl.size(); k++) {
                if (pl.get(k).getHeight() == max)

           play.add(pl.get(k));

            }
        }
        else
            System.out.println("No such club with this name");
        System.out.println();

        return play;
    }

    public double search_total_ys(String cl_ub) {

        List<Player> pl=search(cl_ub);
        Double total = 0.0;
        if (clubfound == 1) {
            Double weeek_total = 0.0;

            for (int j = 0; j < pl.size(); j++)
                weeek_total += pl.get(j).getWeekly_salary();

            total = weeek_total * 52;

            System.out.println();
        }
        else
            System.out.println("No such club with this name");
        System.out.println();
        return total;

    }

}

