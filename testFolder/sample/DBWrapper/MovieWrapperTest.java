package sample.DBWrapper;

import org.junit.jupiter.api.Test;
import sample.Model.Movie;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by blackhatt on 25/09/2017.
 */
class MovieWrapperTest {

    MovieWrapper mr = new MovieWrapper();
    @Test
    void getAllMovies() {
        assertNotEquals(null, mr.getAllMovies() );
    }

    @Test
    void getMovie() {

        assertNotEquals(null, mr.getMovie(1));
        assertEquals("as", mr.getMovie(2));
    }

    @Test
    void updateMovie() {
    }

    @Test
    void deleteMovie() {
    }

    @Test
    void saveMovie() {
    }

}