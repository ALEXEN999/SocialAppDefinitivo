package com.alexen.social;

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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.content.ContentValues.TAG;

public class LoginFragment extends Fragment {
    private FirebaseAuth mAuth;
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
        mAuth = FirebaseAuth.getInstance();

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
                signIn();
            }
        });

        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(requireView()).navigate(R.id.navigation_Register);

            }
        });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        updateUI(currentUser);
//    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) Navigation.findNavController(requireView()).navigate(R.id.navigation_home);

    }

    public void signIn(){
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(),  new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
}
