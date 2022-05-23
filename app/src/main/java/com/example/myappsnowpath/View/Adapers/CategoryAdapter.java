package com.example.myappsnowpath.View.Adapers;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.View.Fragments.SelectedItemFragment;
import com.example.myappsnowpath.ViewModel.CartViewModel;

import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>
{
    List<Item> data;
    CartViewModel viewModel;

    public CategoryAdapter() {}

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.temp_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position)
    {
        viewModel = new CartViewModel();
        final Item temp = data.get(position);
        holder.text.setText(temp.getName());
        holder.loadImage(temp.getImage());
        holder.price.setText(viewModel.sharePrice(String.valueOf(temp.getPrice())));

        holder.itemView.setOnClickListener(v -> {

            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SelectedItemFragment(temp.getId()))
                    .addToBackStack(null)
                    .commit();
        });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCatalog(List<Item> itemList){
        this.data = itemList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView text;
        TextView price;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);
            img = itemView.findViewById(R.id.img_temp);
            text = itemView.findViewById(R.id.text_temp);
            price = itemView.findViewById(R.id.price_temp);
        }
        public void loadImage(String url) {
            Glide.with(itemView.getContext()).load(url).into(img);
        }
    }
}
