package com.example.myapplication;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.myapplication.HomeFragment;
import com.example.myapplication.TemperatureFragment;

public class CustomFragmentStateAdapter extends FragmentStateAdapter {
    public CustomFragmentStateAdapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public Fragment createFragment(int position) {
        if (position == 0) {
            return HomeFragment.newInstance();
        } else if (position == 1) {
            return TemperatureFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        // Return the total number of fragments
        return 2;
    }

}