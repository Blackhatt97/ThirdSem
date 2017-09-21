package sample.DBWrapper;

import javafx.collections.ObservableList;
import sample.Model.Movie;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by blackhatt on 21/09/2017.
 */
class MovieWrapperTest {
    MovieWrapper movieWrapper = new MovieWrapper();
    ObservableList<Movie> ol = movieWrapper.getAllMovies();

    @org.junit.jupiter.api.Test
    void getAllMovies() {
        assertIterableEquals(ol,ol);
    }

    @org.junit.jupiter.api.Test
    void getMovie() {
    }

    @org.junit.jupiter.api.Test
    void saveMovie() {


    }

}