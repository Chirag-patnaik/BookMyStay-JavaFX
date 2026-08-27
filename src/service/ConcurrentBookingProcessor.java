package service;

public class ConcurrentBookingProcessor extends Thread {

    private BookingQueue bookingQueue;
    private BookingService bookingService;
    private RoomInventory inventory;
    private BookingHistory bookingHistory;

    public ConcurrentBookingProcessor(
            BookingQueue bookingQueue,
            BookingService bookingService,
            RoomInventory inventory,
            BookingHistory bookingHistory) {

        this.bookingQueue = bookingQueue;
        this.bookingService = bookingService;
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }

    @Override
    public void run() {

        synchronized (bookingService) {

            bookingService.processBookings(
                    bookingQueue.getBookingQueue(),
                    inventory,
                    bookingHistory);
        }
    }
}