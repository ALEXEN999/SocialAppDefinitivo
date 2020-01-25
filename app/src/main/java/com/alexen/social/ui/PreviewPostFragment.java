package com.alexen.social.ui;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.Publication;
import com.muddzdev.styleabletoast.StyleableToast;


public class PreviewPostFragment extends Fragment {

    NavController navController;
    SocialAppViewModel socialAppViewModel;
    EditText addComentario, addUbicacion;
    ImageView addPublication;

    String comentario, publication, ubication;
    public PreviewPostFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_preview_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = Navigation.findNavController(view);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        setHasOptionsMenu(true);


        addComentario = view.findViewById(R.id.editTextInsertComentario);
        addPublication = view.findViewById(R.id.imageViewInsertPublication);
        addUbicacion = view.findViewById(R.id.editTextInsertUbicacion);


        socialAppViewModel.estadoDelPost.observe(getViewLifecycleOwner(), new Observer<SocialAppViewModel.EstadoDelPost>() {
            @Override
            public void onChanged(SocialAppViewModel.EstadoDelPost estadoDelPost) {
                switch (estadoDelPost){
                    case POST_PUBLICADO:
                        new StyleableToast
                                .Builder(getContext())
                                .text("Post Publicado")
                                .textColor(Color.WHITE)
                                .textBold()
                                .stroke(5,5)
                                .cornerRadius(15)
                                .backgroundColor(0xFFB33030)
                                .show();
                        break;
                    case POST_NO_PUBLICADO:
                        new StyleableToast
                                .Builder(getContext())
                                .text("Post NO Publicado")
                                .textColor(Color.WHITE)
                                .textBold()
                                .stroke(5,5)
                                .cornerRadius(15)
                                .backgroundColor(0xFF66BB6A)
                                .show();
                        break;
                    default:
                        break;
                }
            }
        });
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_activity_arrow, menu);
        // You can look up you menu item here and store it in a global variable by
        // 'mMenuItem = menu.findItem(R.id.my_menu_item);'
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_arrow:
                comentario = addComentario.getText().toString();
                publication = addPublication.toString();
                ubication = addUbicacion.getText().toString();
                socialAppViewModel.insertarPost(new Publication(comentario,ubication,publication,socialAppViewModel.imgAccount,socialAppViewModel.usernametmp));
//                socialAppViewModel.rellenarListaPublication(socialAppViewModel.usernametmp,comentario,publication,socialAppViewModel.imgAccount,ubication);
                navController.navigate(R.id.navigation_home);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
