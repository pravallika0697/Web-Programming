package com.example.snehadidigam.myapplicationcalender;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {
    CalendarView calendar;
    TextView textview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeCalendar();
        show();
    }

    public void createEvent(View v){
        calendar = (CalendarView) findViewById(R.id.calendar);
        textview = (TextView) findViewById(R.id.textView);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
// add code here
                String date_str= String.valueOf(month+1)+"-"+String.valueOf(dayOfMonth)+"-"+String.valueOf(year);
                //String date= date_str + "00:00:00
                String date="02-23-2019 00:00:00";
                Calendar cal = Calendar.getInstance();
                cal.set(year,month,dayOfMonth);
                cal.getTimeInMillis();
                //Calendar cal = Calendar.getInstance();
                Intent intent = new Intent(Intent.ACTION_EDIT);
                intent.setType("vnd.android.cursor.item/event");
                intent.putExtra("beginTime", cal.getTimeInMillis());
                //intent.putExtra("beginTime", timeInMillis);

                intent.putExtra("allDay", false);
                intent.putExtra("rrule", "FREQ=YEARLY");
                intent.putExtra("endTime", cal.getTimeInMillis()+60*60*1000);
                //System.out.println(cal.getTimeInMillis()+60*60*1000);
                intent.putExtra("title", "");
                startActivity(intent);


            }
        });



    }

    public void show() {
        calendar = (CalendarView) findViewById(R.id.calendar);
        textview = (TextView) findViewById(R.id.textView);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
// add code here
                String date_str= String.valueOf(month+1)+"/"+String.valueOf(dayOfMonth)+"/"+String.valueOf(year);
                textview.setText(date_str);
            }
        });
    }
    public  void displaySelectedDate(){
        long selectedDate = calendar.getDate();
        Date date=new Date(selectedDate);
        textview.setText(String.valueOf(date));
    }

    public void initializeCalendar() {

        calendar = (CalendarView) findViewById(R.id.calendar);

        textview = (TextView) findViewById(R.id.textView);
        // sets whether to show the week number.

        calendar.setShowWeekNumber(false);


        // sets the first day of week according to Calendar.

        // here we set Monday as the first day of the Calendar

        calendar.setFirstDayOfWeek(2);


        // The background color for the selected week.

        calendar.setSelectedWeekBackgroundColor(getResources().getColor(

                R.color.green));


        // sets the color for the dates of an unfocused month.

        calendar.setUnfocusedMonthDateColor(getResources().getColor(

                R.color.transparent));


        // sets the color for the separator line between weeks.

        calendar.setWeekSeparatorLineColor(getResources().getColor(

                R.color.transparent));


        // sets the color for the vertical bar shown at the beginning and      //  at the end of the selected date.

        calendar.setSelectedDateVerticalBar(R.color.darkgreen);


        // sets the listener to be notified upon selected date change.

        calendar.setOnDateChangeListener(new OnDateChangeListener() {


            // show the selected date as a toast

            @Override

            public void onSelectedDayChange(CalendarView view, int year,

                                            int month, int day) {

                Intent calIntent = new Intent(Intent.ACTION_INSERT);

                calIntent.setType("vnd.android.cursor.item/event");

//                calIntent.putExtra(Events.TITLE, "My BirthDay Party");
//
//                calIntent.putExtra(Events.EVENT_LOCATION, "My City");
//
//                calIntent.putExtra(Events.DESCRIPTION, "My 22th birthday");


                GregorianCalendar calDate = new GregorianCalendar(year, month, day);

                calIntent.putExtra(CalendarContract.EXTRA_EVENT_ALL_DAY, true);

                calIntent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME,

                        calDate.getTimeInMillis());

                calIntent.putExtra(CalendarContract.EXTRA_EVENT_END_TIME,

                        calDate.getTimeInMillis());
                long selectedDate = calendar.getDate();
                Date date=new Date(selectedDate);
                textview.setText(String.valueOf(date));
                startActivity(calIntent);


            }

        });

    }
}