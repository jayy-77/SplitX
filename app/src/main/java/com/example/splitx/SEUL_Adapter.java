package com.example.splitx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SEUL_Adapter extends RecyclerView.Adapter<SEUL_Adapter.ViewHolder>{
    private ArrayList<SEULA_Object> seulaData = new ArrayList<>();
    private ArrayList<SEULA_Object> newSeulaData = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;
    List<Integer> pos = new ArrayList<>();
     int selectedUserCount = 0;
    String amount;
    float splitSum;
    SEULA_Object obj;

    public SEUL_Adapter(ArrayList<SEULA_Object> seulaData, Context context, String amount) {
        this.seulaData = seulaData;
        this.context = context;
        this.amount = amount;
    }
    public SEUL_Adapter(){

    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.split_user_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        splitSum = Float.valueOf(amount);
        holder.userName.setText(seulaData.get(position).getName());
//        if(FirebaseAuth.getInstance().getCurrentUser().getEmail().equals(seulaData.get(position).getEmail())){
//            obj = new SEULA_Object(seulaData.get(position).name,seulaData.get(position).userPhoto,"0",seulaData.get(position).email,true);
//            seulaData.set(position,obj);
//            holder.stlCheck.setChecked(true);
//            holder.amount.setText("₹ "+amount);
//        }
        if(seulaData.get(position).selected){
            holder.stlCheck.setChecked(true);
        }else{
            holder.stlCheck.setChecked(false);
        }

        Picasso.get().load(seulaData.get(position).getUserPhoto()).into(holder.userPhoto);
        if(seulaData.get(position).selected){
            selectedUserCount++;
            splitSum/=selectedUserCount;
            holder.stlCheck.setChecked(true);
            holder.amount.setText("₹ "+splitSum);
        }else{
            holder.amount.setText("₹ 0");
            holder.stlCheck.setChecked(false);
        }
        holder.splitUserItemCons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.stlCheck.isChecked()){
                    splitSum/=selectedUserCount;
                    holder.stlCheck.setChecked(false);
                    obj = new SEULA_Object(seulaData.get(position).name,seulaData.get(position).getUserPhoto(),String.valueOf(splitSum),seulaData.get(position).getEmail(),false);
                    seulaData.remove(obj);
                    newSeulaData.add(obj);

                }else{
                    splitSum/=selectedUserCount;
                    holder.amount.setText("₹ "+String.valueOf(splitSum));
                    holder.stlCheck.setChecked(true);
                    obj = new SEULA_Object(seulaData.get(position).name,seulaData.get(position).getUserPhoto(),"0",seulaData.get(position).getEmail(),true);
                    newSeulaData.add(obj);
                    seulaData.remove(obj);
                }
                newSeulaData.addAll(seulaData);
                update();
            }

        });
    }
    @Override
    public int getItemCount() {
        return seulaData.size();
    }
    public void update(){
        seulaData.clear();
        seulaData.addAll(newSeulaData);
        notifyDataSetChanged();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        CheckBox stlCheck;
        ImageView userPhoto;
        TextView userName, amount;
        ConstraintLayout splitUserItemCons;
        public ViewHolder(View itemView) {
            super(itemView);
            stlCheck = itemView.findViewById(R.id.sltCheck);
            userPhoto = itemView.findViewById(R.id.userPhoto);
            userName = itemView.findViewById(R.id.userNameTV);
            amount = itemView.findViewById(R.id.splitAmount);
            splitUserItemCons = itemView.findViewById(R.id.splitUserItemCons);
        }
    }
}
