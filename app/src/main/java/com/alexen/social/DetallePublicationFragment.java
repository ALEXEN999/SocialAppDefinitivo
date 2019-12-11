package com.alexen.social;

import android.content.Context;
import android.net.Uri;
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

import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;


public class DetallePublicationFragment extends Fragment {

    SocialAppViewModel socialAppViewModel;
    ImageView imageView;

    public DetallePublicationFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detalle_publication, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);

        imageView = view.findViewById(R.id.imageViewHolder);

        socialAppViewModel.publicationSeleccionado.observe(getViewLifecycleOwner(), new Observer<Publication>() {
            @Override
            public void onChanged(Publication publication) {
                if (publication == null)return;

                imageView.setImageURI(Uri.parse(publication.urlPublicationSource));

            }
        });
    }
}
