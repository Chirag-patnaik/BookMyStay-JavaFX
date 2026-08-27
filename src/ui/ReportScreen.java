package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Reservation;

public class ReportScreen {

    public void show(Stage stage) {

        int totalBookings = Dashboard.getBookingHistory()
                .getBookingHistory().size();

        int confirmed = 0;
        int cancelled = 0;

        int single = 0;
        int doubleroom = 0;
        int suite = 0;

        for (Reservation reservation :
                Dashboard.getBookingHistory().getBookingHistory()) {

            if (reservation.getStatus().equalsIgnoreCase("Confirmed"))
                confirmed++;

            if (reservation.getStatus().equalsIgnoreCase("Cancelled"))
                cancelled++;

            if (reservation.getRoomType().equals("Single Room"))
                single++;

            if (reservation.getRoomType().equals("Double Room"))
                doubleroom++;

            if (reservation.getRoomType().equals("Suite Room"))
                suite++;
        }

        Label title = new Label("HOTEL REPORT");
        title.getStyleClass().add("title");

        Label report = new Label(
                "Total Reservations : " + totalBookings +

                "\n\nConfirmed Bookings : " + confirmed +

                "\nCancelled Bookings : " + cancelled +

                "\n\nSingle Rooms Booked : " + single +

                "\nDouble Rooms Booked : " + doubleroom +

                "\nSuite Rooms Booked : " + suite
        );

        report.setStyle("-fx-font-size:16px;");

        Button back = new Button("Back");
        back.getStyleClass().add("secondary-button");

        back.setOnAction(e -> {

            new Dashboard().show(stage);

        });

        VBox root = new VBox(25);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                title,
                report,
                back
        );

        Scene scene = new Scene(root, 700, 550);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/style.css")
                        .toExternalForm());

        stage.setScene(scene);

        stage.setTitle("Reports");

        stage.show();
    }
}