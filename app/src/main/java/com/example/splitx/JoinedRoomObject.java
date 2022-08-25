package com.example.splitx;

import java.util.ArrayList;
import java.util.List;

public class JoinedRoomObject {
    public ArrayList<String> getJoinedRoomId() {
        return joinedRoomId;
    }

    public void setJoinedRoomId(ArrayList<String> joinedRoomId) {
        this.joinedRoomId = joinedRoomId;
    }

    ArrayList<String> joinedRoomId;
   public  JoinedRoomObject(List<String> joinedRoom){

    }
    public JoinedRoomObject(ArrayList<String> joinedRoomId){
       this.joinedRoomId = joinedRoomId;
    }

}
