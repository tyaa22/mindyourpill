package com.project.mindyourpillnew.ui.home;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.project.mindyourpillnew.R;
import com.project.mindyourpillnew.database.LocalDatabase;
import com.project.mindyourpillnew.databinding.FragmentHomeBinding;
import com.project.mindyourpillnew.entity.Reminder;
import com.project.mindyourpillnew.utility.ReminderAdapter;
import com.project.mindyourpillnew.utility.SwipeToDismiss;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    ReminderAdapter adapter;
    RecyclerView recyclerView;
    HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        binding = FragmentHomeBinding.inflate(inflater, container, false);
        recyclerView = binding.reminderCardList;
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.addButtonImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMedicineDialog();
            }
        });

        Bundle bundle = getArguments();
        if(bundle != null) {
            String username = bundle.getString("username");
            TextView loggedInUser = binding.loggedInUserName;
            loggedInUser.setText(username);
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        adapter = new ReminderAdapter(requireContext());
        adapter.setReminders(homeViewModel.getReminders());
        recyclerView.setAdapter(adapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new SwipeToDismiss(adapter));
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    private void showAddMedicineDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
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
                homeViewModel.addReminder(medicineName.getText().toString(), medicineStartTime.getText().toString(), Integer.parseInt(medicineAmount.getText().toString()), Integer.parseInt(medicineInterval.getText().toString()), button[0].getText().toString());
                adapter.setReminders(homeViewModel.getReminders());
            }
        });

        builder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setView(dialogView);
        builder.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}