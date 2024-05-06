package com.hernanpormachideveloper.retoandroidcp.activities;

import static com.hernanpormachideveloper.retoandroidcp.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.hernanpormachideveloper.retoandroidcp.R;

public class CandystoreActivity extends AppCompatActivity {

   private Button btnGoToPaymentPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_candystore);


        btnGoToPaymentPage =  findViewById(id.btnGoToPaymentPage);

        btnGoToPaymentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CandystoreActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });

    }
}