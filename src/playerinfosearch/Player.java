package playerinfosearch;
import java.io.Serializable;
public class Player implements Serializable {

     String name;
     String country;
     int age;
     double height;
     String clubname;
     String position;
     int number;
     double weekly_salary;
     double price;


    public Player() {
    }

     Player(String name, String country, int age, double height, String clubname, String position, int number, double weekly_salary) {
        this.name = name;
        this.country = country;
        this.age = age;
        this.height = height;
        this.clubname = clubname;
        this.position = position;
        this.number = number;
        this.weekly_salary = weekly_salary;
this.price=0;

    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) { this.position = position; }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public double getWeekly_salary() {
        return weekly_salary;
    }

    public void setWeekly_salary(double weekly_salary) {
        this.weekly_salary = weekly_salary;
    }

    public void  setPrice(double price ){this.price=price;}
    public double getPrice(){return price;}

}
