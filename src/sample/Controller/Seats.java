package sample.Controller;

import javafx.application.Application;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Pagination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Jakub on 25.09.2017.
 */
public class Seats extends Application
{
    String theater1 = "20x__16x\n.20x__16x\n__18x__14x\n.20x__16x\n20x__16x\n.20x__16x\n20x__16x\n";
    String theater2 = "11x_10x_11x\n11x_10x_11x\n11x_10x_11x\n\n11x_10x_11x\n11x_10x_11x\n11x_10x_11x\n";

    public ArrayList<Integer> takenSeatsRoomA = new ArrayList();
    public ArrayList<Integer> takenSeatsRoomB = new ArrayList();


    static class Seat extends Group
    {
        Color freeColor = Color.rgb(30, 200, 40);
        Color reservedColor = Color.rgb(170, 40,  40);

        BooleanProperty iamReserved = new SimpleBooleanProperty(false);
        int myNo;


        /**
         * @param no <<< Number of the seat
         * @param reserved   <<< means whether seat is already reserved
         * @param roomname  <<< Room in which the seat is (can be only "theater1" or "theater2"
         */

        public Seat(int no, boolean reserved, String roomname) {

            if (reserved == true)  {

                Circle pillow = new Circle(22);  //was 12
                pillow.setFill(reservedColor);
                pillow.setStrokeWidth(1);
                pillow.setStroke(Color.rgb(30, 40, 40));
                getChildren().add(pillow);

                Text label = new Text("TAKEN");
                label.setFont(label.getFont().font(12));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setTextOrigin(VPos.CENTER);
                label.setLayoutX(-label.getLayoutBounds().getWidth()/2);
                getChildren().add(label);

            }

            else
            {

                myNo = no;
                Circle pillow = new Circle(22);  //was 12
                pillow.setFill(freeColor);
                pillow.setStrokeWidth(1);
                pillow.setStroke(Color.rgb(0, 0, 0)); // was 30 40 40
                getChildren().add(pillow);

                Text label = new Text("" + no);
                label.setFont(label.getFont().font(12));
                label.setTextAlignment(TextAlignment.CENTER);
                label.setTextOrigin(VPos.CENTER);
                label.setLayoutX(-label.getLayoutBounds().getWidth() / 2);
                getChildren().add(label);

                iamReserved.addListener((e, o, n) -> {
                    pillow.setFill(n ? reservedColor : freeColor);
                });
                setOnMouseClicked(m -> {
                    iamReserved.set(!iamReserved.get());
                    System.out.println("Seat state changed!!!");
                    System.out.println("saving seat into room " + roomname);
                    if (roomname.equals("theater1")){
                        int room = 1;
                    } else {
                        int room = 2;
                    }
                    //save or delete number from ??ArrayList??
                });

            }

        }

        static double width() { return 45; }  //was 26
        static double height() { return 60; }  //was 36
    }
    Pane theater(Pane pane, String theater, ArrayList<Integer> reservations, String roomname) {

        double x = 20;
        double y = 40;
        int no = 1;
        boolean reserved = false;

        //testing arrayList
        takenSeatsRoomA.add(1);
        takenSeatsRoomA.add(2);
        takenSeatsRoomA.add(5);

        takenSeatsRoomB.add(3);
        takenSeatsRoomB.add(5);
        takenSeatsRoomB.add(9);


        //inject numbers from DB, if number is there, seat will be red and unclickable (USE VARIABLE "no")

        for (String row : theater.split("\n")) {
            int count = 0;
            for (int c : row.toCharArray()) {

                switch (c) {
                    case 'x':
                        while (count-- > 0) {

                            if (reservations.contains(no)){
                                reserved = true;
                            }

                            Seat seat = new Seat(no++, reserved, roomname);
                            seat.setLayoutX(x);
                            x+= Seat.width();
                            seat.setLayoutY(y);
                            pane.getChildren().add(seat);
                            reserved = false;
                        }
                        count = 0;
                        break;
                    case '0': case '1': case '2': case '3': case '4': case '5': case'6': case '7': case '8': case '9':
                        count = 10*count + (c-'0');
                        break;
                    case '_':
                        x+= Seat.width();
                        break;
                    case '.':
                        x+= Seat.width()/2;
                        break;
                    default: System.out.println("Unknown char: '"+(char)c+"'");
                }
            }
            y += Seat.height();
            count = 0;
            x = 20;

            Button buttonBack = new Button("Back");
            buttonBack.setLayoutX(10);
            buttonBack.setLayoutY(455);
            pane.getChildren().add(buttonBack);
            buttonBack.setOnAction(new EventHandler<javafx.event.ActionEvent>()
            {
                @Override
                public void handle(javafx.event.ActionEvent event)
                {
                    System.out.println("pressed");
                    //add sheiiiit--
                }
            });





            Button buttonSave = new Button("Save");
            buttonSave.setLayoutX(1740);
            buttonSave.setLayoutY(455);
            pane.getChildren().add(buttonSave);



        }
        return pane;
    }

    LinkedList<Node> myPages = new LinkedList<Node>();
    void addTab(String label, Region node) {
        myPages.add(node);
        node.setBackground(new Background(new BackgroundFill(Color.rgb(80, 80, 80), new CornerRadii(0), new Insets(0))));
    }


    @Override
    public void start(Stage primaryStage) throws Exception
    {




        primaryStage.setTitle("Background of Panes");

        BorderPane border = new BorderPane();
        Pagination pages = new Pagination();
        Scene scene = new Scene(border, 1800, 550, Color.WHITE);
        primaryStage.setScene(scene);

        addTab("1", theater(new Pane(), theater1, takenSeatsRoomA, "theater1"));
        addTab("2", theater(new Pane(), theater2, takenSeatsRoomB, "theater2"));

        pages.setPageCount(myPages.size());
        pages.setPageFactory(no -> myPages.get(no));

        border.setCenter(pages);

        primaryStage.show();



    }

    public static void main(String[] args) { launch(args); }
}
