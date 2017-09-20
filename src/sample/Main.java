package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DBWrapper.ScheduleWrapper;
import sample.Model.MovieDay;
import sample.Model.Schedule;

import java.util.ArrayList;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Views/first.fxml"));
        primaryStage.setTitle("Hello Bogdan");
        primaryStage.setScene(new Scene(root,700,500));
        primaryStage.show();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(Date.valueOf(LocalDate.now()));
//
//        MovieDay movieDay = new MovieDay(cal);
//        for (int i = 0; i < movieDay.getMovieTableObjects().size() ; i++) {
//            System.out.println("TIME:" + movieDay.getMovieTableObjects().get(i).getMovieBeginTimeUtil().toString()
//                    + " TITLE: " + movieDay.getMovieTableObjects().get(i).getMovieTitle());
//        }
        ScheduleWrapper scheduleWrapper = new ScheduleWrapper();
        Schedule schedule = scheduleWrapper.getSchedule(2);
        ArrayList<MovieDay> movieDays = schedule.getMovieDays();
        System.out.println("SIZE: " + movieDays.size());
        for (int i = 0; i < movieDays.size() ; i++) {
            System.out.println("SCREENINGS FOR: " + movieDays.get(i).getCal().getTime().toString());
            for (int j = 0; j < movieDays.get(i).getMovieTableObjects().size() ; j++) {
                System.out.println("TIME: " + movieDays.get(i).getMovieTableObjects().get(j).getMovieBeginTimeUtil().toString() +
                        " | TITLE: " + movieDays.get(i).getMovieTableObjects().get(j).getMovieTitle());
            }
        }
    }


    public static void main(String[] args) {

        launch(args);
//        MovieWrapper mw = new MovieWrapper();
//        System.out.println(mw.getAllMovies().toString());


    }


}
