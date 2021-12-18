package com.example.kijinews;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class kijiAdapter extends RecyclerView.Adapter<kijiAdapter.kijiHolder> {
    private ArrayList<kiji> kijis;
    private RecyclerListener recyclerListener;

    public kijiAdapter(ArrayList<kiji> kijis, RecyclerListener recyclerListener) {
        this.kijis = kijis;
        this.recyclerListener = recyclerListener;
    }

    @NonNull
    @Override
    public kijiHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.card_home,
                parent,
                false);
        return new kijiHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull kijiHolder holder, int position) {
        kiji kiji = kijis.get(position);
        holder.titleItem.setText(kiji.getTitle());
        holder.categoryItem.setText(kiji.getCategory());
        Glide.with(holder.itemView.getContext())
                .load(kiji.getPicture())
                .apply(new RequestOptions().override(500))
                .into(holder.photoItem);
    }

    @Override
    public int getItemCount() {
        return kijis.size();
    }

    public interface RecyclerListener{
        void onClick(View v, int position);
    }

    public class kijiHolder extends RecyclerView.ViewHolder {
        TextView titleItem, categoryItem, descriptionItem;
        ImageView photoItem;

        public kijiHolder(@NonNull View itemView) {
            super(itemView);
            titleItem    = itemView.findViewById(R.id.cardhome_textView_title);
            categoryItem = itemView.findViewById(R.id.cardhome_textView_category);
            photoItem = itemView.findViewById(R.id.cardhome_imageView_image);
            itemView.setOnClickListener(clickListener);
        }

        public View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recyclerListener.onClick(itemView, getAdapterPosition());
            }
        };
    }
}
