package com.project.mindyourpillnew.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.project.mindyourpillnew.R;
import com.project.mindyourpillnew.database.LocalDatabase;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AddMedicineDialog extends DialogFragment {

    LocalDatabase db = new LocalDatabase();

    @NonNull
    @NotNull
    @Override
    public AlertDialog onCreateDialog(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.fragment_add_medicine_dialog, null);

        EditText medicineName = dialogView.findViewById(R.id.txt_medicineName);
        EditText medicineStartTime = dialogView.findViewById(R.id.txt_startTime);
        EditText medicineAmount = dialogView.findViewById(R.id.txt_medicineAmount);
        EditText medicineInterval = dialogView.findViewById(R.id.txt_interval);
        RadioGroup directions = dialogView.findViewById(R.id.radio_directions);
        final RadioButton[] button = new RadioButton[1];
        directions.setOnCheckedChangeListener((radioGroup, i) -> {
            button[0] = radioGroup.findViewById(i);
        });

        Button finishBtn = dialogView.findViewById(R.id.finish_btn);

        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addReminder(medicineName.getText().toString(), medicineStartTime.getText().toString(), Integer.parseInt(medicineAmount.getText().toString()), Integer.parseInt(medicineInterval.getText().toString()), button[0].getText().toString());
            }
        });
        builder.setView(dialogView);
        return builder.create();
    }
}