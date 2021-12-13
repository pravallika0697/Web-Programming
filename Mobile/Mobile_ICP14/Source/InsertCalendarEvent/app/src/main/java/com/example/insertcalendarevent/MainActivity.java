
package com.example.insertcalendarevent;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    EditText tittle;
    EditText location;
    EditText description;
    Button createEvent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tittle = findViewById(R.id.etTitle);
        location = findViewById(R.id.etLocation);
        description = findViewById(R.id.etDescription);
        Button butinsert = (Button) findViewById(R.id.create_eventbut);
        butinsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Auto-generated method stub
                insert();
            }
        });
    }

    public void insert() {
        Intent intent = new Intent(Intent.ACTION_INSERT,
                CalendarContract.Events.CONTENT_URI);
        // Add the calendar event details
        intent.putExtra(CalendarContract.Events.TITLE, "");
        intent.putExtra(CalendarContract.Events.DESCRIPTION,
                "");
        intent.putExtra(CalendarContract.Events.EVENT_LOCATION,
                "");
        Calendar startTime = Calendar.getInstance();
        startTime.set(2021, 12, 12);
        intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,
                startTime.getTimeInMillis());
        intent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);
        // Use the Calendar app to add the new event.

        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}