package com.alexen.social;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;

import java.util.List;


public class ListaPublicacionesFragment extends Fragment {
    SocialAppViewModel socialAppViewModel;
    NavController navController;

    PublicationAdapter publicationAdapter;

    public ListaPublicacionesFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_lista_publicaciones, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);

        RecyclerView publicationRecyclerView = view.findViewById(R.id.item_list);
        publicationRecyclerView.addItemDecoration(new DividerItemDecoration(publicationRecyclerView.getContext(), DividerItemDecoration.VERTICAL));

        publicationAdapter = new PublicationAdapter();
        publicationRecyclerView.setAdapter(publicationAdapter);

        socialAppViewModel.listaPublications.observe(getViewLifecycleOwner(), new Observer<List<Publication>>() {
            @Override
            public void onChanged(List<Publication> publications) {
                publicationAdapter.establecerListaPublications(publications);
            }
        });
    }
}
