package com.example.splitx;

public class RoomObject {
    String roomName, roomId, numberOfpeople, numberOfCards;

    public RoomObject(String roomId, String roomName, String numberOfpeople, String numberOfCards){
        this.roomId = roomId;
        this.roomName = roomName;
        this.numberOfpeople = numberOfpeople;
        this.numberOfCards = numberOfCards;
    }
    public RoomObject(){

    }
    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getNumberOfpeople() {
        return numberOfpeople;
    }

    public void setNumberOfpeople(String numberOfpeople) {
        this.numberOfpeople = numberOfpeople;
    }

    public String getNumberOfCards() {
        return numberOfCards;
    }

    public void setNumberOfCards(String numberOfCards) {
        this.numberOfCards = numberOfCards;
    }



}
