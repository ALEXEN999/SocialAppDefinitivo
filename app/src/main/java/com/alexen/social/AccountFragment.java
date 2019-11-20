package com.alexen.social;

import android.os.Bundle;
import android.util.Log;
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
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class AccountFragment extends Fragment {
    UserFragment userFragment = new UserFragment();
    ImageView imageView;
    TextView username;

    RegisterFragment registerFragment = new RegisterFragment();
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        imageView = view.findViewById(R.id.imagenAccount);
        username = view.findViewById(R.id.textViewUsername);

        try {
            leerDatosAccount();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void leerDatosAccount() throws IOException {
        File file = new File(getContext().getFilesDir(),"datosAccount.txt");

        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String linea= "";
        StringBuilder stringBuilder= new StringBuilder();
        linea = bufferedReader.readLine();
        Boolean eof = false;
        while (!eof){
            if (linea == null){
                eof = true;
            }else {
                stringBuilder.append(linea).append("\n");
                linea = bufferedReader.readLine();
            }
        }

        username.setText(stringBuilder);
        inputStreamReader.close();
        bufferedReader.close();


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