package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;

public class InfoFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ((MainActivity) requireActivity()).setActionBarTitle("О нас");
        return inflater.inflate(R.layout.fragment_info, container, false);
    }
}