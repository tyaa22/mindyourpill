package com.project.mindyourpillnew.entity;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.TimeZone;

public class Reminder {
    private String medicineName;
    private String startTime;
    private int medicineAmount;
    private int interval;
    private String direction;

    public Reminder() {
    }

    public Reminder(String medicineName, String startTime, int medicineAmount, int interval, String direction) {
        this.medicineName = medicineName;
        this.startTime = startTime;
        this.medicineAmount = medicineAmount;
        this.interval = interval;
        this.direction = direction;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public int getMedicineAmount() {
        return medicineAmount;
    }

    public void setMedicineAmount(int medicineAmount) {
        this.medicineAmount = medicineAmount;
    }

    public Date stringToTime(String time) throws ParseException {
        DateFormat formatter = DateFormat.getTimeInstance(DateFormat.DEFAULT);
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        return formatter.parse(time);
    }
}
