package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Reservation;

public class CancelBookingScreen {

    public void show(Stage stage) {

        Label title = new Label("CANCEL BOOKING");
        title.getStyleClass().add("title");

        TextField reservationField = new TextField();
        reservationField.setPromptText("Enter Reservation ID");

        Button cancel = new Button("Cancel Booking");
        cancel.getStyleClass().add("primary-button");

        Button back = new Button("Back");
        back.getStyleClass().add("secondary-button");

        cancel.setOnAction(e -> {

            String reservationId = reservationField.getText().trim();

            Reservation reservation =
                    Dashboard.getBookingHistory()
                            .getReservation(reservationId);

            if (reservation == null) {

                Alert alert = new Alert(Alert.AlertType.ERROR);

                alert.setHeaderText("Reservation Not Found");

                alert.showAndWait();

                return;
            }

            reservation.setStatus("Cancelled");

            Dashboard.getInventory()
                    .increaseAvailability(
                            reservation.getRoomType());

            Alert alert = new Alert(Alert.AlertType.INFORMATION);

            alert.setHeaderText("Booking Cancelled");

            alert.setContentText(
                    "Reservation " +
                            reservationId +
                            " cancelled successfully.");

            alert.showAndWait();

// Save updated booking history
Dashboard.getPersistenceService()
        .saveBookings(Dashboard.getBookingHistory());

new Dashboard().show(stage);

        });

        back.setOnAction(e -> {

            new Dashboard().show(stage);

        });

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                title,
                reservationField,
                cancel,
                back
        );

        Scene scene = new Scene(root,700,500);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/style.css")
                        .toExternalForm());

        stage.setScene(scene);

        stage.show();
    }
}