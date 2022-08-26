package com.example.splitx;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder>{
    private ArrayList<RoomObject> cardData = new ArrayList<>();
    Context context;

    public CardsAdapter(ArrayList<RoomObject> cardData, Context context) {
     this.cardData = cardData;
     this.context = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.card_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.roomName.setText(cardData.get(position).getRoomName());
        holder.roomid.setText("Id: "+cardData.get(position).getRoomId());
        holder.peoples.setText(cardData.get(position).getNumberOfpeople());
        holder.cards.setText(cardData.get(position).getNumberOfCards());
        holder.numberOfPeopleTV.setText(cardData.get(position).getNumberOfpeople()+""+"+");
    }


    @Override
    public int getItemCount() {
        return cardData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView u1,u2,u3,u4;
        TextView roomid, roomName, numberOfPeopleTV;
        Button cards, peoples;
        public ViewHolder(View itemView) {
            super(itemView);
            u1 = itemView.findViewById(R.id.user1IV);
            u2 = itemView.findViewById(R.id.user2IV);
            u3 = itemView.findViewById(R.id.user3IV);
            u4 = itemView.findViewById(R.id.user4IV);
            roomid = itemView.findViewById(R.id.roomId);
            roomName = itemView.findViewById(R.id.roomName);
            cards = itemView.findViewById(R.id.numberOfCards);
            peoples = itemView.findViewById(R.id.numberOfPeopleBtn);
            numberOfPeopleTV = itemView.findViewById(R.id.user5IV);
        }
    }
}