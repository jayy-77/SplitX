package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


import com.google.android.material.appbar.MaterialToolbar;

public class RoomActivity extends AppCompatActivity {
    public String roomName, roomId;
    Button splitExepenseBtn;
    MaterialToolbar topBar;
    private Fragment SplitFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        roomName = getIntent().getStringExtra("roomName");
        roomId = getIntent().getStringExtra("roomId");

        splitExepenseBtn = findViewById(R.id.splitExpenseBtn);
        topBar = findViewById(R.id.topAppBarRoomActivity);
        topBar.setTitle(roomName);
        topBar.setSubtitle(roomId);
        topBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        topBar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.more:
                        return true;
                }
                return false;
            }
        });
        splitExepenseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SplitFrag = new SplitFragment(getApplicationContext());
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragContainer,SplitFrag)
                        .commit();
            }
        });

    }
}