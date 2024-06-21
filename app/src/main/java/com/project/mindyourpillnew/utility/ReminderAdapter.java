package com.project.mindyourpillnew.utility;

import android.os.Build;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.project.mindyourpillnew.R;
import com.project.mindyourpillnew.database.LocalDatabase;
import com.project.mindyourpillnew.entity.Reminder;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;

public class ReminderAdapter extends RecyclerView.Adapter<ReminderAdapter.ViewHolder>{

    private ArrayList<Reminder> reminders;
    private Context context;
    private int count = 0;
    Calendar calendar = Calendar.getInstance();
    AlarmManager alarmManager;
//            = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    public ReminderAdapter(Context context) {
        if(context == null){
            throw new IllegalArgumentException("Context cannot be null");
        }
        this.context = context;
        this.alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
    }
    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.reminder_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        holder.medicineName.setText(reminders.get(position).getMedicineName());
        holder.startTime.setText(reminders.get(position).getStartTime());
        holder.directions.setText(reminders.get(position).getDirection());

        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.putExtra("medicineName", reminders.get(position).getMedicineName());
        intent.putExtra("startTime", reminders.get(position).getStartTime());
        intent.putExtra("medicineAmount", reminders.get(position).getMedicineAmount());
        intent.putExtra("interval", reminders.get(position).getInterval());
        intent.putExtra("direction", reminders.get(position).getDirection());

        String time[] = reminders.get(position).getStartTime().split(":");
        calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(time[0]));
        calendar.set(Calendar.MINUTE, Integer.parseInt(time[1]));
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Calendar now = Calendar.getInstance();
        now.set(Calendar.SECOND, 0);
        now.set(Calendar.MILLISECOND, 0);

        if(now.before(calendar.getTime())){
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ++count, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
        else {
            calendar.add(Calendar.DATE, 0);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, ++count, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            } else {
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
            }
        }
        Toast toast = Toast.makeText(context, "Alarm set for "+ reminders.get(position).getMedicineName()+ " on "+ calendar.get(Calendar.DATE) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.YEAR) + " at " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE), Toast.LENGTH_LONG);
        toast.show();
    }

    @Override
    public int getItemCount() {
        return reminders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView medicineName;
        private TextView startTime;
        private TextView directions;

        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            this.medicineName = itemView.findViewById(R.id.medicineName);
            this.startTime = itemView.findViewById(R.id.startTime);
            this.directions = itemView.findViewById(R.id.directions);
        }
    }

    public void setReminders(ArrayList<Reminder> reminders) {
        this.reminders = reminders;
        notifyDataSetChanged();
    }


    public void deleteReminder(int position){
        reminders.remove(position);
        notifyItemRemoved(position);
    }
}
