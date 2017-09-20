package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.DBWrapper.MovieWrapper;
import sample.Model.Movie;
import sample.Model.MovieData;

import javafx.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by blackhatt on 19/09/2017.
 */
public class AddMovieController implements Initializable {
    //region FXML
    @FXML
    AnchorPane adminPane;
    @FXML
    TextField movieName;
    @FXML
    ChoiceBox<Integer> ageChoice;
    @FXML
    TextArea movieDescription;
    @FXML
    TextArea movieActors;
    @FXML
    TableView movieTable;
    @FXML
    TableColumn<Movie, String> nameCol;
    @FXML
    TableColumn<Movie, Integer> ageCol;
<<<<<<< HEAD
    //endregion
=======

>>>>>>> 9f3a5b784a385890ab769ebfbb2089206bbfc7ff

    MovieWrapper movieWrapper;
    ObservableList<Movie> movieList;
    Integer[] ageNumbers;

    public void onBackBtnPressed(ActionEvent actionEvent) {
        updateWorkScreen("/sample/Views/main.fxml");
    }

    public void onLoadMoviesBtnPressed(ActionEvent actionEvent) {
        loadMoviesFromDB();
    }

    public void onUpdateBtnPressed(ActionEvent actionEvent) {

    }

    private void updateWorkScreen(String path) {
        AnchorPane wpAnchor;
        try {
            wpAnchor = FXMLLoader.load(getClass().getResource(path));
            adminPane.getChildren().setAll(wpAnchor);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    void loadMoviesFromDB() {
        movieList = movieWrapper.getAllMovies();
    }

    void setupTableColumns() {

        nameCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        ageCol.setCellValueFactory(new PropertyValueFactory<>("ageRestriction"));
<<<<<<< HEAD
=======

>>>>>>> 9f3a5b784a385890ab769ebfbb2089206bbfc7ff
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        movieWrapper = new MovieWrapper();
        ageNumbers = new Integer[]{1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18};
        ageChoice.getItems().setAll(ageNumbers);
        loadMoviesFromDB();
        setupTableColumns();
        movieTable.getItems().setAll(movieList);
    }

}
