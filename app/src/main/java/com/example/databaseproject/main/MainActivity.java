package com.example.databaseproject.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.databaseproject.R;

public class MainActivity extends AppCompatActivity {

    private boolean isAdd = false;
    private long lastBackTime = 0;
    private MainFragment mainFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        if (savedInstanceState != null) {
            isAdd = savedInstanceState.getBoolean("isAdd");
        }

        if(!isAdd) {
            addMainFragment();
        }
    }

    private void addMainFragment() {
        if(mainFragment == null){
            mainFragment = new MainFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .add(R.id.main_container, mainFragment, "mainFragment")
                .commit();
        isAdd = true;
    }

    @Override
    public void onBackPressed() {
        if(System.currentTimeMillis() - lastBackTime <= 2000){
            super.onBackPressed();
        } else {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            lastBackTime = System.currentTimeMillis();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean("isAdd", isAdd);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}