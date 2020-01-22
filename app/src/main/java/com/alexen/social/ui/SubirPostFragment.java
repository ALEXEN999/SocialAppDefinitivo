package com.alexen.social.ui;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alexen.social.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SubirPostFragment extends Fragment {


    public SubirPostFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subir_post, container, false);
    }

}
