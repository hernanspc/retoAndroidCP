package com.hernanpormachideveloper.retoandroidcp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.hernanpormachideveloper.retoandroidcp.R;
import android.content.Context;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToMainActivity2;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;

        btnGoToMainActivity2 = findViewById(R.id.btnGoToMainActivity2);

        btnGoToMainActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
