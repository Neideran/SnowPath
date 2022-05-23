package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.bumptech.glide.Glide;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.HomeViewModel;

public class HomeFragment extends Fragment {

    ImageButton adsView1, adsView2, adsView3, adsView4;

    Button buttonTagCatalog, buttonTagSki, buttonTagSnowboard, buttonTagClothes;
    Button buttonTagHelmetsMasks, buttonTagAccessories;
    HomeViewModel homeViewModel;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setActionBarTitle("SnowPath");
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        homeViewModel = new HomeViewModel();
        initView(view);
        return view;
    }

    public void initView(View view){

        adsView1 = view.findViewById(R.id.ads1);
        adsView2 = view.findViewById(R.id.ads2);
        adsView3 = view.findViewById(R.id.ads3);
        adsView4 = view.findViewById(R.id.ads4);

        buttonTagSki = view.findViewById(R.id.button_tag_ski);
        buttonTagSnowboard = view.findViewById(R.id.button_tag_snowboards);
        buttonTagCatalog = view.findViewById(R.id.button_tag_catalog);
        buttonTagAccessories = view.findViewById(R.id.button_tag_accessories);
        buttonTagClothes = view.findViewById(R.id.button_tag_clothes);
        buttonTagHelmetsMasks = view.findViewById(R.id.button_tag_helmets_masks);

        Glide.with(this).load(homeViewModel.imageUri(1)).into(adsView1);
        Glide.with(this).load(homeViewModel.imageUri(2)).into(adsView2);
        Glide.with(this).load(homeViewModel.imageUri(3)).into(adsView3);
        Glide.with(this).load(homeViewModel.imageUri(4)).into(adsView4);

        buttonTagCatalog.setOnClickListener(v -> homeViewModel.openCatalogTemp(view,"null"));
        buttonTagSki.setOnClickListener(v -> homeViewModel.openCatalogTemp(view,"Горные лыжи"));
        buttonTagSnowboard.setOnClickListener(v -> homeViewModel.openCatalogTemp(view,"Сноуборд"));
        buttonTagAccessories.setOnClickListener(v -> homeViewModel.openCatalogTemp(view,"Аксессуары"));
        buttonTagHelmetsMasks.setOnClickListener(v -> homeViewModel.openCatalogTemp(view, "Шлемы, маски"));
        buttonTagClothes.setOnClickListener(v -> homeViewModel.openCatalogTemp(view, "Одежда"));

        adsView1.setOnClickListener(v -> homeViewModel.openSelectedItem(view,10103));
        adsView2.setOnClickListener(v -> homeViewModel.openSelectedItem(view,10503));
        adsView3.setOnClickListener(v -> homeViewModel.openCategoryTemp(view,"Шлемы"));
        adsView4.setOnClickListener(v -> homeViewModel.openCategoryTemp(view,"Куртки"));
    }

}