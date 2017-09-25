package sample.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by blackhatt on 25/09/2017.
 */
public class BookingData {


    private ObservableList<Movie> bookingList = FXCollections.observableArrayList();

    public ObservableList<Movie> getBookingList() {
        return bookingList;
    }
}
