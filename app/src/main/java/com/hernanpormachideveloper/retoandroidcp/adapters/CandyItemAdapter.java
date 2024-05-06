package com.hernanpormachideveloper.retoandroidcp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hernanpormachideveloper.retoandroidcp.R;
import com.hernanpormachideveloper.retoandroidcp.models.CandyItem;

import java.util.List;

public class CandyItemAdapter extends RecyclerView.Adapter<CandyItemAdapter.ViewHolder> {
    private List<CandyItem> candyItems;
    private OnItemClickListener listener;

    public CandyItemAdapter(List<CandyItem> candyItems, OnItemClickListener listener) {
        this.candyItems = candyItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_candy, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CandyItem item = candyItems.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return candyItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(CandyItem item);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtName, txtDescription, txtPrice;
        private CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtDescription = itemView.findViewById(R.id.txtDescription);
            txtPrice = itemView.findViewById(R.id.txtPrice);
            checkBox = itemView.findViewById(R.id.checkBox);
        }

        public void bind(final CandyItem item, final OnItemClickListener listener) {
            txtName.setText(item.getName());
            txtDescription.setText(item.getDescription());
            txtPrice.setText(String.valueOf(item.getPrice()));
            checkBox.setChecked(item.isSelected());

            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}
