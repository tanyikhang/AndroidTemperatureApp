package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class TemperatureFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private WeatherViewModel viewModel;
    private TextView nameTextView;
    private TextView lastUpdatedTextView;
    private TextView celsiusTextView;
    private TextView fahrenheitTextView;

    private Handler handler = new Handler();
    private final int delayMillis = 1000; // 1 second

    private Runnable periodicTask = new Runnable() {
        @Override
        public void run() {
            // Fetch data here
            // Call the method to fetch data from the repository or API
            String apiKey = sharedViewModel.getApiKey().getValue();
            String selectedCity = sharedViewModel.getSelectedCity().getValue();
            viewModel.fetchWeatherData(apiKey, selectedCity);

            handler.postDelayed(this, delayMillis);
        }
    };

    public static TemperatureFragment newInstance() {
        return new TemperatureFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.temperature_fragment, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Observe LiveData for apiKey and selectedCity and update UI
        sharedViewModel.getSelectedCity().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String selectedCity) {
                String updatedSelectedCity = selectedCity;

                String apiKey = sharedViewModel.getApiKey().getValue();

                // Update UI with updatedApiKey and selectedCity
                //call viewModel.fetchWeatherData();
                viewModel.fetchWeatherData(apiKey, updatedSelectedCity);

            }
        });

        nameTextView = view.findViewById(R.id.nameTextView);
        lastUpdatedTextView = view.findViewById(R.id.lastUpdatedTextView);
        celsiusTextView = view.findViewById(R.id.celsiusTextView);
        fahrenheitTextView = view.findViewById(R.id.fahrenheitTextView);

        // Start the periodic task when the fragment is created
        handler.postDelayed(periodicTask, delayMillis);

        // Observe LiveData and update UI when it changes
        viewModel.getNameLiveData().observe(getViewLifecycleOwner(), nameData -> nameTextView.setText(nameData));

        viewModel.getLastUpdatedLiveData().observe(getViewLifecycleOwner(), lastUpdatedData -> lastUpdatedTextView.setText(lastUpdatedData));

        viewModel.getCelsiusLiveData().observe(getViewLifecycleOwner(), celsiusData -> celsiusTextView.setText(celsiusData));

        viewModel.getFahrenheitLiveData().observe(getViewLifecycleOwner(), fahrenheitData -> fahrenheitTextView.setText(fahrenheitData));

        // Initialize UI components and handle logic for the first view
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Stop the periodic task when the fragment is destroyed
        handler.removeCallbacks(periodicTask);
    }
}