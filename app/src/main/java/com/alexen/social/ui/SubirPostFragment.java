package com.alexen.social.ui;


import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.alexen.social.R;
import com.muddzdev.styleabletoast.StyleableToast;


public class SubirPostFragment extends Fragment {
    ImageButton back, next;
    NavController navController;
    public SubirPostFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subir_post, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);

        navController = Navigation.findNavController(view);

        new StyleableToast
                .Builder(getContext())
                .text("Esta es la foto que publicarás")
                .textColor(Color.WHITE)
                .textBold()
                .stroke(5,5)
                .cornerRadius(15)
                .iconStart(R.drawable.jesus)
                .backgroundColor(0xFF66BB6A)
                .show();
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_activity_arrow, menu);
        // You can look up you menu item here and store it in a global variable by
        // 'mMenuItem = menu.findItem(R.id.my_menu_item);'
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            case R.id.action_arrow:
                navController.navigate(R.id.previewPostFragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
