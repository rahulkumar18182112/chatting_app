package com.example.whatsappclone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatsappclone.Chats_detail_Activity;
import com.example.whatsappclone.R;
import com.example.whatsappclone.model.Users;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class chat_adapter extends RecyclerView.Adapter<chat_adapter.ViewHolder> {
    public chat_adapter(@NonNull Context context, ArrayList<Users> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    Context context;
    ArrayList<Users> list=new ArrayList<>();

    @Override
    public chat_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chats_design,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull chat_adapter.ViewHolder holder, int position) {

       Users user=list.get(position);
        Picasso.get().load(user.getProfile_pic()).placeholder(R.drawable.ic_launcher_background).into(holder.profile_pic);
        holder.name.setText(user.getUsername());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(context, Chats_detail_Activity.class);
                intent.putExtra("userid",user.getUserid());
                intent.putExtra("profile_pic",user.getProfile_pic());
                intent.putExtra("name",user.getUsername());

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView profile_pic;
        TextView name;
        TextView last_msg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            profile_pic=itemView.findViewById(R.id.profile_pic);
            name=itemView.findViewById(R.id.name);
            last_msg=itemView.findViewById(R.id.last_msg);

        }
    }
}
