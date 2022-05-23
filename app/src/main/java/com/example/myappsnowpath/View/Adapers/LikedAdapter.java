package com.example.myappsnowpath.View.Adapers;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.myappsnowpath.R;
import com.example.myappsnowpath.Model.Item;
import com.example.myappsnowpath.View.Fragments.LikedFragment;
import com.example.myappsnowpath.View.Fragments.SelectedItemFragment;
import com.example.myappsnowpath.ViewModel.LikedViewModel;

import java.util.Objects;


public class LikedAdapter extends RecyclerView.Adapter<LikedAdapter.ViewHolder>
{
    FragmentActivity fa;
    LikedViewModel viewModel;

    public LikedAdapter(FragmentActivity fragmentActivity) {
        this.fa = fragmentActivity;
        viewModel = new ViewModelProvider(fa).get(LikedViewModel.class);
    }

    @NonNull
    @Override
    public LikedAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;

        view = inflater.inflate(R.layout.temp_liked, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final LikedAdapter.ViewHolder holder, int position)
    {
        final Item temp = Objects.requireNonNull(viewModel.getLikedData().getValue()).get(position);
        holder.text.setText(temp.getName());
        holder.loadImage(temp.getImage());
        holder.price.setText(String.valueOf(temp.getPrice()));

        holder.itemView.setOnClickListener(view -> {
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new SelectedItemFragment(temp.getId()))
                    .addToBackStack(null)
                    .commit();
        });

        holder.delete.setOnClickListener(view -> {
            viewModel.deleteFromLiked(temp);
            Toast.makeText(view.getContext(), "Удалено из избранного", Toast.LENGTH_SHORT).show();
            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            activity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, new LikedFragment())
                    .commit();
        });
    }

    @Override
    public int getItemCount()
    {
            return Objects.requireNonNull(viewModel.getLikedData().getValue()).size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView img;
        TextView text;
        TextView price;
        ImageView delete;

        public ViewHolder (@NonNull View itemView)
        {
            super(itemView);
            img = itemView.findViewById(R.id.img_temp_liked);
            text = itemView.findViewById(R.id.text_temp_liked);
            price = itemView.findViewById(R.id.price_temp_liked);
            delete = itemView.findViewById(R.id.icon_temp_liked_delete_item);
        }
        public void loadImage(String url) {
            Glide.with(itemView.getContext()).load(url).into(img);
        }
    }
}