package com.example.splitx;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SplitFragment extends Fragment {
    RecyclerView seulaRecyclerView;
    private SplitAmountListener spaListener;
    LinearLayoutManager linearLayout;
    Context context;
    EditText splitAmoundEt;
    SEULA_Object seula_object;
    Fragment me = this;
    String roomId;
    public String amountEt;
    TextView numberOfJoinedPeople;
    private ArrayList<SEULA_Object> seulaData = new ArrayList<>();
    public ArrayList<SEULA_Object_For_Fire> seulaDataForFire = new ArrayList<>();
    ArrayList<SEULA_Object> seula_objectsList = new ArrayList<>();
    SEUL_Adapter SEULAdapter;
    List<String> userEmails = new ArrayList<>();
    MaterialToolbar materialToolbar;
    Button sendRequestButton;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    public SplitFragment() {
    }

    public  SplitFragment(Context context,String roomId){
        this.context = context;
        this.roomId = roomId;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    public interface SplitAmountListener {
        void onAmountChange(String text);
    }

    public void seulData(ArrayList<SEULA_Object> userList, Float splitSum){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_split, container, false);

//            spaListener = (SplitAmountListener) getChildFragmentManager();

        seulaRecyclerView = v.findViewById(R.id.joinedUserRecyler);
        seulaRecyclerView.setHasFixedSize(true);
        linearLayout = new LinearLayoutManager(getContext());
        linearLayout.setOrientation(LinearLayoutManager.VERTICAL);
        seulaRecyclerView.setLayoutManager(linearLayout);
        splitAmoundEt = v.findViewById(R.id.amount);
        materialToolbar = v.findViewById(R.id.topAppBarRoomActivity);
        numberOfJoinedPeople = v.findViewById(R.id.numberOfJoinedPeople);
        sendRequestButton = v.findViewById(R.id.sendRequestButton);
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((RoomActivity)getContext()).changeFragment();
            }
        });
        splitAmoundEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                amountEt = splitAmoundEt.getText().toString();
                if(!amountEt.isEmpty()) {
                    SEULAdapter = new SEUL_Adapter(seula_objectsList, getContext(), amountEt,SplitFragment.this);
                    seulaRecyclerView.setAdapter(SEULAdapter);
                }
            }
        });

        db.collection("Rooms").document(((RoomActivity)getActivity()).roomId).collection("Details").document("UserDetails").get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                       userEmails = (List<String>) documentSnapshot.get("joinedUsers");
                       for(int i=0;i<userEmails.size();i++){
                           db.collection("Users").document(userEmails.get(i)).get()
                                   .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                       @Override
                                       public void onSuccess(DocumentSnapshot documentSnapshot) {
                                            seula_object = new SEULA_Object(documentSnapshot.get("name").toString(),documentSnapshot.get("profileUri").toString(),documentSnapshot.get("email").toString(),false);
                                            seula_objectsList.add(seula_object);
                                           SEULAdapter = new SEUL_Adapter(seula_objectsList,getContext(),"0",SplitFragment.this);
                                           seulaRecyclerView.setAdapter(SEULAdapter);
                                       }
                                   });

                       }

                    }
                });
        sendRequestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String,ArrayList<SEULA_Object_For_Fire>> userData = new HashMap<>();
                userData.put("SplitReq",seulaDataForFire);
                db.collection("Rooms").document(roomId).collection("SplitRequests").document("testing").set(userData);
            }
        });

        return v;
    }
    public void dataBus(ArrayList<SEULA_Object> seulaData,String s){
        numberOfJoinedPeople.setText("Split between "+s+" people");
        this.seulaData.addAll(seulaData);
        for(int i=0;i<seulaData.size();i++){
            if(seulaData.get(i).selected == true){
                SEULA_Object_For_Fire obj = new SEULA_Object_For_Fire(seulaData.get(i).getEmail(),"PAID");
                seulaDataForFire.add(obj);
            }else{
                SEULA_Object_For_Fire obj = new SEULA_Object_For_Fire(seulaData.get(i).getEmail(),"UNPAID");
                seulaDataForFire.add(obj);
            }
        }
    }
}