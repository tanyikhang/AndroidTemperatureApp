package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

public class HomeFragment extends Fragment {

    private SharedViewModel sharedViewModel;
    private EditText apiKeyEditText;
    private Spinner citySpinner;
    private Button submitButton;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_fragment, container, false);
        sharedViewModel = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);

        apiKeyEditText = view.findViewById(R.id.apiKeyEditText);
        citySpinner = view.findViewById(R.id.citySpinner);
        submitButton = view.findViewById(R.id.submitButton);

        //viewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        // Set an OnClickListener for the submit button to trigger API request
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String apiKey = apiKeyEditText.getText().toString();
                String selectedCity = citySpinner.getSelectedItem().toString();
                //viewModel.fetchWeatherData(apiKey, selectedCity);
                sharedViewModel.setApiKey(apiKey);
                sharedViewModel.setSelectedCity(selectedCity);
                // Navigate to the second page (page 2)
                if (getActivity() != null && getActivity() instanceof MainActivity) {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.toTemperature();
                }
            }
        });
        // Initialize UI components and handle logic for the first view
        return view;
    }
}