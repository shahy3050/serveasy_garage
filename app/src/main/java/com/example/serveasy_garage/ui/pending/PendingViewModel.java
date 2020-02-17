package com.example.serveasy_garage.ui.pending;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PendingViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PendingViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Pending fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}