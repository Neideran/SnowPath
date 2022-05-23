package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.View.Adapers.LikedAdapter;
import com.example.myappsnowpath.ViewModel.LikedViewModel;


public class LikedFragment extends Fragment {

    RecyclerView recyclerView;
    LikedViewModel viewModel;

    public LikedFragment() { }

    public static LikedFragment newInstance() {
        return new LikedFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_liked, container, false);
        ((MainActivity) requireActivity()).setActionBarTitle("Избранное");

        recyclerView = view.findViewById(R.id.recycler_view_liked);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        viewModel = new ViewModelProvider(requireActivity()).get(LikedViewModel.class);
        if(viewModel.getLikedData() != null){
            LikedAdapter adapter = new LikedAdapter(requireActivity());
            recyclerView.setAdapter(adapter);
        }
        return  view;
    }
}