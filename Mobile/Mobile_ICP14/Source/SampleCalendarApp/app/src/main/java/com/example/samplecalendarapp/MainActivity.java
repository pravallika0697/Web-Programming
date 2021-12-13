
package com.example.samplecalendarapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    CalendarView calendarView;
    TextView myDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calenderview);
        myDate = (TextView) findViewById(R.id.myDate);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 +1) + "/" + i2 +"/"+ i;
                myDate.setText(date);

            }
        });
    }

    public void disp() {
        Calendar startTime = Calendar.getInstance();
        startTime.set(2021, 12, 12, 9, 9);

        Uri uri = Uri.parse("content://com.android.calendar/time/"
                + String.valueOf(startTime.getTimeInMillis()));

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);


        // Use the Calendar app to view the time.
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}