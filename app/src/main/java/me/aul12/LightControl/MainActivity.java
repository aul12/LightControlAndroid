package me.aul12.LightControl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private void sendCommand(int command) {
        int []data = new int[4];
        data[0] = command;
        data[1] = 0;
        data[2] = 0;
        data[3] = 0;

        new CommandSendHandler().execute(new TransportTouple(data, Config.PORT, Config.IP));
    }

    private void sendCommand(int[] colors) {
        int []data = new int[4];
        data[0] = 0;
        data[1] = colors[0];
        data[2] = colors[1];
        data[3] = colors[2];

        new CommandSendHandler().execute(new TransportTouple(data, Config.PORT, Config.IP));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AlarmManager alarmManager = (AlarmManager)getApplicationContext().getSystemService(Context.ALARM_SERVICE);
        AlarmManager.AlarmClockInfo alarmClockInfo = alarmManager.getNextAlarmClock();

        if(alarmClockInfo != null) {
            TextView alarmTimeEdit = findViewById(R.id.alarmTime);
            Date alarmDate = new Date(alarmClockInfo.getTriggerTime());
            alarmTimeEdit.setText(alarmDate.toString());


            Intent sunriseIntent= new Intent(getApplicationContext(), SunriseWakeUpReceiver.class);
            sunriseIntent.setAction(SunriseWakeUpReceiver.ACTION_SUNRISE_ALARM);
            Bundle sunriseBundle = new Bundle();
            sunriseBundle.putShort("PORT", Config.PORT);
            sunriseBundle.putString("IP", Config.IP);
            sunriseIntent.putExtra("BUNDLE", sunriseBundle);

            Calendar sunriseCalendar = Calendar.getInstance();
            sunriseCalendar.setTimeInMillis(alarmClockInfo.getTriggerTime());
            sunriseCalendar.add(Calendar.MINUTE, -10);

            PendingIntent sunriseAlarmIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    0, sunriseIntent, 0);
            alarmManager.setExactAndAllowWhileIdle(
                    AlarmManager.RTC_WAKEUP, sunriseCalendar.getTimeInMillis(), sunriseAlarmIntent);
        }

        final SeekBar seekBarR = findViewById(R.id.seekBarR);
        final SeekBar seekBarG = findViewById(R.id.seekBarG);
        final SeekBar seekBarB = findViewById(R.id.seekBarB);

        seekBarR.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int[] colors = {seekBarR.getProgress(), seekBarG.getProgress(), seekBarB.getProgress()};
                sendCommand(colors);
            }
        });

        seekBarG.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                int[] colors = {seekBarR.getProgress(), seekBarG.getProgress(), seekBarB.getProgress()};
                sendCommand(colors);
            }
        });

        seekBarB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int []colors = {seekBarR.getProgress(), seekBarG.getProgress(), seekBarB.getProgress()};
                sendCommand(colors);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {}

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        findViewById(R.id.buttonWakeUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(1);
            }
        });
        findViewById(R.id.buttonFade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand(2);
            }
        });
        findViewById(R.id.buttonOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int []colors = {0,0,0};
                sendCommand(colors);
                seekBarR.setProgress(0);
                seekBarG.setProgress(0);
                seekBarB.setProgress(0);
            }
        });
    }
}
