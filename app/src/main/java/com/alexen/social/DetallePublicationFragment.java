package com.alexen.social;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;
import com.bumptech.glide.Glide;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetallePublicationFragment extends Fragment {

    SocialAppViewModel socialAppViewModel;
    TextView coment, ubication,username,likes,disklike;
    ImageView publicationSource, accountImage;

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

        coment = view.findViewById(R.id.textView_coment);
        ubication = view.findViewById(R.id.ubicationTextView);
        username = view.findViewById(R.id.userNametextView);
        likes = view.findViewById(R.id.countLikeTextView);
        disklike = view.findViewById(R.id.countDislikeTextView);
        publicationSource = view.findViewById(R.id.publicationImageView);
        accountImage = view.findViewById(R.id.userNamePublicationImageView);


        socialAppViewModel.publicationSeleccionado.observe(getViewLifecycleOwner(), new Observer<Publication>() {
            @Override
            public void onChanged(final Publication publication) {
                if(publication == null) return;

                Glide.with(requireActivity()).load(R.drawable.image).into(accountImage);
                username.setText(publication.usernameAccount);
                ubication.setText(publication.ubication);
                Glide.with(requireActivity()).load(R.drawable.image).into(publicationSource);
                likes.setText(publication.likes);
                disklike.setText(publication.dislike);
            }
        });
    }
}
