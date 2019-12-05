package com.alexen.social.ui;

import android.content.Context;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.ui.MainActivity;
import com.alexen.social.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;
import static com.alexen.social.ViewModel.SocialAppViewModel.*;

public class RegisterFragment extends Fragment {
    SocialAppViewModel socialAppViewModel;
    NavController navController;

    Button buttonRegistrar;
    EditText emailEdit;
    EditText passwordEdit;
    EditText usernameEdit;


    public RegisterFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);

        socialAppViewModel.reiniciarRegistro();

        usernameEdit =view.findViewById(R.id.editTextUsername);
        emailEdit = view.findViewById(R.id.editTextEmail);
        passwordEdit = view.findViewById(R.id.editTextPassword);
        buttonRegistrar = view.findViewById(R.id.buttonIniciarRegistro);

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialAppViewModel.email = emailEdit.getText().toString();
                socialAppViewModel.password = passwordEdit.getText().toString();
                socialAppViewModel.username = usernameEdit.getText().toString();

                navController.navigate(R.id.navigation_User);
            }
        });
    }
}
