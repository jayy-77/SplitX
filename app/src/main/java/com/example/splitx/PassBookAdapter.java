package com.example.splitx;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class PassBookAdapter extends RecyclerView.Adapter<PassBookAdapter.ViewHolder>{
    private ArrayList<PassBookObject> passBookData = new ArrayList<>();
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    Context context;

    public PassBookAdapter(ArrayList<PassBookObject> passBookData, Context context) {
        this.passBookData = passBookData;
        this.context = context;
    }
    public PassBookAdapter(){

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.item_passbook, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.senderUpi.setText(passBookData.get(position).getUpi());

    }


    @Override
    public int getItemCount() {
        return passBookData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView sentDate, sentTime, amount, status, senderName, senderUpi;

        public ViewHolder(View itemView) {
            super(itemView);
            sentDate = itemView.findViewById(R.id.sentDate);
            sentTime = itemView.findViewById(R.id.sentaTime);
            amount = itemView.findViewById(R.id.paidAmount);
            status = itemView.findViewById(R.id.status);
            senderName = itemView.findViewById(R.id.senderName);
            senderUpi = itemView.findViewById(R.id.senderUpi);
        }
    }
}
