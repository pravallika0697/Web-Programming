package com.github.lvp4b.loginapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

public class ViewActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        Button logout = findViewById(R.id.logout);

        //Prompt user for confirmation
        logout.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                .setTitle("Logging out")
                .setMessage("Are you sure you want to exit?")
                // Redirects user to login screen
                .setPositiveButton("Yes", (dialog, which) -> {
                    Intent redirect = new Intent(ViewActivity.this, MainActivity.class);
                    startActivity(redirect);
                    finish();
                // Returns user to current screen
                }).setNegativeButton("No", null).show();
        });
    }
}
