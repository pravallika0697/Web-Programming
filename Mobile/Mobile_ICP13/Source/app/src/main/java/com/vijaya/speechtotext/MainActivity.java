package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.SharedPreferences;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;

import java.util.ArrayList;
import java.util.Locale;
import java.lang.Object;
import java.util.Date;
import java.text.Format;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    private final int CHECK_CODE = 0x1;

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageButton mSpeakBtn;
    private TextToSpeech mtts;
    private boolean ready = false;

    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private static final String PREFS = "prefs";
    private static final String NAME = "name";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(PREFS,0);
        editor = preferences.edit();

        mtts=new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    mtts.setLanguage(Locale.US);
                    mtts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                }
            }
        });

        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });
    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Please Say Hello!! to Contact Medical Assistant");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    recognition(result.get(0));
                }
                break;
            }

        }
    }

    private void recognition(String text){
        //creating an array which contains the words of the answer
        String name;
        String[] speech = text.split(" ");


        if(text.contains("hello")) {
            mtts.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">User : " +text+"</p>"));
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">Medical Assistant : What is your name ?</p>"));
        }
        if(text.contains("my name is"))
        {
            name = speech[speech.length-1];
            editor.putString(NAME,name).apply();
            mtts.speak("Your name is  "+preferences.getString(NAME,null), TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:black;\">.... Changing User's name as "+name+"</p>"));
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">"+name+ ": " +text+"</p>"));
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">Medical Assistant : Your name is"+preferences.getString(NAME,null)+"</p>"));


        }
        if(text.contains("not feeling good"))
        {
            mtts.speak("I can understand. Please tell your symptoms in short.", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">"+preferences.getString(NAME, null)+ ": " +text+"</p>"));
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">Medical Assistant : I can understand. Please tell your symptoms in short.</p>"));

        }
        if(text.contains("time"))
        {
            SimpleDateFormat sdfDate = new SimpleDateFormat("HH:mm");//dd/MM/yyyy
            Date now = new Date();
            String[] strDate = sdfDate.format(now).split(":");
            if(strDate[1].contains("00")) {
                strDate[1] = "o'clock";
            }
            mtts.speak("The time is " + sdfDate.format(now), TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">"+preferences.getString(NAME, null)+ ": " +text+"</p>"));
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">Medical Assistant : The time is " + sdfDate.format(now)+"</p>"));

        }
        if(text.contains("medicine"))
        {
            mtts.speak("I think you have fever. Please take this medicine.", TextToSpeech.QUEUE_FLUSH, null);
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">"+preferences.getString(NAME, null)+ ": " +text+"</p>"));
            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">Medical Assistant : I think you have fever. Please take this medicine.</p>"));

        }
        if(text.contains("thank you")) {
            if (text.contains("medical assistant")) {
                mtts.speak("Thank you too " + preferences.getString(NAME, null) + "Take care", TextToSpeech.QUEUE_FLUSH, null);
                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">"+preferences.getString(NAME, null)+ ": " +text+"</p>"));
                mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">Medical Assistant : Thank you too " + preferences.getString(NAME, null) +" Take care</p>"));

            }
        }
    }
}