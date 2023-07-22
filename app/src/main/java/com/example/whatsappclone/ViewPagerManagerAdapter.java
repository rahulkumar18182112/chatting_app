package com.example.whatsappclone;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerManagerAdapter extends FragmentStateAdapter {
String[] title={ "chats","status","calls"};

    public ViewPagerManagerAdapter(@NonNull MainActivity fragment) {
        super(fragment);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new chats();

            case 1:
                return new status_fragment();
            case 2:
                return new calls_fragment();
        }
return new chats();
    }

    @Override
    public int getItemCount() {
        return title.length ;
    }
}
