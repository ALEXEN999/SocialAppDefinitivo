package com.alexen.social.ui;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;

import java.util.List;

public class AccountPublicationsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account_publications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SocialAppViewModel socialAppViewModel;
        NavController navController;

        final PublicationAdapter publicationAdapter;


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
    }

