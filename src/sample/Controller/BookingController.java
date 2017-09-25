package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.DBWrapper.ScheduleWrapper;
import sample.Model.Booking;
import sample.Model.Movie;
import sample.Model.MovieTableObject;
import sample.Model.Schedule;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

public class BookingController {

    //region FXML
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private ChoiceBox<String> availableMovies;

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

    public AnchorPane staffAnchor;

    private Schedule scheduleRoom1;
    private Schedule scheduleRoom2;

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
    }

    public void onDateSelect(ActionEvent actionEvent) {
        if (date.getValue() == null)
            return;

        ScheduleWrapper scheduleWrapper = new ScheduleWrapper();
        scheduleRoom1 = scheduleWrapper.getSchedule(1);
        scheduleRoom2 = scheduleWrapper.getSchedule(2);

        ObservableList<String> movieList = FXCollections.observableArrayList();

        for (int i = 0; i < scheduleRoom1.getMovieDays().size() ; i++) {
            Date currD = scheduleRoom1.getMovieDays().get(i).getCal().getTime();
            LocalDate currentDate = currD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.getValue().isEqual(currentDate)){
                ArrayList<String> movies = new ArrayList<>();
                for (MovieTableObject mto : scheduleRoom1.getMovieDays().get(i).getMovieTableObjects()) {
                    String title = mto.getMovieTitle();
                    if (title.equals("--") || title.equals(""))
                        continue;
                    String time = mto.getMovieBeginTime();
                    String formattedString = title + " ( " + time + ")";
                    movieList.add(formattedString);
                }
            }
        }
        for (int i = 0; i < scheduleRoom2.getMovieDays().size() ; i++) {
            Date currD = scheduleRoom2.getMovieDays().get(i).getCal().getTime();
            LocalDate currentDate = currD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.getValue().isEqual(currentDate)){
                ArrayList<String> movies = new ArrayList<>();
                for (MovieTableObject mto : scheduleRoom2.getMovieDays().get(i).getMovieTableObjects()) {
                    String title = mto.getMovieTitle();
                    if (title.equals("--") || title.equals(""))
                        continue;
                    String time = mto.getMovieBeginTime();
                    String formattedString = title + " ( " + time + ")";
                    movieList.add(formattedString);
                }
            }
        }

        availableMovies.getItems().setAll(movieList);
    }

    public void chooseSeatsBtn(ActionEvent actionEvent) {
        Seats seats = new Seats();
        try
        {
            seats.start((Stage)staffAnchor.getScene().getWindow());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void goBackBtn(ActionEvent actionEvent) {

        updateWorkScreen("/sample/Views/first.fxml");

    }

    public void updateBookingBtn(ActionEvent actionEvent) {
    }

    public void cancelBookingBtn(ActionEvent actionEvent) {
    }

}
