package com.project.mindyourpillnew.utility;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.project.mindyourpillnew.R;
import com.project.mindyourpillnew.databinding.ActivityAlarmBinding;

public class AlarmActivity extends AppCompatActivity {

    private ActivityAlarmBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(binding.getRoot());

        TextView medicineName, medicineStartTime, medicineAmount, medicineInterval,  medicineDirection;
        medicineName = binding.alarmName;
        medicineStartTime = binding.alarmStarttime;
        medicineAmount = binding.alarmAmount;
        medicineInterval = binding.alarmInterval;
        medicineDirection = binding.alarmDirection;

        Button takeMed = binding.takemedBtn;

        Intent intent = getIntent();
        medicineName.setText(intent.getStringExtra("medicineName"));
        medicineStartTime.setText(intent.getStringExtra("medicineStartTime"));
        medicineAmount.setText(intent.getStringExtra("medicineAmount"));
        medicineInterval.setText(intent.getStringExtra("medicineInterval"));
        medicineDirection.setText(intent.getStringExtra("medicineDirection"));

        takeMed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}