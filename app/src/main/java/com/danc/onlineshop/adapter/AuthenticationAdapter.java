package com.danc.onlineshop.adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.danc.onlineshop.Fragments.LoginFragment;
import com.danc.onlineshop.Fragments.RegisterFragment;

public class AuthenticationAdapter extends FragmentPagerAdapter {

    Context context;
    private int NUM_TABS;

    public AuthenticationAdapter(@NonNull FragmentManager fm, Context context, int NUM_TABS) {
        super(fm);
        this.context = context;
        this.NUM_TABS = NUM_TABS;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                LoginFragment loginFragment = new LoginFragment();
                return loginFragment;

            case 1:
                RegisterFragment registerFragment = new RegisterFragment();
                return registerFragment;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_TABS;
    }
}
