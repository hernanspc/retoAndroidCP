package com.hernanpormachideveloper.retoandroidcp.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hernanpormachideveloper.retoandroidcp.R;
import com.hernanpormachideveloper.retoandroidcp.activities.LoginActivity;
import com.hernanpormachideveloper.retoandroidcp.models.SliderItem;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderAdapterVH> {

    private Context context;
    private List<SliderItem> mSliderItems = new ArrayList<>();

    public SliderAdapter(Context context, List<SliderItem> sliderItems) {

        this.context = context;
        mSliderItems = sliderItems;
    }

    @Override
    public SliderAdapterVH onCreateViewHolder(ViewGroup parent) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_layout_item, null);
        return new SliderAdapterVH(inflate);
    }

    @Override
    public void onBindViewHolder(SliderAdapterVH viewHolder, final int position) {

        SliderItem sliderItem = mSliderItems.get(position);

        if (sliderItem.getImage()!=null){
            if (!sliderItem.getImage().isEmpty()) {
                Picasso.with(context).load(sliderItem.getImage()).into(viewHolder.imageViewSlider);
                viewHolder.textViewDescription.setText(sliderItem.getDescription());
                viewHolder.imageViewSlider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         Intent intent = new Intent(context, LoginActivity.class);
                        context.startActivity(intent);
                    }
                });
            }
        }

    }

    @Override
    public int getCount() {
        return mSliderItems.size();
    }

    class SliderAdapterVH extends SliderViewAdapter.ViewHolder {

        TextView textViewDescription;
        View itemView;
        ImageView imageViewSlider;

        public SliderAdapterVH(View itemView) {
            super(itemView);
            imageViewSlider = itemView.findViewById(R.id.imageViewSlider);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);

            this.itemView = itemView;
        }
    }

}
