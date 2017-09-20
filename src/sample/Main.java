package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.DBWrapper.MovieWrapper;
import sample.Model.Movie;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/sample/Views/first.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root,700,500));
        primaryStage.show();
    }


    public static void main(String[] args) {

        launch(args);
        MovieWrapper mw = new MovieWrapper();
        System.out.println(mw.getAllMovies().toString());

    }

    //testcommentzssss
}
