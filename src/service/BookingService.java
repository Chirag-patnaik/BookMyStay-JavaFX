package service;

import model.Reservation;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class BookingService {

    private HashMap<String, Set<String>> allocatedRooms;
    private int roomCounter;
    private int reservationCounter;

    public BookingService() {

        allocatedRooms = new HashMap<>();
        roomCounter = 1;
        reservationCounter = 1;
    }

    public synchronized void processBookings(
            Queue<Reservation> bookingQueue,
            RoomInventory inventory,
            BookingHistory history) {

        BookingValidator validator = new BookingValidator();

        while (!bookingQueue.isEmpty()) {

            Reservation reservation = bookingQueue.poll();

            try {

                validator.validateReservation(
                        reservation,
                        inventory);

                String roomType = reservation.getRoomType();

                String roomId =
                        roomType.substring(0, 2).toUpperCase()
                                + roomCounter++;

                String reservationId =
                        "RES" + reservationCounter++;

                allocatedRooms
                        .computeIfAbsent(roomType,
                                k -> new HashSet<>())
                        .add(roomId);

                inventory.decreaseAvailability(roomType);

                reservation.setReservationId(reservationId);
                reservation.setRoomId(roomId);
                reservation.setStatus("Confirmed");

                history.addReservation(reservation);

                System.out.println(
                        "\nThread : "
                                + Thread.currentThread().getName());

                System.out.println(
                        "Booking Confirmed");

                System.out.println(reservation);

            }

            catch (InvalidBookingException e) {

                reservation.setStatus("Failed");

                System.out.println(e.getMessage());
            }
        }
    }
}