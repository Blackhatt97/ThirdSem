package sample.Model;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by blackhatt on 25/09/2017.
 */
public class Booking {

    private String name;
    private ArrayList<Integer> noOfSeats = null;
    private Calendar cal;
    private String movieName;

    public Booking(String name, ArrayList<Integer> noOfSeats, Calendar cal, String movieName) {
        this.name = name;
        this.noOfSeats = noOfSeats;
        this.cal = cal;
        this.movieName = movieName;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Integer> getNoOfSeats() {
        return noOfSeats;
    }

    public void setNoOfSeats(ArrayList<Integer> noOfSeats) {
        this.noOfSeats = noOfSeats;
    }

    public Calendar getCal() {
        return cal;
    }

    public void setCal(Calendar cal) {
        this.cal = cal;
    }

}