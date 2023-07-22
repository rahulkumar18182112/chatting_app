package com.example.whatsappclone;

import static java.lang.System.load;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.whatsappclone.Adapter.chat_adapter;
import com.example.whatsappclone.Adapter.chat_details_adapter;
import com.example.whatsappclone.databinding.ActivityChatsDetailBinding;
import com.example.whatsappclone.model.message_model;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class Chats_detail_Activity extends AppCompatActivity {

    ActivityChatsDetailBinding binding;
    FirebaseDatabase fireDb;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding =ActivityChatsDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();

        fireDb=FirebaseDatabase.getInstance();
         final String senderId=auth.getUid();
       final String receiverId=getIntent().getStringExtra("userId");
        String Name=getIntent().getStringExtra("Name");
        String profile_pic=getIntent().getStringExtra("profile_pic");

        binding.name.setText(Name);
     //   load(profile_pic).placeholder(R.drawable.ic_launcher_background).into(binding.profilePic);



     //   getSupportActionBar().setDisplayHomeAsUpEnabled(true);
       final ArrayList<message_model> list=new ArrayList<>();

       final  chat_details_adapter adapter=new chat_details_adapter(this,list);
        binding.chatDetailRecycler.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        binding.chatDetailRecycler.setLayoutManager(layoutManager);

         final String senderRoom= senderId+ receiverId;
        final String receiverRoom= receiverId+ senderId;

        fireDb.getReference().child("chats")
                        .child(senderRoom)
                                .addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                                        list.clear();
                                        for(DataSnapshot snapshot1 :snapshot.getChildren()){
                                            message_model model1=snapshot1.getValue(message_model.class);
                                            list.add(model1);
                                        }
                                        adapter.notifyDataSetChanged();
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError error) {

                                    }
                                });






        binding.Sendbtn.setOnClickListener(new View.OnClickListener()  {
            @Override
            public void onClick(View v) {
                String message= binding.sendText.getText().toString();
              final  message_model model=new message_model(senderId,message);
                model.setTime(new Date().getTime());

                binding.sendText.setText("");
            /*    fireDb.getReference().child("chats").child(senderRoom)
                        .push()
                        .setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                fireDb.getReference().child("chats").child(receiverRoom).push()
                                        .setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {

                                            }
                                        });

                            }

                                        });*/

                fireDb.getReference().child("chats").child(senderRoom).push().setValue(model)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            fireDb.getReference().child("chats").child(receiverRoom).push()
                        .setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                fireDb.getReference().child("chats").child(receiverRoom)
                                        .push().setValue(model).addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void unused) {

                                            }
                                        });
                            }
                        });
              /*  fireDb.getReference().child("chats").child(receiverRoom).push()
                        .setValue(model).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        });*/

            }
        });




    }
}