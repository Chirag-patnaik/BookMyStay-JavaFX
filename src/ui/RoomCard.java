package ui;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Room;
import service.RoomInventory;

public class RoomCard extends VBox {

    public RoomCard(Room room, RoomInventory inventory) {

        setSpacing(10);
        setPadding(new Insets(20));

        setStyle("""
                -fx-background-color:white;
                -fx-background-radius:15;
                -fx-border-radius:15;
                -fx-border-color:#dddddd;
                """);

        Label type = new Label(room.getRoomType());
        type.setStyle("-fx-font-size:20px; -fx-font-weight:bold;");

        Label beds = new Label("Beds : " + room.getBeds());

        Label price = new Label("Price : ₹" + room.getPrice());

        Label available = new Label(
                "Available : " + inventory.getAvailability(room.getRoomType()));

        Button button = new Button("Book Now");
        button.getStyleClass().add("primary-button");

        button.setOnAction(e -> {

            Stage stage = (Stage) button.getScene().getWindow();

            BookingScreen bookingScreen = new BookingScreen();
            bookingScreen.show(stage, room.getRoomType());

        });

        getChildren().addAll(
                type,
                beds,
                price,
                available,
                button
        );
    }
}