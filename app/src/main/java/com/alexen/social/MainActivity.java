package com.alexen.social;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLData;

public class MainActivity extends AppCompatActivity {

    ImageView photoAccount;
    TextView followers, follows;
    EditText username;
    private final int PICKER =1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final BottomNavigationView bottomNavigationView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_accountT, R.id.navigation_notifications, R.id.navigation_login, R.id.navigation_Register, R.id.navigation_User)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(bottomNavigationView, navController);

        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller,
                                             @NonNull NavDestination destination, @Nullable Bundle arguments) {
                switch (destination.getId()){
                    case R.id.navigation_login:
//                      toolbar.setVisibility(View.GONE);
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    case R.id.navigation_Register:
//                      toolbar.setVisibility(View.GONE);
                        bottomNavigationView.setVisibility(View.GONE);
                        break;
                    default:
//                      toolbar.setVisibility(View.VISIBLE);
                        bottomNavigationView.setVisibility(View.VISIBLE);
                }
            }
        });



    }

//    public void guardarDatos(View view) throws IOException {
//
//        photoAccount=view.findViewById(R.id.imagenAccount);
//        username=view.findViewById(R.id.editTextUsername);
//        followers=view.findViewById(R.id.textViewCountFollowers);
//        follows=view.findViewById(R.id.textViewCountFollows);
//
//        String txtUsername = username.getText().toString();
////        int numberFollowers = Integer.parseInt(followers.toString());
////        int numberFollows = Integer.parseInt(follows.toString());
////        URL url = new URL(photoAccount.toString());
//
////        String urlPhoto = url.toString();
//
//
//
//        BaseDeDatos baseDeDatos = new BaseDeDatos(this,"DEMODB",null,1);
//
//        SQLiteDatabase db = baseDeDatos.getWritableDatabase();
//        if (db!=null){
//            ContentValues registroNuevo = new ContentValues();
//
//            registroNuevo.put("username",txtUsername);
////            registroNuevo.put("followers",numberFollowers);
////            registroNuevo.put("followws", numberFollows);
////            registroNuevo.put("accountPhoto",urlPhoto);
//
//            db.insert("DatosCuenta",null,registroNuevo);
//            Toast.makeText(this, "Datos almacenados", Toast.LENGTH_SHORT).show();
//        }
//    }
}
