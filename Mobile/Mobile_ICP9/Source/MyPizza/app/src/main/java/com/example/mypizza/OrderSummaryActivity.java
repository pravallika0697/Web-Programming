package com.example.mypizza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderSummaryActivity extends AppCompatActivity {
    TextView Details;
    Button BackToOrder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_summary);
        Details = (TextView)findViewById(R.id.Details);

        Details.setText(getIntent().getStringExtra("MESSAGE"));
        BackToOrder = findViewById(R.id.BackToOrder);

        BackToOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSummaryActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}