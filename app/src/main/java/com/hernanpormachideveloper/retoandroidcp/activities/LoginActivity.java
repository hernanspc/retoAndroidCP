    package com.hernanpormachideveloper.retoandroidcp.activities;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Intent;
    import android.os.Bundle;

    import com.hernanpormachideveloper.retoandroidcp.R;

    import android.view.View;
    import android.widget.Button;

    public class LoginActivity extends AppCompatActivity {

        private Button btnGoToCandystoreAsGuest;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            btnGoToCandystoreAsGuest = findViewById(R.id.btnEnterAsGuest);

            btnGoToCandystoreAsGuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     Intent intent = new Intent(LoginActivity.this, CandystoreActivity.class);
                    startActivity(intent);
                }
            });
        }
    }

