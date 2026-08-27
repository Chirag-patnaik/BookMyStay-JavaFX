package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.AddOnService;
import model.Reservation;

public class BookingScreen {

    public void show(Stage stage, String roomType) {

        Label title = new Label("BOOK A ROOM");
        title.getStyleClass().add("title");

        Label guestLabel = new Label("Guest Name");

        TextField guestField = new TextField();
        guestField.setPromptText("Enter Guest Name");

        Label roomLabel = new Label("Room Type");

        Label roomValue = new Label(roomType);

        Label peopleLabel = new Label("Guests");

        ComboBox<Integer> guests = new ComboBox<>();
        guests.getItems().addAll(1, 2, 3, 4);
        guests.setValue(1);

        // Add-On Services

        Label addOnLabel = new Label("Add-On Services");

        CheckBox breakfast = new CheckBox("Breakfast (₹500)");
        CheckBox spa = new CheckBox("Spa (₹1000)");
        CheckBox pickup = new CheckBox("Airport Pickup (₹700)");

        Button confirm = new Button("Confirm Booking");
        confirm.getStyleClass().add("primary-button");
        confirm.setPrefWidth(220);

        Button cancel = new Button("Cancel");
        cancel.getStyleClass().add("secondary-button");
        cancel.setPrefWidth(220);

                confirm.setOnAction(e -> {

            String guestName = guestField.getText().trim();

            if (guestName.isEmpty()) {

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Invalid Input");
                alert.setHeaderText("Guest Name Required");
                alert.setContentText("Please enter the guest name.");
                alert.showAndWait();
                return;
            }

            try {

                Reservation reservation = new Reservation(guestName, roomType);

                // Add booking request
                Dashboard.getBookingQueue().addBookingRequest(reservation);

                // Process booking
                Dashboard.getBookingService().processBookings(
                        Dashboard.getBookingQueue().getBookingQueue(),
                        Dashboard.getInventory(),
                        Dashboard.getBookingHistory());

                // Add selected add-on services
                if (breakfast.isSelected()) {
                    Dashboard.getAddOnServiceManager().addService(
                            reservation.getReservationId(),
                            new AddOnService("Breakfast", 500));
                }

                if (spa.isSelected()) {
                    Dashboard.getAddOnServiceManager().addService(
                            reservation.getReservationId(),
                            new AddOnService("Spa", 1000));
                }

                if (pickup.isSelected()) {
                    Dashboard.getAddOnServiceManager().addService(
                            reservation.getReservationId(),
                            new AddOnService("Airport Pickup", 700));
                }

                double total = Dashboard.getAddOnServiceManager()
                        .getTotalCost(reservation.getReservationId());

                StringBuilder services = new StringBuilder();

                if (breakfast.isSelected())
                    services.append("✔ Breakfast\n");

                if (spa.isSelected())
                    services.append("✔ Spa\n");

                if (pickup.isSelected())
                    services.append("✔ Airport Pickup\n");

                if (services.length() == 0)
                    services.append("No Add-On Services");

                Alert alert = new Alert(Alert.AlertType.INFORMATION);

                alert.setTitle("Booking Successful");
                alert.setHeaderText("Reservation Confirmed");

                alert.setContentText(
                        "Reservation ID : " + reservation.getReservationId()
                                + "\nRoom ID : " + reservation.getRoomId()
                                + "\nStatus : " + reservation.getStatus()
                                + "\n\nSelected Services\n"
                                + services
                                + "\nTotal Add-On Cost : ₹" + total);

alert.showAndWait();

// Save bookings to file
Dashboard.getPersistenceService()
        .saveBookings(Dashboard.getBookingHistory());

new Dashboard().show(stage);

            } catch (Exception ex) {

                ex.printStackTrace();

                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Booking Failed");
                alert.setHeaderText("Error");
                alert.setContentText(ex.getMessage());
                alert.showAndWait();
            }

        });

        cancel.setOnAction(e -> {

            new Dashboard().show(stage);

        });

        VBox root = new VBox(15);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                title,

                guestLabel,
                guestField,

                roomLabel,
                roomValue,

                peopleLabel,
                guests,

                addOnLabel,
                breakfast,
                spa,
                pickup,

                confirm,
                cancel
        );

        Scene scene = new Scene(root, 700, 650);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/style.css")
                        .toExternalForm());

        stage.setTitle("Book My Stay");
        stage.setScene(scene);
        stage.show();
    }
}