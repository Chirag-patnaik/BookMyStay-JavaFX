package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Reservation;

public class BookingHistoryScreen {

    public void show(Stage stage) {

        Label title = new Label("BOOKING HISTORY");
        title.getStyleClass().add("title");

        VBox historyBox = new VBox(15);
        historyBox.setPadding(new Insets(20));

        if (Dashboard.getBookingHistory().getBookingHistory().isEmpty()) {

            historyBox.getChildren().add(
                    new Label("No bookings available.")
            );

        } else {

            for (Reservation reservation :
                    Dashboard.getBookingHistory().getBookingHistory()) {

                Label booking = new Label(

                        "Reservation ID : " + reservation.getReservationId()

                        + "\nGuest : " + reservation.getGuestName()

                        + "\nRoom : " + reservation.getRoomType()

                        + "\nRoom ID : " + reservation.getRoomId()

                        + "\nStatus : " + reservation.getStatus()

                        + "\n-------------------------------------------"
                );

                booking.setStyle("-fx-font-size:16px;");

                historyBox.getChildren().add(booking);
            }
        }

        ScrollPane scroll = new ScrollPane(historyBox);
        scroll.setFitToWidth(true);

        Button back = new Button("Back");
        back.getStyleClass().add("secondary-button");

        back.setOnAction(e -> {

            Dashboard dashboard = new Dashboard();
            dashboard.show(stage);

        });

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(20));

        root.getChildren().addAll(
                title,
                scroll,
                back
        );

        Scene scene = new Scene(root, 900, 600);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/style.css")
                        .toExternalForm());

        stage.setScene(scene);

        stage.show();
    }
}