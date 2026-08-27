package ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import model.DoubleRoom;
import model.Room;
import model.SingleRoom;
import model.SuiteRoom;

import service.AddOnServiceManager;
import service.BookingHistory;
import service.BookingQueue;
import service.BookingService;
import service.PersistenceService;
import service.RoomInventory;

public class Dashboard {

    // ==========================
    // Shared Backend Objects
    // ==========================

    private static RoomInventory inventory = new RoomInventory();
    private static BookingQueue bookingQueue = new BookingQueue();
    private static BookingHistory bookingHistory = new BookingHistory();
    private static BookingService bookingService = new BookingService();
    private static AddOnServiceManager addOnServiceManager = new AddOnServiceManager();
    private static PersistenceService persistenceService = new PersistenceService();

    public void show(Stage stage) {

        Label title = new Label("🏨 BOOK MY STAY");
        title.getStyleClass().add("title");

        Label subtitle = new Label("Available Rooms");
        subtitle.getStyleClass().add("subtitle");

        Room single = new SingleRoom();
        Room doubleroom = new DoubleRoom();
        Room suite = new SuiteRoom();

        FlowPane cards = new FlowPane();
        cards.setHgap(25);
        cards.setVgap(25);
        cards.setAlignment(Pos.CENTER);

        cards.getChildren().addAll(
                new RoomCard(single, inventory),
                new RoomCard(doubleroom, inventory),
                new RoomCard(suite, inventory)
        );

        Button searchButton = new Button("Search Rooms");
        searchButton.getStyleClass().add("secondary-button");
        searchButton.setPrefWidth(220);

        searchButton.setOnAction(e -> {
            new SearchRoomScreen().show(stage);
        });

        Button historyButton = new Button("Booking History");
        historyButton.getStyleClass().add("secondary-button");
        historyButton.setPrefWidth(220);

        historyButton.setOnAction(e -> {
            new BookingHistoryScreen().show(stage);
        });

        Button cancelButton = new Button("Cancel Booking");
        cancelButton.getStyleClass().add("secondary-button");
        cancelButton.setPrefWidth(220);

        cancelButton.setOnAction(e -> {
            new CancelBookingScreen().show(stage);
        });

        Button reportButton = new Button("Reports");
        reportButton.getStyleClass().add("secondary-button");
        reportButton.setPrefWidth(220);

        reportButton.setOnAction(e -> {
            new ReportScreen().show(stage);
        });

        Button concurrentButton = new Button("Concurrent Booking");
concurrentButton.getStyleClass().add("secondary-button");
concurrentButton.setPrefWidth(220);

concurrentButton.setOnAction(e -> {
    new ConcurrentBookingScreen().show(stage);
});

        VBox root = new VBox(30);
        root.setAlignment(Pos.TOP_CENTER);
        root.setPadding(new Insets(30));

      root.getChildren().addAll(
        title,
        subtitle,
        cards,
        searchButton,
        historyButton,
        cancelButton,
        reportButton,
        concurrentButton
);
        Scene scene = new Scene(root, 1200, 700);

        scene.getStylesheets().add(
                getClass().getResource("/styles/style.css").toExternalForm()
        );

        stage.setTitle("Book My Stay");
        stage.setScene(scene);
        stage.show();
    }

    // ==========================
    // Getters
    // ==========================

    public static RoomInventory getInventory() {
        return inventory;
    }

    public static BookingQueue getBookingQueue() {
        return bookingQueue;
    }

    public static BookingHistory getBookingHistory() {
        return bookingHistory;
    }

    public static BookingService getBookingService() {
        return bookingService;
    }

    public static AddOnServiceManager getAddOnServiceManager() {
        return addOnServiceManager;
    }

    public static PersistenceService getPersistenceService() {
        return persistenceService;
    }
}