package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CatalogViewModel;


public class LoadingFragment extends Fragment {

    ProgressBar progressBar;
    private int mProgressStatus = 0;
    private final Handler mHandler = new Handler();
    CatalogViewModel catalogViewModel;

    public LoadingFragment() {  }

    public static LoadingFragment newInstance() {
        return new LoadingFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_progress_bar, container, false);
        progressBar = view.findViewById(R.id.progressBar);

        catalogViewModel = new ViewModelProvider(requireActivity()).get(CatalogViewModel.class);
        catalogViewModel.getCatalogData("null");
        catalogViewModel.setDefaultItemData();

        new Thread(() -> {
            while (mProgressStatus < 100){
                mProgressStatus++;

                android.os.SystemClock.sleep(17);
                mHandler.post(() -> progressBar.setProgress(mProgressStatus));
            }
            mHandler.post(() -> {
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment()).commit();
                MainActivity.isLoaded = true;
            });
        }).start();

        return view;
    }
}