package com.example.databaseproject.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.R;
import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.SaleList;

import java.util.List;

public class SaleAdapter extends RecyclerView.Adapter<SaleAdapter.SaleViewHolder> {

    private final LayoutInflater mInflater;
    private List<SaleList> mLists;

    public SaleAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public SaleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.depo_item, parent, false);
        return new SaleViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull SaleViewHolder holder, int position) {
        if(mLists != null) {
            SaleList current = mLists.get(position);
            holder.listId.setText(current.getSaleId() + "");
            holder.listPrice.setText(current.getTotalPrice() + "");
            holder.listTime.setText(current.getSaleTime() + "");
        }
    }

    public void setmLists(List<SaleList> lists) {
        mLists = lists;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mLists != null) {
            return mLists.size();
        }
        return 0;
    }

    class SaleViewHolder extends RecyclerView.ViewHolder {
        private TextView listId, listPrice, listTime;
        private SaleViewHolder (View itemView) {
            super(itemView);
            listId = itemView.findViewById(R.id.list_id);
            listPrice = itemView.findViewById(R.id.total_price);
            listTime = itemView.findViewById(R.id.list_time);
        }
    }
}
