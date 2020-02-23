package com.example.serveasy_garage.ui.bike;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.serveasy_garage.R;

public class BikeFragment extends Fragment {

    private BikeViewModel bikeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        bikeViewModel =
                ViewModelProviders.of(this).get(BikeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_bike, container, false);

        return root;
    }
}