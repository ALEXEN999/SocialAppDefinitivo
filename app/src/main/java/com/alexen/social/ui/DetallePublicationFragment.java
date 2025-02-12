package com.alexen.social.ui;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Notificacion;
import com.alexen.social.manage.Entity.User;
import com.alexen.social.manage.Entity.Publication;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePublicationFragment extends Fragment {

    SocialAppViewModel socialAppViewModel;
    TextView coment, ubication,username,likes,disklike,usernameComment,userJesus;
    ImageView accountImage,accountImageComment;
    ImageButton likeButton, dislikeButton;
    PhotoView publicationSource;
    Notificacion notificacion;

    public DetallePublicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detalle_publication, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);

        accountImageComment = view.findViewById(R.id.userNamePublicationImageView2);
        coment = view.findViewById(R.id.textViewComentario);
        ubication = view.findViewById(R.id.ubicationTextView);
        username = view.findViewById(R.id.userNametextView);
        likes = view.findViewById(R.id.countLikeTextView);
        disklike = view.findViewById(R.id.countDislikeTextView);
        usernameComment = view.findViewById(R.id.userNametextView2);
        userJesus = view.findViewById(R.id.userNametextViewJesus);




        publicationSource = view.findViewById(R.id.publicationImageView);
        accountImage = view.findViewById(R.id.userNamePublicationImageView);
        dislikeButton = view.findViewById(R.id.buttonDislikeImageButton);
        likeButton = view.findViewById(R.id.buttonLikeImageButton);


        socialAppViewModel.publicationSeleccionado.observe(getViewLifecycleOwner(), new Observer<Publication>() {
            @Override
            public void onChanged(final Publication publication) {
                if(publication == null) return;
                Glide.with(requireActivity()).load(socialAppViewModel.imgAccount).into(accountImage);
                username.setText(socialAppViewModel.usernametmp);
                ubication.setText(publication.ubication);
                Glide.with(requireActivity()).load(R.drawable.biblia).into(publicationSource);
                likes.setText(String.valueOf(publication.likes));
                disklike.setText(String.valueOf(publication.dislike));
                coment.setText(String.valueOf(publication.coment));
                Glide.with(requireActivity()).load(socialAppViewModel.imgAccount).into(accountImageComment);
                usernameComment.setText(socialAppViewModel.usernametmp);

                likeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publication.likes+=1;
                        likes.setText(String.valueOf(publication.likes));
                    }
                });
                dislikeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publication.dislike += 1;
                        disklike.setText(String.valueOf(publication.dislike));
                    }
                });
            }
        });

    }


}
