package com.example.jsonandroid;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    List<ItemModel> items;

    public ItemAdapter(List<ItemModel> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        ItemModel item = items.get(position);
        holder.textName.setText(item.getName());
        holder.textEmail.setText(item.getEmail());
        Picasso.get().load("https://lebavui.github.io/" + item.getThumbnail()).transform(new CropCircleTransformation()).into(holder.imageThumbnail);
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("name", item.getName());
            intent.putExtra("username", item.getUsername());
            intent.putExtra("email", item.getEmail());
            intent.putExtra("address", item.getAddress());
            intent.putExtra("photo", item.getPhoto());
            intent.putExtra("phone", item.getPhone());
            intent.putExtra("company", item.getCompany());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView textName, textEmail;
        ImageView imageThumbnail;

        public ItemViewHolder(View itemView){
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textEmail = itemView.findViewById(R.id.text_email);
            imageThumbnail = itemView.findViewById(R.id.thumbnail);
        }
    }
}
