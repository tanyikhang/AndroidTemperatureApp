package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

public class MainActivity extends AppCompatActivity{
    private ViewPager2 viewPager;
    private CustomFragmentStateAdapter pagerAdapter;
    private ViewModel viewModel;
    private Button button1;
    private Button button2;
    //private SharedViewModel sharedViewModel;
    // Other UI elements for the bottom navigation bar

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI components and set up the ViewPager with the fragments
        viewPager = findViewById(R.id.viewPager);
        pagerAdapter = new CustomFragmentStateAdapter(getSupportFragmentManager(), getLifecycle());
        viewPager.setAdapter(pagerAdapter);

        //sharedViewModel = new ViewModelProvider(this).get(SharedViewModel.class);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);

        // Set up navigation buttons and handle switching between views
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });

    }

    public void toTemperature() {
        viewPager.setCurrentItem(1);
    }
}