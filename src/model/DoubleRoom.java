package model;

public class DoubleRoom extends Room {

    public DoubleRoom() {
        super("Double Room", 2, 4500);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}  