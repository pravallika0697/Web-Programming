package com.github.lvp4b.loginapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login = findViewById(R.id.login);

        final EditText usernameInput = findViewById(R.id.username);
        final EditText userPasswordInput = findViewById(R.id.password);

        login.setOnClickListener(v -> {
            String username = usernameInput.getText().toString();
            String password = userPasswordInput.getText().toString();

            // Checks for valid inputs and directs to view page
            if (!username.isEmpty() && !password.isEmpty()) {
                if (username.equals("Admin") && password.equals("Admin")) {
                    Intent redirect = new Intent(MainActivity.this, ViewActivity.class);
                    startActivity(redirect);
                }
                else {
                    // Display alert message
                    new AlertDialog.Builder(MainActivity.this)
                        .setMessage("Invalid Username or Password")
                        .setCancelable(true)
                        .setPositiveButton(
                                "OK", (dialog, id) -> dialog.cancel())
                        .show();

                }
            }
        });
    }
}