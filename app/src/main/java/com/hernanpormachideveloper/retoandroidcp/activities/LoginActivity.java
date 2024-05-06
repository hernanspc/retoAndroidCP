    package com.hernanpormachideveloper.retoandroidcp.activities;

    import androidx.appcompat.app.AppCompatActivity;

    import android.content.Context;
    import android.content.Intent;
    import android.os.Bundle;

    import com.hernanpormachideveloper.retoandroidcp.R;

    import android.view.View;
    import android.widget.Button;

    public class LoginActivity extends AppCompatActivity {

        private Button btnGoToAsGuest;
        private Context context; // Declarar una variable context

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            // Inicializar la variable context
            context = this;

            btnGoToAsGuest = findViewById(R.id.btnEnterAsGuest);

            btnGoToAsGuest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Usar la variable context en lugar de LoginActivity.this
                    Intent intent = new Intent(context, CandystoreActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
