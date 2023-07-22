package com.example.whatsappclone;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsappclone.Adapter.chat_adapter;
import com.example.whatsappclone.databinding.FragmentChatsBinding;
import com.example.whatsappclone.model.Users;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class chats extends Fragment {



    public chats() {
        // Required empty public constructor
    }


    FragmentChatsBinding binding;
    ArrayList<Users> list=new ArrayList<>();
    FirebaseDatabase fireDB ;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        fireDB= FirebaseDatabase.getInstance();
        // Inflate the layout for this fragment
        binding= FragmentChatsBinding.inflate(inflater, container, false);
        chat_adapter adapter=new chat_adapter(getContext(),list);

        binding.recyclerChats.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext());
        binding.recyclerChats.setLayoutManager(layoutManager);
        fireDB.getReference().child("Users").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    Users user=dataSnapshot.getValue(Users.class);
                    user.setUserid(dataSnapshot.getKey());
                    list.add(user);


                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



        return binding.getRoot();




    }

}