package sample.DBWrapper;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Model.Booking;
import sample.Model.Movie;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by blackhatt on 25/09/2017.
 */
public class BookingWrapper {

    private static final String TABLE = "bookings";
    Connection conn = null;

    public BookingWrapper(){

    }

    public ObservableList<Booking> getBookingsForSchedule(int scheduleId){
        ObservableList<Booking> scheduleBookings = FXCollections.observableArrayList();
        String sql = "SELECT * FROM bookings WHERE id = " + scheduleId;
        try{
            DBConn dbConn = new DBConn();
            conn = dbConn.getConn();
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                ArrayList<Integer> seatsTakenForBooking = new ArrayList();
                String seatsBooked[] = rs.getString(2).split(",");
                for (int i = 0; i < seatsBooked.length ; i++) {
                    seatsTakenForBooking.add(Integer.parseInt(seatsBooked[i]));
                }

//                Booking booking = new Booking(rs.getString(1),
//                        seatsTakenForBooking,
//
//
//                        )
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return null;
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
                ArrayList<Integer> seats = new ArrayList<>();
                Calendar cal = null;
                cal.setTime(rs.getDate(3));
                String[] str = rs.getString(2).split(",");
                for (int i = 0; i < str.length; i++) {
                    seats.add(Integer.parseInt(str[i]));
                }
                //Fix later
                //Booking book = new Booking(rs.getString(1), seats, cal, rs.getString(4));

                //bookingOL.add(book);

            }

            conn.close();
            return bookingOL;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean deleteBooking(int id){
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
            return true;
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return false;
        }

    }
    public Booking saveBooking(String name, ArrayList<Integer> seatNumbers, int movieID, int scheduleId){

        String seats = "";
        for (int i = 0; i < seatNumbers.size(); i++) {
            seats += seatNumbers.get(i) + ",";
        }

        Date date = scheduleWrapper.getScheduleTime(scheduleId);
        java.sql.Timestamp time = new java.sql.Timestamp(date.getTime());

        DBConn dbConn = new DBConn();
        conn = dbConn.getConn();

        String sqlTxt = "INSERT INTO " + TABLE + " ( `name`, `seats`, `movie_id`, `schedule_id`) VALUES (?,?,?,?)";
        try
        {

            PreparedStatement ps = conn.prepareStatement(sqlTxt);
            ps.setString(1, name);
            ps.setString(2, seats);
            ps.setInt(3, movieID );
            ps.setInt(4, scheduleId);
            ps.executeUpdate();
            ps.close();
            conn.close();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        return null;
    }


}
