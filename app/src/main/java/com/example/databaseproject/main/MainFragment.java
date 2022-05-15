package com.example.databaseproject.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.paging.LoadState;
import androidx.viewpager.widget.ViewPager;

import com.example.databaseproject.DepositoryFragment;
import com.example.databaseproject.PurchaseFragment;
import com.example.databaseproject.R;
import com.example.databaseproject.SaleFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainFragment extends Fragment {

    private ViewPager viewPager;
    private BottomNavigationView bottomNavigationView;
    private List<Fragment> fragmentList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstance) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        viewPager = view.findViewById(R.id.viewpager);
        bottomNavigationView = view.findViewById(R.id.navigation);
        initFragment();
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.cangku:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.xiaoshou:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.jinhuo:
                    viewPager.setCurrentItem(2);
                    break;
            }
            return false;
        });
        return view;
    }

    private void initFragment() {
        fragmentList = new ArrayList<>(3);
        fragmentList.add(new DepositoryFragment());
        fragmentList.add(new SaleFragment());
        fragmentList.add(new PurchaseFragment());
        viewPager.setAdapter(new MainViewPagerAdapter(getChildFragmentManager(), fragmentList));
    }

}
