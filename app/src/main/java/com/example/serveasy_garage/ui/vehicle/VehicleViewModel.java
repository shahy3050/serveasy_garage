package com.example.serveasy_garage.ui.vehicle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class VehicleViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public VehicleViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Vehicle fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}