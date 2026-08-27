package service;

import java.util.ArrayList;
import java.util.List;

import model.Reservation;

public class BookingHistory {

    private List<Reservation> bookingHistory;

    public BookingHistory() {

        bookingHistory = new ArrayList<>();
    }

    public void addReservation(Reservation reservation) {

        bookingHistory.add(reservation);
    }

    public List<Reservation> getBookingHistory() {

        return bookingHistory;
    }

    public Reservation getReservation(String reservationId) {

        for (Reservation reservation : bookingHistory) {

            if (reservation.getReservationId().equals(reservationId)) {

                return reservation;
            }
        }

        return null;
    }
}