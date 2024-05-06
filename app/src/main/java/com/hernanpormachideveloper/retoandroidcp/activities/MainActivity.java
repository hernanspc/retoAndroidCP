package com.hernanpormachideveloper.retoandroidcp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;

import com.hernanpormachideveloper.retoandroidcp.R;
import com.hernanpormachideveloper.retoandroidcp.adapters.SliderAdapter;
import com.hernanpormachideveloper.retoandroidcp.models.SliderItem;
import com.smarteist.autoimageslider.IndicatorAnimations;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btnGoToLogin;
    SliderView mSliderView;
    SliderAdapter mSliderAdapter;
    List<SliderItem> mSliderItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<SliderItem> mSliderItems = new ArrayList<>();
        mSliderItems.add(new SliderItem("https://upload.wikimedia.org/wikipedia/en/thumb/e/e9/Black_Widow_%282021_film%29_poster.jpg/220px-Black_Widow_%282021_film%29_poster.jpg", "Black Widow"));
        mSliderItems.add(new SliderItem("https://rukminim2.flixcart.com/image/850/1000/l0tweq80/sticker/p/v/e/small-godzilla-poster-sticker-self-adhesive-12-inch-x-18-inch-12-original-imagc8afhbkmnydp.jpeg?q=90&crop=falseg", "Godzilla"));
        mSliderItems.add(new SliderItem("https://m.media-amazon.com/images/I/51qboNmFw3L._AC_UF894,1000_QL80_DpWeblab_.jpg", "Kunfu Panda"));
        mSliderItems.add(new SliderItem("https://pics.filmaffinity.com/vaguito-844142389-large.jpg", "Vaguito"));
        mSliderItems.add(new SliderItem("https://diamondfilms.com/img/poster/65b8f763a195083b4d2cfef9c.jpg", "Atrapados en lo Profundo"));
        mSliderItems.add(new SliderItem("https://http2.mlstatic.com/D_NQ_NP_821271-MLB48233421696_112021-O.webp", "Duna"));

        mSliderView = findViewById(R.id.imageSlider);
        mSliderAdapter = new SliderAdapter(MainActivity.this,mSliderItems );
        mSliderView.setSliderAdapter(mSliderAdapter);
        mSliderView.setIndicatorAnimation(IndicatorAnimations.WORM);
        mSliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        mSliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_RIGHT);
        mSliderView.setIndicatorSelectedColor(Color.WHITE);
        mSliderView.setIndicatorUnselectedColor(Color.GRAY);
        mSliderView.setScrollTimeInSec(3);
        mSliderView.setAutoCycle(true);
        mSliderView.startAutoCycle();

        btnGoToLogin = findViewById(R.id.btnGoToMainActivity2);
        btnGoToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
