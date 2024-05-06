package com.hernanpormachideveloper.retoandroidcp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.hernanpormachideveloper.retoandroidcp.R;

public class PaymentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        double totalPrice = getIntent().getDoubleExtra("totalPrice", 0.0);

        TextView totalPriceTextView = findViewById(R.id.txtTotalPrice);
        totalPriceTextView.setText(String.valueOf(totalPrice));
    }
}