package service;

import model.Reservation;

import java.util.Stack;

public class CancellationService {

    private Stack<String> releasedRooms;

    public CancellationService() {

        releasedRooms = new Stack<>();
    }

    public void cancelReservation(
            Reservation reservation,
            RoomInventory inventory) {

        if (!reservation.getStatus().equals("Confirmed")) {

            System.out.println("Cancellation Failed.");
            return;
        }

        releasedRooms.push(reservation.getRoomId());

        inventory.increaseAvailability(
                reservation.getRoomType());

        reservation.setStatus("Cancelled");

        System.out.println("\n========== RESERVATION CANCELLED ==========");
        System.out.println(reservation);
    }

    public void displayReleasedRooms() {

        System.out.println("\nReleased Room IDs");

        while (!releasedRooms.isEmpty()) {

            System.out.println(releasedRooms.pop());
        }
    }
}