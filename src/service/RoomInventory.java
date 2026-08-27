package service;

import java.util.HashMap;

public class RoomInventory {

    private HashMap<String, Integer> inventory;

    public RoomInventory() {

        inventory = new HashMap<>();

        inventory.put("Single Room", 5);
        inventory.put("Double Room", 3);
        inventory.put("Suite Room", 2);
    }

    public int getAvailability(String roomType) {
        return inventory.get(roomType);
    }

    public void updateAvailability(String roomType, int count) {
        inventory.put(roomType, count);
    }

    public void decreaseAvailability(String roomType) {

        int available = inventory.get(roomType);

        if (available > 0) {
            inventory.put(roomType, available - 1);
        }
    }

    public void increaseAvailability(String roomType) {

        inventory.put(
                roomType,
                inventory.get(roomType) + 1);
    }

    public void displayInventory() {

        System.out.println("Current Room Inventory");
        System.out.println("----------------------");

        for (String room : inventory.keySet()) {
            System.out.println(room + " : " + inventory.get(room));
        }
    }
}