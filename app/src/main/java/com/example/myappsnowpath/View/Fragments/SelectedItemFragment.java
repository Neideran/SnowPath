package com.example.myappsnowpath.View.Fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CartViewModel;
import com.example.myappsnowpath.ViewModel.CatalogViewModel;
import com.example.myappsnowpath.ViewModel.LikedViewModel;
import com.google.android.material.button.MaterialButton;

import java.util.Objects;

public class SelectedItemFragment extends Fragment {

    TextView itemName, price, description, color, size, brand, sex, season;
    ImageView img;
    MaterialButton buttonLiked, buttonCart;

    int id;
    CatalogViewModel viewModel;
    LikedViewModel likedViewModel;
    CartViewModel cartViewModel;

    public SelectedItemFragment(int id) {
        this.id = id;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_selected_item, container, false);

        viewModel = new ViewModelProvider(requireActivity()).get(CatalogViewModel.class);

        likedViewModel = new ViewModelProvider(requireActivity()).get(LikedViewModel.class);
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        Item curItem = viewModel.getCurrItem(id).getValue();

        setObjectValues(Objects.requireNonNull(curItem), view);
        ((MainActivity) requireActivity()).setActionBarTitle(curItem.getBrand());

        buttonLiked.setOnClickListener(v -> {
            likedViewModel.addItemToLiked(curItem);
            Toast.makeText(getContext(), likedViewModel
                    .getToastMessage(), Toast.LENGTH_SHORT)
                    .show();
            });

        buttonCart.setOnClickListener(v -> {
            cartViewModel.addItemToCart(curItem);
            Toast.makeText(getContext(), cartViewModel
                    .getToastMessage(), Toast.LENGTH_SHORT)
                    .show();
        });

        return view;
    }

    public void setObjectValues(Item c,View view){

        buttonLiked = view.findViewById(R.id.button_item_liked);
        buttonCart = view.findViewById(R.id.button_item_cart);

        itemName = view.findViewById(R.id.text_temp_item_name);
        itemName.setText(c.getName());

        img = view.findViewById(R.id.img_temp_item);
        Glide.with(view.getContext()).load(c.getImage()).into(img);

        price = view.findViewById(R.id.price_item_temp);
        price.setText(cartViewModel.sharePrice(String.valueOf(c.getPrice())));

        description = view.findViewById(R.id.text_temp_item_description);
        description.setText(c.getDescription());

        color = view.findViewById(R.id.text_temp_item_val_color);
        color.setText(c.getColor());

        brand = view.findViewById(R.id.text_temp_item_val_brand);
        brand.setText(c.getBrand());

        season = view.findViewById(R.id.text_temp_item_val_season);
        season.setText(c.getSeason());

        sex = view.findViewById(R.id.text_temp_item_val_sex);
        sex.setText(c.getSex());

        size = view.findViewById(R.id.text_temp_item_val_size);
        size.setText(c.getSize());

        buttonCart = view.findViewById(R.id.button_item_cart);
        buttonLiked = view.findViewById(R.id.button_item_liked);
    }
}