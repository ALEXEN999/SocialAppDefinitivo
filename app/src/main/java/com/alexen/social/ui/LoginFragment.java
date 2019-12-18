package com.alexen.social.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;

public class LoginFragment extends Fragment {

    Button buttonIniciarSesion;
    Button buttonRegistrar;
    EditText userEdit;
    EditText passwordEdit;

    String user;
    String password;
    SocialAppViewModel socialAppViewModel;
    NavController navController;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);
        // Initialize Firebase Auth
        userEdit = view.findViewById(R.id.editTextUsername);
        passwordEdit = view.findViewById(R.id.editTextPassword);
        buttonIniciarSesion = view.findViewById(R.id.buttonIniciarSesion);
        buttonRegistrar = view.findViewById(R.id.buttonIniciarRegistro);

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = userEdit.getText().toString();
                password = passwordEdit.getText().toString();
                socialAppViewModel.resetearEstadoUsuario();
                socialAppViewModel.loginUsuario(user, password);
                socialAppViewModel.estadoDelLogin.observe(getViewLifecycleOwner(), new Observer<SocialAppViewModel.EstadoDelLogin>() {
                    @Override
                    public void onChanged(SocialAppViewModel.EstadoDelLogin estadoDelLogin) {
                        switch (estadoDelLogin){
                            case LOGIN_COMPLETADO:
                                socialAppViewModel.username = user;
                                Log.e("EFGH",user + " "+ socialAppViewModel.username);
                                navController.navigate(R.id.navigation_accountT);
                                break;
                            case EMAIL_NO_DISPONIBLE:

                                break;
                            case CREDENCIALES_NO_VALIDAS:

                                break;
                        }
                    }
                });


            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.navigation_Register);

            }
        });
    }
}
