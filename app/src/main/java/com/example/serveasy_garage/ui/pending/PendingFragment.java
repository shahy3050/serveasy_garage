package com.example.serveasy_garage.ui.pending;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.serveasy_garage.R;

public class PendingFragment extends Fragment {

    private PendingViewModel pendingViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        pendingViewModel =
                ViewModelProviders.of(this).get(PendingViewModel.class);
        View root = inflater.inflate(R.layout.fragment_pending, container, false);
        final TextView textView = root.findViewById(R.id.text_pending);
        pendingViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}