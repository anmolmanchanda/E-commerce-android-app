package com.example.finalproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Product> mList;

    public ListAdapter(List<Product> list, Context context){
        super();
        mList = list;
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        public TextView tvProductID;
        public TextView tvProductName;
        public TextView tvProductDescription;
        public TextView tvProductPrice;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvProductID = (TextView) itemView.findViewById(R.id.tvProductID);
            tvProductName = (TextView) itemView.findViewById(R.id.tvProductName);
            tvProductDescription = (TextView) itemView.findViewById(R.id.tvProductDescription);
            tvProductPrice = (TextView) itemView.findViewById(R.id.tvProductPrice);
        }
    }



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.record_layout,parent,
                false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Product itemAdapter = mList.get(position);
        ((ViewHolder) holder).tvProductID.setText(itemAdapter.getProductID()+"");
        ((ViewHolder) holder).tvProductName.setText(itemAdapter.getProduct_name());
        ((ViewHolder) holder).tvProductDescription.setText(itemAdapter.getDescription());
        ((ViewHolder) holder).tvProductPrice.setText((itemAdapter.getPrice()+""));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
