package com.example.myappsnowpath.ViewModel;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Fragments.CatalogFragment;
import com.example.myappsnowpath.View.Fragments.CategoryFragment;
import com.example.myappsnowpath.View.Fragments.SelectedItemFragment;

public class HomeViewModel {

    public String imageUri(int imageNo){
        switch(imageNo){
            case 1:
                return "https://firebasestorage.googleapis.com/v0/b/snowpat" +
                        "h-3c582.appspot.com/o/main_ads%2FadsBase.jpeg?alt=media&token=e15c61e3-7bbc-4e1" +
                        "d-867a-fdc0446aa9ea";
            case 2:
                return "https://firebasestorage.googleapis.com/v0/b/snowpath" +
                        "-3c582.appspot.com/o/main_ads%2FadsCapita.jpg?alt=media&token=5c9ff81a-fe46-426" +
                        "6-bce9-860fa49c0f61";
            case 3:
                return "https://firebasestorage.googleapis.com/v0/b/snowpath" +
                        "-3c582.appspot.com/o/main_ads%2FhmMan.jpeg?alt=media&token=66a56991-47ea-49de-a52" +
                        "3-b14bf30a3b6b";
            case 4:
                return "https://firebasestorage.googleapis.com/v0/b/snowpat" +
                        "h-3c582.appspot.com/o/main_ads%2FH0245_01_bAgOnbJ.jpg?alt=media&token=d02becc1-a" +
                        "13c-4419-a5c4-b292c15b188d";
        }
        return null;
    }

    public void openCatalogTemp(View v, String parent){
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CatalogFragment(parent))
                .addToBackStack(null)
                .commit();
    }

    public void openCategoryTemp(View v, String category){
        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new CategoryFragment(category,false))
                .addToBackStack(null)
                .commit();
    }

    public void openSelectedItem(View v, int id){

        AppCompatActivity activity = (AppCompatActivity) v.getContext();
        activity.getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new SelectedItemFragment(id))
                .addToBackStack(null)
                .commit();
    }
}
