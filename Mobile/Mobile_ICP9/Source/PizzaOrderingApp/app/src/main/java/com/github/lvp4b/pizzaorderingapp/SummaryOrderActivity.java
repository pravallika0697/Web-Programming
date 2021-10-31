package com.github.lvp4b.pizzaorderingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SummaryOrderActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summaryorder);

        String summary = getIntent().getExtras().getString("summary");
        ((TextView)findViewById(R.id.orderSummary)).setText(summary);
        Log.d("summary", summary);
    }

    public void sendEmail(View view) {
        Log.i("Send email", "");

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"pravallika.app@gmail.com"});
        intent.putExtra(Intent.EXTRA_SUBJECT, "Pizza Ordering App's Order");

        String summary = getIntent().getExtras().getString("summary");
        intent.putExtra(Intent.EXTRA_TEXT, summary);

        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
