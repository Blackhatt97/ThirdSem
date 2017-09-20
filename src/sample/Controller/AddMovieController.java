package sample.Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
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
    //endregion

    MovieData movieData;
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    public void onBackBtnPressed(ActionEvent actionEvent) {
        updateWorkScreen("/sample/Views/main.fxml");
    }

    public void onLoadMoviesBtnPressed(ActionEvent actionEvent) {

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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //movieList = movieData.getMovieList();
    }

}
