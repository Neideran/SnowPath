package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CatalogViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class SortFragment extends Fragment {

    Button sortButton;
    RadioButton by_default, by_name, exp_first, cheap_first, new_first;
    RadioGroup radioGroup;
    String category;
    CatalogViewModel viewModel;
    boolean isChecked = false;
    ArrayList<Item> sortList;

    public SortFragment(String category) { this.category = category; }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sort, container, false);
        ((MainActivity) requireActivity()).setActionBarTitle("Сортировка");
        init(view);
        viewModel = new ViewModelProvider(requireActivity()).get(CatalogViewModel.class);
        sortList = new ArrayList<>(
                Objects.requireNonNull(viewModel.getItemData(category).getValue()));
        sortButton.setOnClickListener(v -> {
            check(sortList);
            if(isChecked) {
                viewModel.setItemData(check(sortList));
                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CategoryFragment(category, true))
                        .commit();
            }
            else{
                Toast.makeText(v.getContext(), "Выберите тип сортировки", Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    public ArrayList<Item> check(ArrayList<Item> sortList){
        if(by_default.isChecked()){
            isChecked = true;
        } else if (by_name.isChecked()){
            sortList.sort(viewModel.NameComparator);
            isChecked = true;
        } else if (exp_first.isChecked()){
            sortList.sort(viewModel.PriceComparatorExp);
            isChecked = true;
        } else if (cheap_first.isChecked()){
            sortList.sort(viewModel.PriceComparatorCheap);
            isChecked = true;
        } else if (new_first.isChecked()) {
            sortList.sort(viewModel.SeasonComparator);
            isChecked = true;
        } else {
            isChecked = false;
        }
        return sortList;
    }

    public void init(View view){
        sortButton = view.findViewById(R.id.button_sorted);
        radioGroup = view.findViewById(R.id.rb_group_sort);
        by_default = view.findViewById(R.id.rb_sort_1);
        by_name = view.findViewById(R.id.rb_sort_2);
        exp_first = view.findViewById(R.id.rb_sort_3);
        cheap_first = view.findViewById(R.id.rb_sort_4);
        new_first = view.findViewById(R.id.rb_sort_5);
    }
}