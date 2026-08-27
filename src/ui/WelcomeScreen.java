package ui;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class WelcomeScreen {

    public void show(Stage stage) {

        Label title = new Label("🏨 BOOK MY STAY");
        title.getStyleClass().add("title");

        Label subtitle = new Label("Hotel Booking Management System");
        subtitle.getStyleClass().add("subtitle");

        Button start = new Button("Start Booking");
        start.setOnAction(e -> {

    Dashboard dashboard = new Dashboard();
    dashboard.show(stage);

});
        start.getStyleClass().add("primary-button");
        start.setPrefWidth(250);

        Button exit = new Button("Exit");
        exit.getStyleClass().add("secondary-button");
        exit.setPrefWidth(250);

        exit.setOnAction(e -> stage.close());

        VBox root = new VBox(25);
        root.setAlignment(Pos.CENTER);

        root.getChildren().addAll(
                title,
                subtitle,
                start,
                exit
        );

        Scene scene = new Scene(root, 1000, 650);

        scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm());

        stage.setTitle("Book My Stay");
        stage.setScene(scene);
        stage.show();
    }
}