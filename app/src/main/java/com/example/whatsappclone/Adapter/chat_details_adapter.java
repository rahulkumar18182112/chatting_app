package com.example.whatsappclone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.R;
import com.example.whatsappclone.model.message_model;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chat_details_adapter extends RecyclerView.Adapter {
    @NonNull
    Context context;
    ArrayList<message_model> list;
    int SENDER_VIEW_TYPE=1;
    int RECEIVER_VIEW_TYPE=2;

    public chat_details_adapter(@NonNull Context context, ArrayList<message_model> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == SENDER_VIEW_TYPE){
            View view= LayoutInflater.from(context).inflate(R.layout.sample_sender,parent,false);
            return new SenderViewHolder(view);
        }
        else {
            View view= LayoutInflater.from(context).inflate(R.layout.sample_receiver,parent,false);
            return new ReceiverViewHolder(view);

        }




    }
   @Override
    public int getItemViewType(int position){
        if(list.get(position).getuId().equals(FirebaseAuth.getInstance().getCurrentUser().getUid().equals(list))){
            return SENDER_VIEW_TYPE;
        }
        else
            return RECEIVER_VIEW_TYPE;


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        message_model model=list.get(position);
        if(holder.getClass()== SenderViewHolder.class){
            ((SenderViewHolder)holder).Smsg.setText(model.getMsg());
        }
        else{
            ((ReceiverViewHolder)holder).Rmsg.setText(model.getMsg());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView Smsg,Stime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            Smsg=itemView.findViewById(R.id.sender_txt);
            Stime=itemView.findViewById(R.id.sender_time);
        }
    }
    public class ReceiverViewHolder extends RecyclerView.ViewHolder {
        TextView Rmsg,Rtime;
        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            Rmsg=itemView.findViewById(R.id.rcvr_txt);
            Rtime=itemView.findViewById(R.id.rcvr_time);
        }
    }
}
