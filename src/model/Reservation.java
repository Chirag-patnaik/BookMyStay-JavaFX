package model;

import java.io.Serializable;

public class Reservation implements Serializable {

    private String reservationId;
    private String guestName;
    private String roomType;
    private String roomId;
    private String status;

    public Reservation(String guestName, String roomType) {

        this.guestName = guestName;
        this.roomType = roomType;

        this.reservationId = "";
        this.roomId = "";
        this.status = "Pending";
    }

    public String getReservationId() {
        return reservationId;
    }

    public void setReservationId(String reservationId) {
        this.reservationId = reservationId;
    }

    public String getGuestName() {
        return guestName;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {

        return "Reservation ID : " + reservationId +
                "\nGuest          : " + guestName +
                "\nRoom Type      : " + roomType +
                "\nRoom ID        : " + roomId +
                "\nStatus         : " + status;
    }
}