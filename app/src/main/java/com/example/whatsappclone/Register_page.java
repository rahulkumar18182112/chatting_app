package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsappclone.databinding.ActivityLogInPageBinding;
import com.example.whatsappclone.databinding.ActivityRegisterPageBinding;
import com.example.whatsappclone.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register_page extends AppCompatActivity {
     ActivityRegisterPageBinding binding;

    private FirebaseAuth auth;
    FirebaseDatabase fireDB;
    ProgressBar Pbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityRegisterPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


auth=FirebaseAuth.getInstance();
fireDB=FirebaseDatabase.getInstance();



binding.regisBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        auth.createUserWithEmailAndPassword(binding.email.getText().toString(),binding.password.getText().toString()).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){


                    Users user=new Users(binding.email.getText().toString(),binding.password.getText().toString(),
                            binding.name.getText().toString());
                    String id=task.getResult().getUser().getUid();
                   fireDB.getReference().child("Users").child(id).setValue(user);


                    Toast.makeText(Register_page.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                }


                else
                    Toast.makeText(Register_page.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
});
binding.loginPage.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(getApplicationContext(),LogIn_page.class);
        startActivity(intent);
    }
});
    }
}