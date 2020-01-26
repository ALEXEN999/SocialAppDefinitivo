package com.alexen.social.ui;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alexen.social.R;
import com.alexen.social.ViewModel.SocialAppViewModel;


public class ChatMuestraFragment extends Fragment {


    public ChatMuestraFragment() {
        // Required empty public constructor
    }
    LinearLayout linearLayout;
    ImageButton sendButton;
    EditText message;
    TextView msgOwner;

    SocialAppViewModel socialAppViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chat_muestra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);

        linearLayout = view.findViewById(R.id.linearLayoutChatResponse);
        linearLayout.setVisibility(View.GONE);
        message = view.findViewById(R.id.editTextResponse);
        sendButton = view.findViewById(R.id.imageButtonResponse);
        msgOwner = view.findViewById(R.id.textViewChatOwner);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                socialAppViewModel.msg = message.getText().toString();
                msgOwner.setText(socialAppViewModel.msg);
                linearLayout.setVisibility(View.VISIBLE);

            }
        });






    }
}
