package com.example.splitx;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;


import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class RoomActivity extends AppCompatActivity {
    public String roomName, roomId;
    Button splitExepenseBtn;
    MaterialToolbar topBar;
    private Fragment SplitFrag;
    FrameLayout frameLayout;
    RecyclerView recyclerView;
    SplitRequestAdapter splitRequestAdapter;
    LinearLayoutManager linearLayout;
    SEULA_Object_For_Fire seula_object_for_fire;
    private ArrayList<SEULA_Object_For_Fire> requestData = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);

        roomName = getIntent().getStringExtra("roomName");
        roomId = getIntent().getStringExtra("roomId");

        frameLayout  =findViewById(R.id.fragContainer);
        splitExepenseBtn = findViewById(R.id.splitExpenseBtn);
        topBar = findViewById(R.id.topAppBarRoomActivity);
        topBar.setTitle(roomName);
        topBar.setSubtitle(roomId);

        recyclerView = (RecyclerView) findViewById(R.id.expenseCardRecyler);
        recyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(getApplicationContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayout);

        db.collection("Rooms").document(roomId).collection("SplitRequests").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                             seula_object_for_fire = documentSnapshot.toObject(SEULA_Object_For_Fire.class);
                             requestData.add(seula_object_for_fire);
                        }
                        splitRequestAdapter = new SplitRequestAdapter(requestData,getApplicationContext());
                        recyclerView.setAdapter(splitRequestAdapter);
                    }
                });

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
                SplitFrag = new SplitFragment(getApplicationContext(),roomId);
                frameLayout.setVisibility(View.VISIBLE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragContainer,SplitFrag).commit();
            }
        });
    }
    public void changeFragment(){
        frameLayout.setVisibility(View.GONE);
    }

}