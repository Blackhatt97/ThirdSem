package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class BookingController {


    public AnchorPane staffAnchor;

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

    public void chooseSeats(ActionEvent actionEvent) {
    }
}
