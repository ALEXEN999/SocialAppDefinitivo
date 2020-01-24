package com.alexen.social.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;
import com.alexen.social.manage.Entity.User;
import com.bumptech.glide.Glide;

import java.util.List;


public class HomeFragment extends Fragment {
    ImageButton imageButton;
    SocialAppViewModel socialAppViewModel;
    NavController navController;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return  inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);

        imageButton = view.findViewById(R.id.imageButtonElegirPost);


        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.subirPostFragment);
            }
        });


        final PublicationDetalleAdapter publicationDetalleAdapter;

        RecyclerView publicationDetalleRecyclerView = view.findViewById(R.id.publicationDetalleList);

        publicationDetalleAdapter = new HomeFragment.PublicationDetalleAdapter();
        publicationDetalleRecyclerView.setAdapter(publicationDetalleAdapter);

        socialAppViewModel.listaPublications.observe(getViewLifecycleOwner(), new Observer<List<Publication>>() {
            @Override
            public void onChanged(List<Publication> publications) {
                publicationDetalleAdapter.establecerListaPublicaciones(publications);
            }
        });

    }

    class PublicationDetalleAdapter extends RecyclerView.Adapter<PublicationDetalleAdapter.PublicationDetalleViewHolder> {
        List<Publication> publications;


        @NonNull
        @Override
        public PublicationDetalleAdapter.PublicationDetalleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new PublicationDetalleAdapter.PublicationDetalleViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_detalle_publication, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull final PublicationDetalleAdapter.PublicationDetalleViewHolder holder, final int position) {

            final Publication publication = publications.get(position);
            Glide.with(requireActivity()).load(socialAppViewModel.imgAccount).into(holder.accountImage);
            holder.username.setText(socialAppViewModel.usernametmp);
            holder.ubication.setText(publication.ubication);
            Glide.with(requireActivity()).load(R.drawable.image).into(holder.publicationSource);
            holder.likes.setText(String.valueOf(publication.likes));
            holder.disklike.setText(String.valueOf(publication.dislike));

            holder.likeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    publication.likes+=1;
                    holder.likes.setText(String.valueOf(publication.likes));
                }
            });
            holder.dislikeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    publication.dislike += 1;
                    holder.disklike.setText(String.valueOf(publication.dislike));
                }
            });


        }

        @Override
        public int getItemCount() {
            return publications == null ? 0 : publications.size();
        }

        public void establecerListaPublicaciones(List<Publication> publications){
            this.publications = publications;
            notifyDataSetChanged();
        }

        class PublicationDetalleViewHolder extends RecyclerView.ViewHolder {
            SocialAppViewModel socialAppViewModel;
            TextView coment, ubication,username,likes,disklike;
            ImageView publicationSource, accountImage;
            ImageButton likeButton, dislikeButton;
            int likeC = 0, dislikeC = 0;
            public PublicationDetalleViewHolder(@NonNull View itemView) {
                super(itemView);
//                coment = itemView.findViewById(R.id.textView_coment);
                ubication = itemView.findViewById(R.id.ubicationTextView);
                username = itemView.findViewById(R.id.userNametextView);
                likes = itemView.findViewById(R.id.countLikeTextView);
                disklike = itemView.findViewById(R.id.countDislikeTextView);
                publicationSource = itemView.findViewById(R.id.publicationImageView);
                accountImage = itemView.findViewById(R.id.userNamePublicationImageView);
                dislikeButton = itemView.findViewById(R.id.buttonDislikeImageButton);
                likeButton = itemView.findViewById(R.id.buttonLikeImageButton);
            }
        }
    }
}
