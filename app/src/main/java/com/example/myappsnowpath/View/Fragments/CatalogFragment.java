package com.example.myappsnowpath.View.Fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Adapers.CatalogAdapter;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CatalogViewModel;


public class CatalogFragment extends Fragment {

    CatalogViewModel viewModel;
    String parent;
    CatalogAdapter adapter;
    RecyclerView recyclerView;

    public CatalogFragment(String parent) {
        this.parent = parent;
    }

    public static CatalogFragment newInstance(String parent) {
        return new CatalogFragment(parent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_catalog, container, false);
        viewModel = new ViewModelProvider(requireActivity()).get(CatalogViewModel.class);
        if(parent.equals("null")) {
            ((MainActivity) requireActivity()).setActionBarTitle("Каталог");
        }else{
            ((MainActivity) requireActivity()).setActionBarTitle(parent);
            viewModel.getItemData(parent);
        }
        recyclerView = view.findViewById(R.id.recycler_view_catalog);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CatalogAdapter();
        adapter.setData(viewModel.getCatalogData(parent).getValue());
        recyclerView.setAdapter(adapter);
        return view;
    }
}