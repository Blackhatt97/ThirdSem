package sample.DBWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Booking;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by blackhatt on 25/09/2017.
 */
public class BookingWrapper {

    private static final String TABLE = "bookings";
    Connection conn = null;

    public BookingWrapper(){

    }

    public ObservableList<Booking> getAllBookings() {

        ObservableList<Booking> bookingOL = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bookings";


        try{
            DBConn dbConn = new DBConn();
            conn = dbConn.getConn();



            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){

                int id = rs.getInt(4);
                MovieWrapper mw = new MovieWrapper();

                ArrayList<Integer> seats = new ArrayList<>();
                java.sql.Date date = (rs.getDate(3));
                java.sql.Time timee = rs.getTime(3);
                String time = String.valueOf(timee);
                String[] str = rs.getString(2).split(", ");
                for (int i = 0; i < str.length; i++) {
                    seats.add(Integer.parseInt(str[i]));
                }
                //Fix later
                Booking book = new Booking(rs.getString(1), seats, date, time, mw.getMovie(id).getTitle());
                System.out.println(book);

                bookingOL.add(book);

            }

            conn.close();
            return bookingOL;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void deleteBooking(int id){
        DBConn dbConn = new DBConn();
        conn = dbConn.getConn();

        String sqlTxt = "DELETE FROM " + TABLE +
                " WHERE `id` = '" + id + "';";

        try
        {
            PreparedStatement prepStmt = conn.prepareStatement(sqlTxt);
            prepStmt.execute();
            prepStmt.close();
            conn.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

    }
    /*public Booking saveBooking(String name , Calendar calendar, ArrayList<Integer> seatNumbers){

        String seats = "";
        for (int i = 0; i < seatNumbers.size(); i++) {
            seats += seatNumbers.get(i) + ",";

        }
        DBConn dbConn = new DBConn();
        conn = dbConn.getConn();

        String sqlTxt = "INSERT INTO " + TABLE + " ( `name`, `seat_numbers`, `date`, `movie`) VALUES (?,?,?,?)";
        try
        {
            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setString(1, title);
            ps.setString(2, description);
            ps.setInt(3, ageRequirement);
            ps.setString(4, actors);
            ps.setInt(5, duration);
            ps.executeUpdate();
            ps.close();
            conn.close();
            return new Movie(title,description, ageRequirement,actors, duration);
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }*/


}
