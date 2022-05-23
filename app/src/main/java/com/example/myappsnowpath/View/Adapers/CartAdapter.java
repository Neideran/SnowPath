package com.example.myappsnowpath.View.Adapers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Fragments.CartFragment;
import com.example.myappsnowpath.View.Fragments.SelectedItemFragment;
import com.example.myappsnowpath.ViewModel.CartViewModel;

import java.util.ArrayList;
import java.util.Objects;


public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    FragmentActivity fa;
    CartViewModel viewModel;
    ArrayList<Item> dataList;
    TextView total_price;

    public CartAdapter(FragmentActivity fa, TextView total_price) {
        viewModel = new ViewModelProvider(fa).get(CartViewModel.class);
        dataList = new ArrayList<>(Objects.requireNonNull(viewModel.getCartData().getValue()).keySet());
        this.fa = fa;
        this.total_price = total_price;
    }

    @NonNull
    @Override
    public CartAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.temp_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final CartAdapter.ViewHolder holder, int position) {

        Item temp = dataList.get(position);
        holder.text.setText(temp.getName());
        holder.loadImage(temp.getImage());
        holder.price.setText(viewModel.sharePrice(String.valueOf(temp.getPrice())));
        holder.item_value.setText(String.valueOf(Objects.requireNonNull(viewModel.getCartData().getValue()).get(temp)));

        holder.img.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SelectedItemFragment(temp.getId()))
                    .commit();
        });

        holder.add.setOnClickListener(view -> {
            viewModel.addItemToCart(temp);
            total_price.setText(viewModel.sharePrice(String.valueOf(viewModel.getFullPrice())));
            Toast.makeText(fa, viewModel.getToastMessage(), Toast.LENGTH_SHORT).show();
            holder.item_value.setText(String.valueOf(viewModel.getCartData().getValue().get(temp)));
        });

        holder.delete.setOnClickListener(view -> {
            viewModel.deleteFromCart(temp);
            total_price.setText(viewModel.sharePrice(String.valueOf(viewModel.getFullPrice())));
            Toast.makeText(fa, viewModel.getToastMessage(), Toast.LENGTH_SHORT).show();
            if (viewModel.getToastMessage().equals("Удалено из корзины")){
                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                activity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, new CartFragment())
                        .commit();
            }
            else{
                holder.item_value.setText(String.valueOf(viewModel
                        .getCartData().getValue().get(temp)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return Objects.requireNonNull(viewModel.getCartData().getValue()).keySet().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img;
        TextView text, price, item_value;
        Button add, delete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_temp_cart);
            text = itemView.findViewById(R.id.text_temp_cart);
            price = itemView.findViewById(R.id.price_temp_cart);

            add = itemView.findViewById(R.id.plus_cart);
            delete = itemView.findViewById(R.id.minus_cart);
            item_value = itemView.findViewById(R.id.items_number);
        }
        public void loadImage(String url) {
            Glide.with(itemView.getContext()).load(url).into(img);
        }
    }
}
