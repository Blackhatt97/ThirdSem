package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import sample.DBWrapper.MovieWrapper;
import sample.DBWrapper.ScheduleWrapper;
import sample.Model.Movie;
import sample.Model.MovieDay;
import sample.Model.MovieTableObject;
import sample.Model.Schedule;

import java.sql.Time;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;

import static jdk.nashorn.internal.objects.NativeMath.round;

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


    public void createButton(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void initialize() {

        MovieWrapper mw = new MovieWrapper();

        datePicker.setValue(LocalDate.now());
        ScheduleWrapper scheduleWrapper = new ScheduleWrapper();
        scheduleRoom1 = scheduleWrapper.getSchedule(1);
        scheduleRoom2 = scheduleWrapper.getSchedule(2);
        LocalDate selectedDate = datePicker.getValue();
        displayMovieSchedule(selectedDate);

        choiceBox.setItems(mw.getAllMovies());

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

        Movie movie = (Movie) choiceBox.getSelectionModel().getSelectedItem();
        System.out.println(movie);  //testing


        //ROOM 1 PART
        try
        {
            MovieTableObject movieTableObject = (MovieTableObject) room1Table.getSelectionModel().getSelectedItem();
            if (movieTableObject.getMovieTitle().equals("")){

                Date start = movieTableObject.getMovieBeginTimeUtil();
                int duration = movie.getDuration();
                if (duration%60 < 31){
                    int hours = duration/60 + 1;
                    if (verifySpace(start, hours))
                    {

                        ScheduleWrapper sw = new ScheduleWrapper();
                        sw.saveMovieToSchedule(movie.getId(), start, 1);

                    }
                    initialize();


                }
                else{

                    int hours = duration/60 + 2;
                    if (verifySpace(start, hours))
                    {

                        ScheduleWrapper sw = new ScheduleWrapper();
                        sw.saveMovieToSchedule(movie.getId(), start, 1);

                    }
                    initialize();

                }







            } else {
                System.out.println("No space here in room A");
            }



        } catch (Exception e){
            e.printStackTrace();
        }


        //ROOM 2 PART
        try
        {
            MovieTableObject movieTableObject = (MovieTableObject) room2Table.getSelectionModel().getSelectedItem();
            if (movieTableObject.getMovieTitle().equals("")){

                Date start = movieTableObject.getMovieBeginTimeUtil();
                int duration = movie.getDuration();
                if (duration%60 < 31){
                    int hours = duration/60 + 1;
                    if (verifySpace(start, hours))
                    {

                        ScheduleWrapper sw = new ScheduleWrapper();
                        sw.saveMovieToSchedule(movie.getId(), start, 2);

                    }
                    initialize();


                }
                else{
                    int hours = duration/60 + 2;
                    if (verifySpace(start, hours))
                    {

                        ScheduleWrapper sw = new ScheduleWrapper();
                        sw.saveMovieToSchedule(movie.getId(), start, 2);

                    }
                    initialize();
                }

                //System.out.println("HOURS: " + hours + " MINUTES: " + duration);






            } else {
                System.out.println("No space here in room B");
            }



        } catch (Exception e){
            e.printStackTrace();
        }




    }

    public boolean verifySpace(Date startMovieToAdd, int hours){

        boolean verify = false;
        int index = room1Table.getSelectionModel().getSelectedIndex();
        for (int i = index + 1; i < index + hours ; i++)
        {
            room1Table.getSelectionModel().select(i);
            MovieTableObject mto = (MovieTableObject) room1Table.getSelectionModel().getSelectedItem();
            if (mto.getMovieTitle().equals("")){
                verify = true;
            }

        }


        return verify;
    }

    public void removeAction(ActionEvent actionEvent) {
    }
}
