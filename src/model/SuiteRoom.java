package model;

public class SuiteRoom extends Room {

    public SuiteRoom() {
        super("Suite Room", 3, 8000);
    }

    @Override
    public void displayRoomDetails() {
        System.out.println("Room Type : " + getRoomType());
        System.out.println("Beds      : " + getBeds());
        System.out.println("Price     : ₹" + getPrice());
    }
}