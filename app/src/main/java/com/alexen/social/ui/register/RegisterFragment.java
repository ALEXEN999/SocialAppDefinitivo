package com.alexen.social.ui.register;

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
import androidx.navigation.Navigation;

import com.alexen.social.ui.MainActivity;
import com.alexen.social.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.ContentValues.TAG;

public class RegisterFragment extends Fragment {
    private FirebaseAuth mAuth;
    Button buttonRegistrar;
    EditText emailEdit;
    EditText passwordEdit;
    EditText usernameEdit;
    MainActivity mainActivity = new MainActivity();

    private String email;
    private String username;
    private String password;

    public RegisterFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_register, container, false);
    }


    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        // Initialize Firebase Auth
        usernameEdit =view.findViewById(R.id.editTextUsername);
        emailEdit = view.findViewById(R.id.editTextEmail);
        passwordEdit = view.findViewById(R.id.editTextPassword);
        buttonRegistrar = view.findViewById(R.id.buttonIniciarRegistro);


        buttonRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = emailEdit.getText().toString();
                password = passwordEdit.getText().toString();
                username = usernameEdit.getText().toString();
                setUsername(username);
                createAccount();
                try {
                    guardarUser();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void updateUI(FirebaseUser currentUser) {
        if (currentUser != null) Navigation.findNavController(requireView()).navigate(R.id.navigation_User);

    }

    public void guardarUser () throws IOException {



        File file = new File(getContext().getFilesDir(),"datosAccount.txt");



        FileOutputStream fileOutputStream = new FileOutputStream(file);
        fileOutputStream.write(username.getBytes());
        Log.d("TAG1", "Fiechero salvado:"+getContext().getFilesDir()+"/datosAcount.txt");
        fileOutputStream.close();

    }
    public  void createAccount(){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(requireActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
