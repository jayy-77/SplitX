package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toolbar;

public class RoomActivity extends AppCompatActivity {
    String roomName, roomId;
    Toolbar topBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        roomName = getIntent().getStringExtra("roomName");
        roomId = getIntent().getStringExtra("roomId");

        topBar = findViewById(R.id.topAppBarRoomActivity);
        topBar.setTitle(roomName);
        topBar.setSubtitle(roomId);
    }
}