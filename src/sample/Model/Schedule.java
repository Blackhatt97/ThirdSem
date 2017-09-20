package sample.Model;

import java.util.ArrayList;

/**
 * Created by blackhatt on 19/09/2017.
 */
public class Schedule {

    private ArrayList<MovieDay> movieDays;

    public Schedule(ArrayList<MovieDay> movieDays) {
        this.movieDays = movieDays;
    }

    public ArrayList<MovieDay> getMovieDays() {
        return movieDays;
    }

    public void setMovieDays(ArrayList<MovieDay> movieDays) {
        this.movieDays = movieDays;
    }
}
