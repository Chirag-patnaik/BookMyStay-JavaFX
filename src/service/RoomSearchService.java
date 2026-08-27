package service;
import model.Room;
import model.Reservation;

public class RoomSearchService {

    public void searchAvailableRooms(Room[] rooms, RoomInventory inventory) {

        System.out.println("\n========== AVAILABLE ROOMS ==========\n");

        for (Room room : rooms) {

            int available = inventory.getAvailability(room.getRoomType());

            if (available > 0) {

                room.displayRoomDetails();
                System.out.println("Available Rooms : " + available);
                System.out.println("------------------------------------");
            }
        }
    }
}