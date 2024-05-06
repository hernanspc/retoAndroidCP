package com.hernanpormachideveloper.retoandroidcp.activities;

import static com.hernanpormachideveloper.retoandroidcp.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.hernanpormachideveloper.retoandroidcp.models.CandyItem;
import com.hernanpormachideveloper.retoandroidcp.models.CandyStoreResponse;
import com.hernanpormachideveloper.retoandroidcp.services.ApiService;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CandystoreActivity extends AppCompatActivity {

   private Button btnGoToPaymentPage;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_candystore);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cp-staging.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);
        callApiCandystore();

        btnGoToPaymentPage =  findViewById(id.btnGoToPaymentPage);
        btnGoToPaymentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CandystoreActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }

    private void callApiCandystore() {
        apiService.getCandyStoreItems().enqueue(new Callback<CandyStoreResponse>() {
            @Override
            public void onResponse(Call<CandyStoreResponse> call, Response<CandyStoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CandyStoreResponse candyStoreResponse = response.body();
                    List<CandyItem> candyItems = candyStoreResponse.getItems();
                    // Aquí puedes hacer lo que necesites con los datos recibidos
                    // Por ejemplo, mostrarlos en un RecyclerView o en un ListView
                    System.out.println("****** " + candyItems.get(0).getName());
                } else {
                    Log.e("CandystoreActivity", "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<CandyStoreResponse> call, Throwable t) {
                Log.e("CandystoreActivity", "Error de conexión con la API", t);
                Toast.makeText(CandystoreActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }
}