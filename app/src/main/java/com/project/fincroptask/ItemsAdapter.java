package com.project.fincroptask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private Context mContext;
    private ArrayList<ItemModel> itemList = new ArrayList<>();



    public ItemsAdapter(MainActivity mainActivity, ArrayList<ItemModel> itemModels) {
        this.mContext = mainActivity;
        this.itemList = itemModels;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ItemModel modal = itemList.get(position);

        holder.email_tv.setText(modal.getEmail());
        holder.number_tv.setText(modal.getNumber());

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView email_tv,number_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            email_tv =itemView.findViewById(R.id.email_tv);
            number_tv =itemView.findViewById(R.id.mobile_number_tv);

        }
    }
}
