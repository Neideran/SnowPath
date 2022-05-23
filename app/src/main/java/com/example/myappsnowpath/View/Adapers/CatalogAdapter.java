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
import com.example.myappsnowpath.Model.Catalog;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.View.Fragments.CatalogFragment;
import com.example.myappsnowpath.View.Fragments.CategoryFragment;

import java.util.List;


public class CatalogAdapter extends RecyclerView.Adapter<CatalogAdapter.ViewHolder>
{
    List<Catalog> data;

    public CatalogAdapter() { }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view;
        view = inflater.inflate(R.layout.temp_catalog, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CatalogAdapter.ViewHolder holder, int position)
    {

        final Catalog temp = data.get(position);
        holder.text.setText(temp.getCategoryName());
        holder.loadImage(temp.getImage());

        holder.itemView.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
               if (temp.getParent().equals("null")) {
                   activity.getSupportFragmentManager().beginTransaction()
                           .replace(R.id.fragment_container, new CatalogFragment(temp.getCategoryName()))
                           .addToBackStack(null)
                           .commit();
                }
                else {
                   activity.getSupportFragmentManager().beginTransaction()
                           .replace(R.id.fragment_container, new CategoryFragment(temp.getCategoryName(),false))
                           .addToBackStack(null)
                           .commit();
                }
            });
    }

    @Override
    public int getItemCount()
    {
        return data.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setData(List<Catalog> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img_temp);
            text = itemView.findViewById(R.id.text_temp);
        }
        public void loadImage(String url) {
            Glide.with(itemView.getContext()).load(url).into(img);
        }
    }
}