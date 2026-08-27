import javafx.application.Application;
import javafx.stage.Stage;

import ui.WelcomeScreen;
import service.PersistenceService;

public class Main extends Application {

    @Override
    public void start(Stage stage) {

        // Load previous bookings
        PersistenceService persistence = new PersistenceService();
        persistence.loadBookings();

        // Open Welcome Screen
        WelcomeScreen welcome = new WelcomeScreen();
        welcome.show(stage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}       