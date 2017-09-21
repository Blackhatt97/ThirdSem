package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import sample.DBWrapper.MovieWrapper;
import sample.DBWrapper.ScheduleWrapper;
import sample.Model.Movie;
import sample.Model.MovieDay;
import sample.Model.MovieTableObject;
import sample.Model.Schedule;

import java.io.IOException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by blackhatt on 19/09/2017.
 */
public class ScheduleController {

    @FXML
    DatePicker datePicker;

    @FXML
    ChoiceBox choiceBox;

    @FXML
    TableView room1Table;

    @FXML
    TableView room2Table;

    private Schedule scheduleRoom1;
    private Schedule scheduleRoom2;
    public AnchorPane scheduleAnchor;

    public void createButton(ActionEvent actionEvent) {
    }

    private void updateWorkScreen(String path) {

        AnchorPane wpAnchor;
        try {
            wpAnchor = FXMLLoader.load(getClass().getResource(path));
            scheduleAnchor.getChildren().setAll(wpAnchor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void back(ActionEvent actionEvent) {


        updateWorkScreen("/sample/Views/first.fxml");

    }

    public void initialize() {

        datePicker.setValue(LocalDate.now());
        ScheduleWrapper scheduleWrapper = new ScheduleWrapper();
        scheduleRoom1 = scheduleWrapper.getSchedule(1);
        scheduleRoom2 = scheduleWrapper.getSchedule(2);
        LocalDate selectedDate = datePicker.getValue();
        displayMovieSchedule(selectedDate);

        datePicker.setOnAction((event) -> {
            if (datePicker.getValue() != null) {
                displayMovieSchedule(datePicker.getValue());
            }
        });


    }
    private void resetTables(LocalDate currD){
        Date date = Date.from(currD.atStartOfDay(ZoneId.systemDefault()).toInstant());
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        room1Table.setItems(new MovieDay(cal).getMovieTableObjects());
        room2Table.setItems(new MovieDay(cal).getMovieTableObjects());

    }

    private void displayMovieSchedule(LocalDate date){
        resetTables(date);
        for (int i = 0; i < scheduleRoom1.getMovieDays().size() ; i++) {
            Date currD = scheduleRoom1.getMovieDays().get(i).getCal().getTime();
            LocalDate currentDate = currD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.isEqual(currentDate)){
                room1Table.setItems(scheduleRoom1.getMovieDays().get(i).getMovieTableObjects());
                room1Table.refresh();
            }
        }
        for (int i = 0; i < scheduleRoom2.getMovieDays().size() ; i++) {
            Date currD = scheduleRoom2.getMovieDays().get(i).getCal().getTime();
            LocalDate currentDate = currD.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            if (date.isEqual(currentDate)){
                room2Table.setItems(scheduleRoom2.getMovieDays().get(i).getMovieTableObjects());
                room2Table.refresh();
            }
        }
    }

    public void addMovieToSchedule () {

        java.sql.Date date = java.sql.Date.valueOf(datePicker.getValue());
        //needs conversion !!!!!!!!!!!!!!!!
        MovieTableObject movieTableObject = (MovieTableObject) room1Table.getSelectionModel().getSelectedItem();

        //testing
        if (!movieTableObject.getMovieTitle().equals("")){
            System.out.println(movieTableObject);

        } else {
            System.out.println("No movie");
        }
        //testing end
    }
}
