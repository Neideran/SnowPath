package com.example.myappsnowpath.View.Fragments;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myappsnowpath.Model.Order;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.MainActivity;
import com.example.myappsnowpath.ViewModel.CartViewModel;
import com.example.myappsnowpath.ViewModel.RegistrationViewModel;

import java.util.HashMap;

public class RegistrationFragment extends Fragment {

    EditText name, email, phone, address;
    TextView price_item, price_del, price_tot;
    CardView exec;

    CartViewModel cartViewModel;
    RegistrationViewModel regViewModel;

    public RegistrationFragment() { }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_registration, container, false);
        ((MainActivity) requireActivity()).setActionBarTitle("Оформление заказа");
        cartViewModel = new ViewModelProvider(requireActivity()).get(CartViewModel.class);
        regViewModel = new ViewModelProvider(requireActivity()).get(RegistrationViewModel.class);
        setObjectValues(view);
        price_item.setText(cartViewModel.sharePrice(String.valueOf(cartViewModel.getFullPrice())));
        price_del.setText(cartViewModel.sharePrice(String.valueOf(regViewModel.countDelPrice(cartViewModel.getFullPrice()))));
        price_tot.setText(cartViewModel.sharePrice(String.valueOf(regViewModel.countTotPrice(cartViewModel.getFullPrice()))));
        execution();
        return view;
    }

    public void setObjectValues(View view){

        name = view.findViewById(R.id.editTextName);
        email = view.findViewById(R.id.editTextEmail);
        phone = view.findViewById(R.id.editTextPhone);
        address = view.findViewById(R.id.editTextAddress);
        price_item = view.findViewById(R.id.reg_price_1);
        price_del = view.findViewById(R.id.reg_price_2);
        price_tot = view.findViewById(R.id.reg_price_3);
        exec = view.findViewById(R.id.button_go_to_exec);
    }

    public void execution(){
        exec.setOnClickListener(view -> {

            boolean check = regViewModel.checkIfTextFilled(
                    name.getText().toString(),
                    phone.getText().toString(),
                    email.getText().toString(),
                    address.getText().toString());

            Toast.makeText(view.getContext(),regViewModel.getToastMessage(),Toast.LENGTH_SHORT).show();
            if (check) {
                HashMap<String,String> map = regViewModel.createOrderMap(cartViewModel.getCartData().getValue());
                Order order = new Order(regViewModel.randomString(),
                        name.getText().toString(),
                        address.getText().toString(),
                        email.getText().toString(),
                        phone.getText().toString(),
                        map,
                        cartViewModel.sharePrice(String.valueOf(cartViewModel.getFullPrice())) + " ₽");

                regViewModel.saveOrder(order);
                cartViewModel.clearCart();
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, new HomeFragment())
                        .commit();
            }
        });
    }
}