package com.project.mindyourpillnew.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.project.mindyourpillnew.database.LocalDatabase;
import com.project.mindyourpillnew.entity.Reminder;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {

    private LocalDatabase mDatabase;
    private ArrayList<Reminder> mReminders;

    public HomeViewModel() {
        mDatabase = new LocalDatabase();
        mReminders = new ArrayList<>();
        mReminders = mDatabase.getReminders();
    }

    public ArrayList<Reminder> getReminders() {
        return mReminders;
    }

    public void addReminder(String medName, String startTime, int amount, int interval, String direction) {
        mDatabase.addReminder(medName, startTime, amount, interval, direction);
    }
}