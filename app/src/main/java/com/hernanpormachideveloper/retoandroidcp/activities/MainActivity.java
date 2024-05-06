package com.hernanpormachideveloper.retoandroidcp.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.hernanpormachideveloper.retoandroidcp.R;
import com.hernanpormachideveloper.retoandroidcp.adapters.SliderAdapter;
import com.hernanpormachideveloper.retoandroidcp.models.Premiere;
import com.hernanpormachideveloper.retoandroidcp.models.PremiereResponse;
import com.hernanpormachideveloper.retoandroidcp.models.SliderItem;
import com.hernanpormachideveloper.retoandroidcp.services.PremiereService;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToLogin;
    SliderView mSliderView;
    SliderAdapter mSliderAdapter;
    List<SliderItem> mSliderItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://cp-staging.onrender.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PremiereService service = retrofit.create(PremiereService.class);

        Call<PremiereResponse> call = service.getPremieres();
        call.enqueue(new Callback<PremiereResponse>() {
            @Override
            public void onResponse(Call<PremiereResponse> call, Response<PremiereResponse> response) {
                if (response.isSuccessful()) {
                    PremiereResponse premiereResponse = response.body();

                    List<SliderItem> mSliderItems = new ArrayList<>();
                    for (Premiere premiere : premiereResponse.getPremieres()) {
                        mSliderItems.add(new SliderItem(premiere.getImage(), premiere.getDescription()));
                    }

                    mSliderView = findViewById(R.id.imageSlider);
                    mSliderAdapter = new SliderAdapter(MainActivity.this,mSliderItems );
                    mSliderView.setSliderAdapter(mSliderAdapter);
                    mSliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
                    mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
                    mSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
                    mSliderView.setIndicatorSelectedColor(Color.WHITE);
                    mSliderView.setIndicatorUnselectedColor(Color.GRAY);
                    mSliderView.setScrollTimeInSec(5);
                    mSliderView.setAutoCycle(true);
                    mSliderView.startAutoCycle();
                } else {
                    showErrorDialog();
                }
            }

            @Override
            public void onFailure(Call<PremiereResponse> call, Throwable t) {
                showErrorDialog();
            }
        });



        /*
         btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
           */
    }

    private void showErrorDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Ocurrió un error al obtener las premieres. Por favor, inténtalo de nuevo más tarde.");
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
