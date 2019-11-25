package com.alexen.social.ui.account;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager.widget.ViewPager;

import com.alexen.social.manage.Entity.DatosUser;
import com.alexen.social.model.SocialAppViewModel;
import com.alexen.social.ui.AccountPublicationsFragment;
import com.alexen.social.R;
import com.alexen.social.ui.register.RegisterFragment;
import com.alexen.social.ui.UserFragment;
import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import static android.app.Activity.RESULT_OK;

public class AccountFragment extends Fragment {
    SocialAppViewModel socialAppViewModel;

    ImageView imageView;
    TextView username;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        socialAppViewModel = ViewModelProviders.of(requireActivity()).get(SocialAppViewModel.class);

        imageView = view.findViewById(R.id.imageViewAccountPhoto);
        username = view.findViewById(R.id.textViewUsername);

        socialAppViewModel.datosUserMutableLiveData.observe(getViewLifecycleOwner(), new Observer<DatosUser>() {
            @Override
            public void onChanged(DatosUser datosUser) {
                if(datosUser == null){
                    // navigar loing
                } else {
                    mostrarPerfil(datosUser);
                }
            }
        });



        ViewPager viewPager = view.findViewById(R.id.viewPager);

        // F por viewPager
        viewPager.setAdapter(new DemoPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

//        if(user != null){
//            Log.i("Username", user);
//        }



    }

    private void mostrarPerfil(DatosUser datosUser) {
        username.setText(datosUser.username);
//        imageView.setImageURI(Uri.parse(datosUser.urlFoto));

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            Uri path = data.getData();
            imageView.setImageURI(path);
    }

    class DemoPagerAdapter extends FragmentPagerAdapter {

        public DemoPagerAdapter(FragmentManager fm, int behavior) {
            super(fm, behavior);

        }

        @Override
        public CharSequence getPageTitle(int position) {
            if (position == 0) {
                return "Publicaciones";
            } else if (position == 1) {
                return "Descripcion";
            } else  {
                return "Chats";
            }

        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            if(position == 0) {return new AccountPublicationsFragment();
            }else if (position == 1) {return new AccountDescriptionsFragment();
            }else {
                return new AccountChatsFragment();
            }


        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}