package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class SearchRoomScreen {

    public void show(Stage stage) {

        Label title = new Label("SEARCH ROOMS");
        title.getStyleClass().add("title");

        ComboBox<String> roomType = new ComboBox<>();

        roomType.getItems().addAll(
                "Single Room",
                "Double Room",
                "Suite Room"
        );

        roomType.setPromptText("Select Room Type");

        Label result = new Label();

        Button search = new Button("Search");
        search.getStyleClass().add("primary-button");

        Button back = new Button("Back");
        back.getStyleClass().add("secondary-button");

        search.setOnAction(e -> {

            if (roomType.getValue() == null) {

                result.setText("Please select a room.");

                return;
            }

            int available = Dashboard.getInventory()
                    .getAvailability(roomType.getValue());

            result.setText(
                    "Room Type : " + roomType.getValue()
                    + "\nAvailable Rooms : " + available
            );

        });

        back.setOnAction(e -> {

            new Dashboard().show(stage);

        });

        VBox root = new VBox(20);

        root.setAlignment(Pos.CENTER);

        root.setPadding(new Insets(25));

        root.getChildren().addAll(
                title,
                roomType,
                search,
                result,
                back
        );

        Scene scene = new Scene(root, 700, 500);

        scene.getStylesheets().add(
                getClass()
                        .getResource("/styles/style.css")
                        .toExternalForm());

        stage.setScene(scene);

        stage.show();
    }
}