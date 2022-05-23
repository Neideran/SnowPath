package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Adapers.CartAdapter;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CartViewModel;


public class CartFragment extends Fragment {

    RecyclerView recyclerView;
    CartAdapter adapter;
    CartViewModel viewModel;
    TextView go_to_exec_view;
    TextView total_price_view;

    public CartFragment() { }

    public static CartFragment newInstance() { return new CartFragment(); }

    @Override
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);
        ((MainActivity) requireActivity()).setActionBarTitle("Корзина");
        go_to_exec_view = view.findViewById(R.id.button_go_to_exec);
        total_price_view = view.findViewById(R.id.total_price);
        recyclerView = view.findViewById(R.id.recycler_view_cart);
        LinearLayoutManager layoutManager= new LinearLayoutManager
                (view.getContext(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);

        viewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);

        if(viewModel.getCartData() != null){
            adapter = new CartAdapter(requireActivity(), total_price_view);
            total_price_view.setText(viewModel.sharePrice(String.valueOf(viewModel.getFullPrice())));
            recyclerView.setAdapter(adapter);
        }
        go_to_exec_view.setOnClickListener(view1 -> {
            if (viewModel.getCartData() !=  null) {
                if (viewModel.getFullPrice() != 0){
                    AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new RegistrationFragment())
                        .addToBackStack(null)
                        .commit();
            }
                else{
                    Toast.makeText(view.getContext(), "Корзина пуста", Toast.LENGTH_SHORT).show();
                }
            }
            else{
                Toast.makeText(view.getContext(), "Корзина пуста", Toast.LENGTH_SHORT).show();
            }
        });

        return  view;
    }
}