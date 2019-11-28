package com.alexen.social.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.alexen.social.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {

    Button buttonIniciarSesion;
    Button buttonRegistrar;
    EditText emailEdit;
    EditText passwordEdit;

    String email;
    String password;

    public LoginFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Initialize Firebase Auth
        emailEdit = view.findViewById(R.id.editTextUsername);
        passwordEdit = view.findViewById(R.id.editTextPassword);
        buttonIniciarSesion = view.findViewById(R.id.buttonIniciarSesion);
        buttonRegistrar = view.findViewById(R.id.buttonIniciarRegistro);

        buttonIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = emailEdit.getText().toString();
                password = passwordEdit.getText().toString();
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
