package com.alexen.social.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.manage.DataBase.SocialAppDataBase;
import com.alexen.social.manage.Entity.DatosUser;
import com.alexen.social.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

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

}
