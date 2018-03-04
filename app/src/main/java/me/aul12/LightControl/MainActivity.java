package me.aul12.LightControl;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Calendar;
import java.util.Date;


public class MainActivity extends AppCompatActivity {

    private void sendCommand(byte command) {
        new CommandSendHandler().execute(new TransportTouple(command, Config.PORT, Config.IP));
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

            Intent lightifyIntent = new Intent(getApplicationContext(), LightifyWakeUpReceiver.class);
            lightifyIntent.setAction(LightifyWakeUpReceiver.ACTION_LIGHTIFY_ALARM);

            Bundle lightifyBundle = new Bundle();
            lightifyBundle.putString("USER", Config.LIGHTIFY_USERNAME);
            lightifyBundle.putString("PASSWORD", Config.LIGHITFY_PASSWORD);
            lightifyBundle.putString("SERIAL_NO", Config.LIGHTIFY_SERIAL_NO);
            lightifyIntent.putExtra("BUNDLE", lightifyBundle);

            Calendar lightifyCalendar = Calendar.getInstance();
            lightifyCalendar.setTimeInMillis(alarmClockInfo.getTriggerTime());
            lightifyCalendar.add(Calendar.MINUTE, -5);

            PendingIntent lightifyAlarmIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    0, lightifyIntent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, lightifyCalendar.getTimeInMillis(), lightifyAlarmIntent);


            Intent sunriseIntent= new Intent(getApplicationContext(), SunriseWakeUpReceiver.class);
            lightifyIntent.setAction(SunriseWakeUpReceiver.ACTION_SUNRISE_ALARM);
            Bundle sunriseBundle = new Bundle();
            sunriseBundle.putShort("PORT", Config.PORT);
            sunriseBundle.putString("IP", Config.IP);
            sunriseIntent.putExtra("BUNDLE", sunriseBundle);

            Calendar sunriseCalendar = Calendar.getInstance();
            sunriseCalendar.setTimeInMillis(alarmClockInfo.getTriggerTime());
            sunriseCalendar.add(Calendar.MINUTE, -10);

            PendingIntent sunriseAlarmIntent = PendingIntent.getBroadcast(getApplicationContext(),
                    0, sunriseIntent, 0);
            alarmManager.set(AlarmManager.RTC_WAKEUP, sunriseCalendar.getTimeInMillis(), sunriseAlarmIntent);
        }


        findViewById(R.id.buttonUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)0);
            }
        });
        findViewById(R.id.buttonDown).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)1);
            }
        });
        findViewById(R.id.buttonOff).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)2);
            }
        });
        findViewById(R.id.buttonOn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)3);
            }
        });
        findViewById(R.id.buttonR).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)4);
            }
        });
        findViewById(R.id.buttonR1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)8);
            }
        });
        findViewById(R.id.buttonR2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)12);
            }
        });
        findViewById(R.id.buttonR3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)16);
            }
        });
        findViewById(R.id.buttonR4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)20);
            }
        });
        findViewById(R.id.buttonG).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)5);
            }
        });
        findViewById(R.id.buttonG1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)9);
            }
        });
        findViewById(R.id.buttonG2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)13);
            }
        });
        findViewById(R.id.buttonG3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)17);
            }
        });
        findViewById(R.id.buttonG4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)21);
            }
        });
        findViewById(R.id.buttonB).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)6);
            }
        });
        findViewById(R.id.buttonB1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)10);
            }
        });
        findViewById(R.id.buttonB2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)14);
            }
        });
        findViewById(R.id.buttonB3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)18);
            }
        });
        findViewById(R.id.buttonB4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)22);
            }
        });
        findViewById(R.id.buttonW).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)7);
            }
        });
        findViewById(R.id.buttonW1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)11);
            }
        });
        findViewById(R.id.buttonW2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)15);
            }
        });
        findViewById(R.id.buttonW3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)19);
            }
        });
        findViewById(R.id.buttonW4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)23);
            }
        });
        findViewById(R.id.buttonWakeUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)24);
            }
        });
        findViewById(R.id.buttonFade).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)25);
            }
        });
        findViewById(R.id.btnQuit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendCommand((byte)255);
            }
        });
        ((ToggleButton)findViewById(R.id.toggleLightify)).setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                new LightifyManager().execute(
                        new LightifyDetails(Config.LIGHTIFY_USERNAME, Config.LIGHITFY_PASSWORD,
                                Config.LIGHTIFY_SERIAL_NO,
                                isChecked, getApplicationContext()));
            }
        });
    }
}
