package service;

import model.Reservation;

public class BookingValidator {

    public void validateReservation(
            Reservation reservation,
            RoomInventory inventory)
            throws InvalidBookingException {

        if (reservation.getGuestName() == null ||
                reservation.getGuestName().trim().isEmpty()) {

            throw new InvalidBookingException(
                    "Guest name cannot be empty.");
        }

        if (inventory.getAvailability(
                reservation.getRoomType()) <= 0) {

            throw new InvalidBookingException(
                    "No rooms available for "
                            + reservation.getRoomType());
        }
    }
}