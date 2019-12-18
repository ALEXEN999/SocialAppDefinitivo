package com.alexen.social.ui.account;

import android.content.Intent;
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
import androidx.navigation.NavController;
import androidx.viewpager.widget.ViewPager;

import com.alexen.social.manage.Entity.User;
import com.alexen.social.ViewModel.SocialAppViewModel;
import com.alexen.social.R;
import com.alexen.social.ui.AccountPublicationsFragment;
import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabLayout;

public class AccountFragment extends Fragment {
    SocialAppViewModel socialAppViewModel;
    NavController navController;

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

        socialAppViewModel.usuarioLogeado.observe(getViewLifecycleOwner(), new Observer<User>() {
            @Override
            public void onChanged(User user) {
                if(user == null){
                    navController.navigate(R.id.navigation_login);
                } else {
                    mostrarPerfil(user);

                }
            }
        });



        ViewPager viewPager = view.findViewById(R.id.viewPager);

        // F por viewPager
        viewPager.setAdapter(new DemoPagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT));

        TabLayout tabLayout = view.findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

    }

    private void mostrarPerfil(User user) {
        username.setText(String.valueOf(user.username));
        Glide.with(requireActivity()).load(Uri.parse(user.urlFoto)).into(imageView);

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