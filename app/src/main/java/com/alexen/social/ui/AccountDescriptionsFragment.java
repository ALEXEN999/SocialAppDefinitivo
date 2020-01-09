package com.alexen.social.ui;


import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.Entity.User;
import com.bumptech.glide.Glide;

public class AccountDescriptionsFragment extends Fragment {

    SocialAppViewModel socialAppViewModel;
    NavController navController;

    TextView username, tagname, description;
    Button editarBuetton;
    public AccountDescriptionsFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account_descriptions, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);
        username = view.findViewById(R.id.userNameDescriptionTextView);
        tagname = view.findViewById(R.id.userNameDescription2TextView);
        description = view.findViewById(R.id.descriptionTextView);

        socialAppViewModel.usuarioLogeado.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null){
                    navController.navigate(R.id.navigation_login);
                } else {
                    mostrarDatoUser(user);
                    socialAppViewModel.usernametmp = user.username;
                    socialAppViewModel.descriptiontmp = user.descripcion;
                }
            }
        });



        editarBuetton = view.findViewById(R.id.editarPerfilButton);

        editarBuetton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navController.navigate(R.id.editarDescripcionFragment);
            }
        });
    }

    private void mostrarDatoUser(User user) {
        username.setText(String.valueOf(user.username));
        tagname.setText(String.valueOf("@"+user.username));

    }
}
