package com.example.splitx;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

public class CardsFragment extends Fragment {
    private ExtendedFloatingActionButton createRoom;
    private EditText roomNameEt;
    public CardsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_cards, container, false);
        createRoom = v.findViewById(R.id.createRoom);
        createRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                roomNameEt = new EditText(getContext());
                roomNameEt.setHint("Room Name");
                roomNameEt.setWidth(1);
                new MaterialAlertDialogBuilder(getContext(),R.style.ThemeOverlay_App_MaterialAlertDialog)
                        .setTitle("New Room")
                        .setView(R.layout.room_name_dilaog)
                        .setPositiveButton("Create", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .show();
            }
        });

        return v;
    }
}