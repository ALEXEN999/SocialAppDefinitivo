package com.alexen.social.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditarDescripcionFragment extends Fragment {
    SocialAppViewModel socialAppViewModel;
    NavController navController;

    EditText usernameEditText, descripcionEditText;
    Button guardarCambiosButton;

    public EditarDescripcionFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_editar_descripcion, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);

        guardarCambiosButton = view.findViewById(R.id.guardarCambiosButton);
        usernameEditText = view.findViewById(R.id.editTextUsernameProfile);
        descripcionEditText = view.findViewById(R.id.editTextDescriptionProfile);

        guardarCambiosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                socialAppViewModel.editarUser(usernameEditText.getText().toString(),descripcionEditText.getText().toString(), socialAppViewModel.usernametmp,socialAppViewModel.descriptiontmp);
                navController.navigate(R.id.navigation_accountT);
            }
        });
    }
}
