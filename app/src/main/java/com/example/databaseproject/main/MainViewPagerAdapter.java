package com.example.databaseproject.main;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class MainViewPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;

    public MainViewPagerAdapter(@NonNull FragmentManager fm, @NonNull List<Fragment> fragmentList) {
        super(fm);
        this.list = fragmentList;

    }

    @Override
    public int getCount() {
        return list.size();
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    public void setFragments(List<Fragment> fragmentList) {
        this.list = fragmentList;
    }
}
