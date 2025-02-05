package com.alexen.social.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;

import static android.app.Activity.RESULT_OK;


public class UserFragment extends Fragment {
    private Button button;
    SocialAppViewModel socialAppViewModel;
    NavController navController;
    ImageView imagen;
    MainActivity mainActivity = new MainActivity();
    RegisterFragment registerFragment;
    String url = "";
    String email = "";
    String username = "";
    String password = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);
        navController = Navigation.findNavController(view);

        imagen = view.findViewById(R.id.imagenAccount);
        button = view.findViewById(R.id.buttonFoto);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("IntentReset")
            @Override
            public void onClick(View v) {
                cargarFoto();
            }
        });

        socialAppViewModel.estadoDelRegistro.observe(getViewLifecycleOwner(), new Observer<SocialAppViewModel.EstadoDelRegistro>() {
            @Override
            public void onChanged(SocialAppViewModel.EstadoDelRegistro estadoDelRegistro) {
                switch (estadoDelRegistro){
                    case NOMBRE_NO_DISPONIBLE:
                        Log.e("ABCD", "NOMBRE NO DISPONILBE");
                        Toast.makeText(requireContext(),"Username o Email NO DISPONIBLE",Toast.LENGTH_SHORT).show();

                        break;
                }
            }
        });

        socialAppViewModel.estadoDelLogin.observe(getViewLifecycleOwner(), new Observer<SocialAppViewModel.EstadoDelLogin>() {
            @Override
            public void onChanged(SocialAppViewModel.EstadoDelLogin estadoDelLogin) {
                switch (estadoDelLogin){
                    case LOGIN_COMPLETADO:
                        navController.navigate(R.id.navigation_accountT);
                        break;
                }
            }
        });

    }

    public void cargarFoto(){
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent,"Seleccione la aplicacion"),10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            Uri path = data.getData();
            imagen.setImageURI(path);
            url = String.valueOf(path);

            username = socialAppViewModel.username;
            email = socialAppViewModel.email;
            password = socialAppViewModel.password;


            socialAppViewModel.registrarUsuarioAndLogin(username,email,password,url);
        }
    }
}
