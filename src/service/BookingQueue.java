package service;

import java.util.LinkedList;
import java.util.Queue;

import model.Reservation;

public class BookingQueue {

    private Queue<Reservation> bookingQueue;

    public BookingQueue() {
        bookingQueue = new LinkedList<>();
    }

    public void addBookingRequest(Reservation reservation) {

        bookingQueue.offer(reservation);

        System.out.println(
                reservation.getGuestName()
                        + "'s booking request added successfully.");
    }

    public void displayBookingRequests() {

        System.out.println("\n========== BOOKING REQUEST QUEUE ==========\n");

        for (Reservation reservation : bookingQueue) {
            System.out.println(reservation);
        }
    }

    public Queue<Reservation> getBookingQueue() {
        return bookingQueue;
    }
}