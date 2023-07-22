package com.example.whatsappclone;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.whatsappclone.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    FirebaseAuth auth;

    String[] titles={"chats","status","calls"};
ViewPagerManagerAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth=FirebaseAuth.getInstance();


        if( getIntent().getBooleanExtra("Exit", false)){
            finish();
        }

        setSupportActionBar(binding.toolbar);

if(getSupportActionBar() !=null){
    getSupportActionBar().setDisplayHomeAsUpEnabled(true);

}
binding.toolbar.setTitle("WhatsApp");
binding.toolbar.setSubtitle("clone");



        adapter=new ViewPagerManagerAdapter(this);
        binding.pager.setAdapter(adapter);

        new TabLayoutMediator(binding.tab,binding.pager,(((tab1, position) -> tab1.setText(titles[position])))).attach();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        new MenuInflater(this).inflate(R.menu.toolbar_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int itemId=item.getItemId();
       if(itemId==R.id.camera){
            Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.search){
            Toast.makeText(this, "Opening toast", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.setting){
            Toast.makeText(this, "opening setting", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.profile){
            Toast.makeText(this, "Opening profile", Toast.LENGTH_SHORT).show();
        }else if(itemId==R.id.logout){

           auth.signOut();
            Intent intent=new Intent(getApplicationContext(),LogIn_page.class);
            startActivity(intent);

        }

     /*   switch (item.getItemId()){
            case R.id.camera:
                Toast.makeText(this, "Opening Camera", Toast.LENGTH_SHORT).show();
                break;
            case R.id.search:
                Toast.makeText(this, "Opening toast", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "opening setting", Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile:
                Toast.makeText(this, "Opening profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.logout:
                Intent intent=new Intent(getApplicationContext(),LogIn_page.class);
                startActivity(intent);
                break;





        }
        */


        return super.onOptionsItemSelected(item);
    }

}