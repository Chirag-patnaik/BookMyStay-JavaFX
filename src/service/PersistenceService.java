package service;


import service.PersistenceService;
import model.Reservation;
import java.io.*;
import java.util.List;

public class PersistenceService {

    private static PersistenceService persistenceService = new PersistenceService();
    private static final String FILE_NAME = "booking_history.dat";

    public void saveBookings(BookingHistory history) {

        try (ObjectOutputStream out =
                     new ObjectOutputStream(
                             new FileOutputStream(FILE_NAME))) {

            out.writeObject(history.getBookingHistory());

            System.out.println("\nBookings saved successfully.");

        } catch (IOException e) {

            System.out.println("Error saving bookings.");
        }
    }

    @SuppressWarnings("unchecked")
    public void loadBookings() {

        try (ObjectInputStream in =
                     new ObjectInputStream(
                             new FileInputStream(FILE_NAME))) {

            List<Reservation> reservations =
                    (List<Reservation>) in.readObject();

            System.out.println("\n========== RECOVERED BOOKINGS ==========\n");

            for (Reservation reservation : reservations) {

                System.out.println(reservation);
                System.out.println("--------------------------------------");
            }

        } catch (Exception e) {

            System.out.println("No previous booking data found.");
        }
    }
}