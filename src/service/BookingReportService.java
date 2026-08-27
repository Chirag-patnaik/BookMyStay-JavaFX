package service;

import model.Reservation;
import java.util.List;

public class BookingReportService {

    public void displayReport(BookingHistory history) {

        List<Reservation> reservations = history.getBookingHistory();

        System.out.println("\n========== BOOKING HISTORY ==========\n");

        if (reservations.isEmpty()) {

            System.out.println("No bookings available.");
            return;
        }

        for (Reservation reservation : reservations) {

            System.out.println(reservation);
            System.out.println("--------------------------------------");
        }

        System.out.println("Total Bookings : " + reservations.size());
    }
}   