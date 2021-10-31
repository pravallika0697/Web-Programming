package com.github.lvp4b.pizzaorderingapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    final int pizza_price = 5;
    final int proni_price = 1;
    final int ham_price = 1;
    final int peppers_price = 1;
    final int pine_price = 1;
    int quantity = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method is called when the order button is clicked
    public void submitOrder(View view){
        // gets the user's input
        EditText inputView = (EditText) findViewById(R.id.input);
        String userInputName = inputView.getText().toString();

        CheckBox pepperoni = (CheckBox) findViewById(R.id.checkPepp);
        boolean hasPepperoni = pepperoni.isChecked();

        // checks if ham is selected
        CheckBox ham = (CheckBox) findViewById(R.id.checkHam);
        boolean hasHam = ham.isChecked();

        // checks if peppers is selected
        CheckBox peppers = (CheckBox) findViewById(R.id.checkPeppers);
        boolean hasPeppers = peppers.isChecked();

        // checks if pineapple is selected
        CheckBox pineapple = (CheckBox) findViewById(R.id.checkPineapple);
        boolean hasPineapple = pineapple.isChecked();

        Intent intent = new Intent(MainActivity.this, SummaryOrderActivity.class);
        intent.putExtra("summary", createOrderSummary(userInputName, hasPepperoni, hasHam, hasPeppers, hasPineapple));

        startActivity(intent);
    }

    // calculates price
    private float calculatePrice(boolean hasPepperoni, boolean hasHam, boolean hasPeppers,  boolean hasPineapple) {
        int basePrice = pizza_price;

        if (hasPepperoni) {
            basePrice += proni_price;
        }
        if (hasHam) {
            basePrice += ham_price;
        }
        if (hasPeppers) {
            basePrice += peppers_price;
        }
        if (hasPineapple) {
            basePrice += pine_price;
        }
        return quantity * basePrice;
    }

    private String boolToString(boolean bool) {
        return bool ? (getString(R.string.yes)) : (getString(R.string.no));
    }

    // displays a list of items user has ordered
    private String createOrderSummary(String userInputName, boolean hasPepperoni, boolean hasHam, boolean hasPeppers,  boolean hasPineapple) {
        String orderSummaryMessage = getString(R.string.order_summary_name, userInputName) + "\n" +
                getString(R.string.order_summary_pepperoni, boolToString(hasPepperoni)) + "\n" +
                getString(R.string.order_summary_ham, boolToString(hasHam)) + "\n" +
                getString(R.string.order_summary_peppers, boolToString(hasPeppers)) + "\n" +
                getString(R.string.order_summary_pineapple, boolToString(hasPineapple)) + "\n" +
                getString(R.string.order_summary_quantity, quantity) + "\n" +
                getString(R.string.order_summary_total, calculatePrice(hasPepperoni, hasHam, hasPeppers, hasPineapple)) + "\n" +
                getString(R.string.thank_you);
        return orderSummaryMessage;
    }



    // update the number of quantity
    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    // checks if the quantity is less than 100
    public void increment(View view) {
        if (quantity < 100) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred pizza");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    // checks if the quantity is greater than 0
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select at least one pizza");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_little_pizza);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }


    }
}