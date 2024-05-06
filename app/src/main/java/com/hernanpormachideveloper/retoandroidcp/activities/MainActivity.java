package com.hernanpormachideveloper.retoandroidcp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.hernanpormachideveloper.retoandroidcp.R;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToMainActivity2;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnGoToMainActivity2 = findViewById(R.id.btnGoToMainActivity2);

        btnGoToMainActivity2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}