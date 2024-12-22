package com.example.myfavtvshow;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomeAdapter extends RecyclerView.Adapter<CustomeAdapter.MyViewHolder> {

    private ArrayList<DataModel> dataSet;
    private ArrayList<DataModel> filteredList;

    public CustomeAdapter(ArrayList<DataModel> dataSet) {
        this.dataSet = dataSet;
        this.filteredList = new ArrayList<>(dataSet);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewVersion;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textView);
            textViewVersion = itemView.findViewById(R.id.textView2);
            imageView = itemView.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public CustomeAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardrows, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        DataModel currentItem = filteredList.get(position);

        holder.textViewName.setText(currentItem.getName());
        holder.textViewVersion.setText(currentItem.getVersion());
        holder.imageView.setImageResource(currentItem.getImage());

        holder.itemView.setOnClickListener(v -> {

            Toast.makeText(v.getContext(), "נבחרה דמות: " + currentItem.getName(), Toast.LENGTH_SHORT).show();
        });
    }


    public int getItemCount() {

        return filteredList.size();
    }

    public void filter(String query) {
        filteredList.clear();
        if (query.isEmpty()) {
            filteredList.addAll(dataSet);
        }
        else {
            for (DataModel item : dataSet) {
                if (item.getName().toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }




}

