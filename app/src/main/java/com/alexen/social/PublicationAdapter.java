package com.alexen.social;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.RecyclerView;

import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;

import java.util.List;

public class PublicationAdapter extends RecyclerView.Adapter<PublicationAdapter.PublicationViewHolder> {

    List<Publication> publications;
    NavController navController;
    SocialAppViewModel socialAppViewModel;

    @NonNull
    @Override
    public PublicationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PublicationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_publication, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PublicationViewHolder holder, final int position) {

        final Publication publication = publications.get(position);

        holder.imageView.setImageURI(Uri.parse(publication.urlPublicationSource));



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                socialAppViewModel.establecerPublicacionSeleccionado(publication);
                navController.navigate(R.id.navigation_DetallePublicationFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return publications == null ? 0 : publications.size();
    }

    public void establecerListaPublications(List<Publication> publications){
        this.publications = publications;
        notifyDataSetChanged();
    }

    class PublicationViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;


        public PublicationViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewHolder);
        }
    }
}
