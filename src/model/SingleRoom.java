package model;

public class SingleRoom extends Room {

    public SingleRoom() {
        super("Single Room", 1, 2500);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}