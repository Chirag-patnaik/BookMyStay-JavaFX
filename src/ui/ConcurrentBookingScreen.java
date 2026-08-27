package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.Reservation;
import service.ConcurrentBookingProcessor;

public class ConcurrentBookingScreen {

    public void show(Stage stage) {

        Label title = new Label("CONCURRENT BOOKING");
        title.getStyleClass().add("title");

        Label subtitle = new Label(
                "Simulate multiple users booking\nrooms at the same time.");
        subtitle.getStyleClass().add("subtitle");

        Button start = new Button("Start Concurrent Booking");
        start.getStyleClass().add("primary-button");
        start.setPrefWidth(260);

        Button back = new Button("Back");
        back.getStyleClass().add("secondary-button");
        back.setPrefWidth(260);

        start.setOnAction(e -> {

            try {

                // Create sample reservations
                Dashboard.getBookingQueue().addBookingRequest(
                        new Reservation("User 1", "Single Room"));

                Dashboard.getBookingQueue().addBookingRequest(
                        new Reservation("User 2", "Double Room"));

                Dashboard.getBookingQueue().addBookingRequest(
                        new Reservation("User 3", "Suite Room"));

                ConcurrentBookingProcessor t1 =
                        new ConcurrentBookingProcessor(
                                Dashboard.getBookingQueue(),
                                Dashboard.getBookingService(),
                                Dashboard.getInventory(),
                                Dashboard.getBookingHistory());

                ConcurrentBookingProcessor t2 =
                        new ConcurrentBookingProcessor(
                                Dashboard.getBookingQueue(),
                                Dashboard.getBookingService(),
                                Dashboard.getInventory(),
                                Dashboard.getBookingHistory());

                ConcurrentBookingProcessor t3 =
                        new ConcurrentBookingProcessor(
                                Dashboard.getBookingQueue(),
                                Dashboard.getBookingService(),
                                Dashboard.getInventory(),
                                Dashboard.getBookingHistory());

                t1.start();
                t2.start();
                t3.start();

                t1.join();
                t2.join();
                t3.join();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Completed");
                alert.setHeaderText("Concurrent Booking Successful");
                alert.setContentText(
                        "3 booking threads executed successfully.");

                alert.showAndWait();

            } catch (Exception ex) {

                ex.printStackTrace();
            }

        });

        back.setOnAction(e -> {

            new Dashboard().show(stage);

        });

        VBox root = new VBox(25);

        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(30));

        root.getChildren().addAll(
                title,
                subtitle,
                start,
                back
        );

        Scene scene = new Scene(root, 700, 500);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/style.css")
                        .toExternalForm());

        stage.setTitle("Concurrent Booking");
        stage.setScene(scene);
        stage.show();
    }
}