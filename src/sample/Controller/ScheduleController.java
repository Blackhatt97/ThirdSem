package sample.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;

import java.time.LocalDate;

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

    public void createButton(ActionEvent actionEvent) {
    }

    public void back(ActionEvent actionEvent) {
    }

    public void initialize() {
        datePicker.setValue(LocalDate.now());
    }
}
