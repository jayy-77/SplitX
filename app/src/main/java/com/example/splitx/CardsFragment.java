package com.example.splitx;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardsFragment extends Fragment {
    private ExtendedFloatingActionButton createRoom;
    private EditText roomNameEt;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference roomRef = null;
    public CardsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_cards, container, false);
         View v2 = LayoutInflater.from(getContext()).inflate(R.layout.room_name_dilaog, null);



        createRoom = v.findViewById(R.id.createRoom);

        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomNameEt = v2.findViewById(R.id.roomNameEt);

            MaterialAlertDialogBuilder obj =  new MaterialAlertDialogBuilder(getContext(),R.style.ThemeOverlay_App_MaterialAlertDialog)
                        .setTitle("New Room")
                        .setView(R.layout.room_name_dilaog)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                String roomId = String.valueOf((int)(Math.random()*(100000-10000+1)+10000));
                                RoomObject obj = new RoomObject(roomId,roomNameEt.getText().toString(),"1","0");
                                db.collection("Rooms").document(roomId).set(obj).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                       List<String> joinedRooms = new ArrayList<>();
                                       joinedRooms.add(roomId);
                                       JoinedRoomObject obj2 = new JoinedRoomObject(joinedRooms);
                                        db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("JoinedRooms").limit(1).get()
                                                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                                    @Override
                                                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                                        if(queryDocumentSnapshots.getDocuments().size()>0){
                                                            db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("JoinedRooms").document("rooms").update("joinedRooms",FieldValue.arrayUnion(roomId));
                                                        }else{
                                                            db.collection("Users").document(FirebaseAuth.getInstance().getCurrentUser().getEmail()).collection("JoinedRooms").document("rooms").set(obj2);
                                                        }
                                                    }
                                                });
                                        Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Room created successfully", Snackbar.LENGTH_LONG);
                                        snackBar.setBackgroundTint(Color.parseColor("#b2fab4"));
                                        snackBar.setTextColor(Color.BLACK);
                                        snackBar.show();
                                    }
                                })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Snackbar snackBar = Snackbar.make(getActivity().findViewById(android.R.id.content), "Failed  to create room", Snackbar.LENGTH_LONG);
                                                snackBar.setBackgroundTint(Color.parseColor("#b2fab4"));
                                                snackBar.setTextColor(Color.BLACK);
                                                snackBar.show();
                                            }
                                        });
                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        obj.show();
            }
        });

        return v;
    }
}