package sample.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
<<<<<<< HEAD
import sample.DBWrapper.BookingWrapper;
<<<<<<< HEAD
=======
import sample.DBWrapper.MovieWrapper;
>>>>>>> 76d01de1c73415d9f6f54cef96f10c2b1046ccb4
=======
>>>>>>> parent of b278a12... no message
import sample.DBWrapper.ScheduleWrapper;
import sample.Model.*;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

public class BookingController implements Initializable {

    //region FXML
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<String> availableMovies;
    @FXML
    private Label currentSeatsValue;

    @FXML
    private TableView<Booking> bookingTable;
    @FXML
    private TableColumn<Booking, String> nameCol;
    @FXML
    private TableColumn<Booking, String> dateCol;
    @FXML
    private TableColumn<Booking, String> timeCol;
    @FXML
    private TableColumn<Booking, String> movieCol;
    //endregion

    private ObservableList<Movie> allMovies;

    public AnchorPane staffAnchor;

    private String tempTime;
    private String tempMovieTitle;

    private Schedule scheduleRoom1;
    private Schedule scheduleRoom2;

<<<<<<< HEAD
<<<<<<< HEAD
    private BookingWrapper bookingWrapper = new BookingWrapper();
=======
    private ArrayList<Integer> currentSeatsSelected;
>>>>>>> 76d01de1c73415d9f6f54cef96f10c2b1046ccb4

=======
>>>>>>> parent of b278a12... no message
    private void updateWorkScreen(String path) {

        AnchorPane wpAnchor;
        try {
            wpAnchor = FXMLLoader.load(getClass().getResource(path));
            staffAnchor.getChildren().setAll(wpAnchor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void addBookingBtn(ActionEvent actionEvent) {
        if (!name.getText().equals("")) {
            if (date.getValue() != null) {
                if (!availableMovies.getSelectionModel().getSelectedItem().equals("")) {
<<<<<<< HEAD
                    // setting seat number array to null for now, change this later pls
<<<<<<< HEAD
                    //java.sql.Date date =
                    String movie = availableMovies.getSelectionModel().getSelectedItem();
                    String[] split = movie.split("/");
                    String time = split[1];
                    java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
                    Booking newBooking = new Booking(name.getText(), null, date, time, tempMovieTitle);
=======
                    Booking newBooking = new Booking(name.getText(), currentSeatsSelected, date.getValue(), tempTime, tempMovieTitle);
>>>>>>> 76d01de1c73415d9f6f54cef96f10c2b1046ccb4
=======
                    Booking newBooking = new Booking(name.getText(), null, date.getValue(), tempTime, tempMovieTitle);
>>>>>>> parent of b278a12... no message
                    BookingData.bookingList.add(newBooking);
                    BookingWrapper bookingWrapper = new BookingWrapper();
                    //dirty cheat
                    int currMovieId = 0;
                    String[] scheduleStringId = availableMovies.getSelectionModel().getSelectedItem().split("-");
                    System.out.println(scheduleStringId[1]);
                    int scheduleID = Integer.parseInt(scheduleStringId[1]);
                    for (int i = 0; i < allMovies.size() ; i++) {
                        if (allMovies.get(i).getTitle().equals(tempMovieTitle)){
                            currMovieId = allMovies.get(i).getId();
                        }
                    }
                    bookingWrapper.saveBooking(newBooking.getName(),
                            newBooking.getSeatNumbers(),
                            currMovieId,
                            scheduleID
                            );

                    bookingTable.getItems().setAll(BookingData.bookingList);
                    clearFields();
                }
                else {
                    System.out.println("select a movie pls");
                }
            }
            else {
                System.out.println("put a date pls");
            }
        }
        else {
            System.out.println("put a name pls");
        }
    }

    public void onDateSelect(ActionEvent actionEvent) {
        if (date.getValue() == null)
            return;

        ObservableList<String> movieList = FXCollections.observableArrayList();

        for (int i = 0; i < scheduleRoom1.getMovieDays().size() ; i++) {
            Date currD = scheduleRoom1.getMovieDays().get(i).getCal().getTime();
            LocalDate currentDate = currD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.getValue().isEqual(currentDate)){
                for (MovieTableObject mto : scheduleRoom1.getMovieDays().get(i).getMovieTableObjects()) {
                    String title = mto.getMovieTitle();
                    if (title.equals("--") || title.equals(""))
                        continue;
                    String time = mto.getMovieBeginTime();
<<<<<<< HEAD
<<<<<<< HEAD
                    String formattedString = title + " / " + time;
=======
                    String formattedString = title + " ( " + time + ") | Schedule ID-" + mto.getId();
>>>>>>> 76d01de1c73415d9f6f54cef96f10c2b1046ccb4
=======
                    String formattedString = title + " ( " + time + ")";
>>>>>>> parent of b278a12... no message
                    movieList.add(formattedString);
                }
            }
        }
        for (int i = 0; i < scheduleRoom2.getMovieDays().size() ; i++) {
            Date currD = scheduleRoom2.getMovieDays().get(i).getCal().getTime();
            LocalDate currentDate = currD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.getValue().isEqual(currentDate)){
                for (MovieTableObject mto : scheduleRoom2.getMovieDays().get(i).getMovieTableObjects()) {
                    String title = mto.getMovieTitle();
                    if (title.equals("--") || title.equals(""))
                        continue;
                    String time = mto.getMovieBeginTime();
<<<<<<< HEAD
<<<<<<< HEAD
                    String formattedString = title + " / " + time ;
=======
                    String formattedString = title + " ( " + time + ") | Schedule ID-" + mto.getId();
>>>>>>> 76d01de1c73415d9f6f54cef96f10c2b1046ccb4
=======
                    String formattedString = title + " ( " + time + ")";
>>>>>>> parent of b278a12... no message
                    movieList.add(formattedString);
                }
            }
        }

        availableMovies.getItems().setAll(movieList);
    }

    public void chooseSeatsBtn(ActionEvent actionEvent) {
        Seats seats = new Seats();
        ArrayList<Integer> test = seats.getSeatsSelected();
        for (int i = 0; i < test.size() ; i++) {
            System.out.println("Seat taken: " + test.get(i));
        }
        currentSeatsSelected = test;
        //        try
//        {
//            seats.start((Stage)staffAnchor.getScene().getWindow());
//        } catch (Exception e)
//        {
//            e.printStackTrace();
//        }
    }

    public void goBackBtn(ActionEvent actionEvent) {

        updateWorkScreen("/sample/Views/first.fxml");

    }

    public void updateBookingBtn(ActionEvent actionEvent) {
        if (bookingTable.getSelectionModel().isEmpty())
            return;

        Booking booking = bookingTable.getSelectionModel().getSelectedItem();
        booking.setName(name.getText());
        booking.setDate(date.getValue());
        booking.setTime(tempTime);
        booking.setMovieTitle(tempMovieTitle);

        bookingTable.getItems().setAll(BookingData.bookingList);
        clearFields();
    }

    public void cancelBookingBtn(ActionEvent actionEvent) {
        if (bookingTable.getSelectionModel().isEmpty())
            return;

        Booking booking = bookingTable.getSelectionModel().getSelectedItem();
        BookingData.bookingList.remove(booking);
        bookingTable.getItems().setAll(BookingData.bookingList);
        clearFields();

        //Remove from DB

    }

    public void onTableClick(MouseEvent mouseEvent) {
        if (bookingTable.getSelectionModel().isEmpty())
            return;

        Booking booking = bookingTable.getSelectionModel().getSelectedItem();
        name.setText(booking.getName());
        date.setValue(booking.getDate());
        //currentSeatsValue.setText("Current seats: " + booking.getSeatNumbers().size());
    }

    void clearFields() {
        name.setText("");
        date.setValue(null);
        availableMovies.getItems().clear();
    }

    void setupTableColumns() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        dateCol.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeCol.setCellValueFactory(new PropertyValueFactory<>("time"));
        movieCol.setCellValueFactory(new PropertyValueFactory<>("movieTitle"));
    }

    void addChangeListener()
    {
        availableMovies.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>()
        {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2)
            {
                if (availableMovies.getItems().size() < 1)
                    return;
                String[] strings = availableMovies.getItems().get((Integer) number2).split(" \\( | \\)");
                tempMovieTitle = strings[0];
                tempTime = strings[1];
            }
        });
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addChangeListener();
        setupTableColumns();

<<<<<<< HEAD
<<<<<<< HEAD

        BookingData.bookingList = bookingWrapper.getAllBookings();
        bookingTable.getItems().addAll(BookingData.bookingList);


=======
>>>>>>> parent of b278a12... no message
        ScheduleWrapper scheduleWrapper = new ScheduleWrapper();
        scheduleRoom1 = scheduleWrapper.getSchedule(1);
        scheduleRoom2 = scheduleWrapper.getSchedule(2);
=======
        scheduleRoom1 = Schedule.scheduleRoomA;
        scheduleRoom2 = Schedule.scheduleRoomB;

        MovieWrapper movieWrapper = new MovieWrapper();
        allMovies = movieWrapper.getAllMovies();
        movieWrapper = null;
>>>>>>> 76d01de1c73415d9f6f54cef96f10c2b1046ccb4

    }
}
