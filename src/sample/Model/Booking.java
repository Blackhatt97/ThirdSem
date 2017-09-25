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
    private MovieDay movieDay;

    public Booking(String name, ArrayList<Integer> noOfSeats, Calendar cal, MovieDay movieDay){
        this.name = name;
        this.noOfSeats = noOfSeats;
        this.cal = cal;
        this.movieDay = movieDay;

    }

}
