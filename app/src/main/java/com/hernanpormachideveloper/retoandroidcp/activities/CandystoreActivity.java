package com.hernanpormachideveloper.retoandroidcp.activities;

import static com.hernanpormachideveloper.retoandroidcp.R.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.hernanpormachideveloper.retoandroidcp.R;
import com.hernanpormachideveloper.retoandroidcp.adapters.CandyItemAdapter;
import com.hernanpormachideveloper.retoandroidcp.models.CandyItem;
import com.hernanpormachideveloper.retoandroidcp.models.CandyStoreResponse;
import com.hernanpormachideveloper.retoandroidcp.services.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CandystoreActivity extends AppCompatActivity {

    private Button btnGoToPaymentPage;
    private TextView txtTvTotalPrice;
    private ApiService apiService;
    private RecyclerView recyclerView;
    private List<CandyItem> candyItems;
    private CandyItemAdapter adapter;
    private double totalPrice = 0.0;

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

        recyclerView = findViewById(R.id.recyclerView);
        txtTvTotalPrice = findViewById(R.id.tvTotalPrice);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        candyItems = new ArrayList<>();
        adapter = new CandyItemAdapter(candyItems, new CandyItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(CandyItem item) {
                if (item.isSelected()) {
                    totalPrice -= item.getPrice();
                } else {
                    totalPrice += item.getPrice();
                }
                item.setSelected(!item.isSelected());
                updateTotalPrice();
            }
        });
        recyclerView.setAdapter(adapter);

        btnGoToPaymentPage =  findViewById(id.btnGoToPaymentPage);
        btnGoToPaymentPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CandystoreActivity.this, PaymentActivity.class);
                intent.putExtra("totalPrice", totalPrice);
                startActivity(intent);
            }
        });
    }

    private void updateTotalPrice() {
        txtTvTotalPrice.setText("Total: S/." + String.format("%.2f", totalPrice));

    }

    private void callApiCandystore() {
        apiService.getCandyStoreItems().enqueue(new Callback<CandyStoreResponse>() {
            @Override
            public void onResponse(Call<CandyStoreResponse> call, Response<CandyStoreResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CandyStoreResponse candyStoreResponse = response.body();
                    List<CandyItem> candyItemsFromResponse = candyStoreResponse.getItems();

                    candyItems.clear();
                    candyItems.addAll(candyItemsFromResponse);
                    adapter.notifyDataSetChanged();

                } else {
                    showErrorDialog();
                    Log.e("CandystoreActivity", "Error en la respuesta de la API");
                }
            }

            @Override
            public void onFailure(Call<CandyStoreResponse> call, Throwable t) {
                showErrorDialog();
                Log.e("CandystoreActivity", "Error de conexión con la API", t);
                Toast.makeText(CandystoreActivity.this, "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Ocurrió un error al obtener los productos. Por favor, inténtalo de nuevo más tarde.");
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}