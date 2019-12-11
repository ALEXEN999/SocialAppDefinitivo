package com.alexen.social;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PublicationViewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;

    public PublicationViewHolder(@NonNull View itemView, ImageView imageView) {
        super(itemView);
        this.imageView = imageView;
    }


}
