package com.example.myappsnowpath.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.myappsnowpath.View.Fragments.LoadingFragment;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Fragments.CartFragment;
import com.example.myappsnowpath.View.Fragments.CatalogFragment;
import com.example.myappsnowpath.View.Fragments.HomeFragment;
import com.example.myappsnowpath.View.Fragments.InfoFragment;
import com.example.myappsnowpath.View.Fragments.LikedFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    public static boolean isLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_MyAppSnowPath);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_container, LoadingFragment.newInstance())
                .commit();

        BottomNavigationView btn_nav = findViewById(R.id.bottom_nav);
        btn_nav.setOnItemSelectedListener(navListener);
    }

    public void setActionBarTitle(String title){
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    private final BottomNavigationView.OnItemSelectedListener navListener = item -> {
        Fragment selectedFragment = null;
        switch (item.getItemId()){
            case R.id.homeFragment:
                selectedFragment = HomeFragment.newInstance();
                break;

            case R.id.catalogFragment:
                selectedFragment = CatalogFragment.newInstance("null");
                break;

            case R.id.cartFragment:
                selectedFragment = CartFragment.newInstance();
                break;

            case R.id.likedFragment:
                selectedFragment = LikedFragment.newInstance();
                break;

            case R.id.infoFragment:
                selectedFragment = new InfoFragment();
                break;
    }
        if (isLoaded) {
            assert selectedFragment != null;
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container
                            , selectedFragment).commit();
        }
        return true;
    };
}