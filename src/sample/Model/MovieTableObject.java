package sample.Model;

import java.sql.Date;

/**
 * Created by CIA on 20/09/2017.
 */
public class MovieTableObject {

    java.util.Date movieBeginTimeUtil;
    String movieBeginTime;
    String movieTitle;

    public MovieTableObject(java.util.Date movieBeginTime, String movieTitle){

        this.movieBeginTimeUtil = movieBeginTime;
        this.movieTitle = movieTitle;
        this.movieBeginTime = movieBeginTimeUtil.toString().substring(11, 20);

    }

    public java.util.Date getMovieBeginTimeUtil() {
        return movieBeginTimeUtil;
    }

    public void setMovieBeginTimeUtil(Date movieBeginTimeUtil) {
        this.movieBeginTimeUtil = movieBeginTimeUtil;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setMovieBeginTimeUtil(java.util.Date movieBeginTimeUtil) {
        this.movieBeginTimeUtil = movieBeginTimeUtil;
    }

    public String getMovieBeginTime() {
        return movieBeginTime;
    }

    public void setMovieBeginTime(String movieBeginTime) {
        this.movieBeginTime = movieBeginTime;
    }
}
