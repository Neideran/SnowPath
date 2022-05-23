package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Adapers.CategoryAdapter;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CatalogViewModel;


public class CategoryFragment extends Fragment {

    String category;
    CatalogViewModel viewModel;
    SearchView searchView;
    CategoryAdapter adapter;
    Button sort;
    boolean isEdited = false;

    public CategoryFragment(String category, boolean isEdited) {
        this.category = category;
        this.isEdited = isEdited;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ((MainActivity) requireActivity()).setActionBarTitle(category);
        searchView = view.findViewById(R.id.search_view_item);
        sort = view.findViewById(R.id.button_sort);
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view_category_items);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);

        viewModel = new ViewModelProvider(requireActivity()).get(CatalogViewModel.class);
        adapter = new CategoryAdapter();

        if(isEdited){
            adapter.setCatalog(viewModel.getEditedItemData().getValue());
        } else {
            adapter.setCatalog(viewModel.getItemData(category).getValue());
        }

        recyclerView.setAdapter(adapter);
        itemSearch();
        itemSort();
        return view;
    }

    private void itemSearch(){
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String query)
            {
                adapter.setCatalog(viewModel.getSearch(query,category));
                return true;
            }
        });
    }

    private void itemSort(){
        sort.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SortFragment(category))
                    .addToBackStack(null)
                    .commit();
        });
    }
}