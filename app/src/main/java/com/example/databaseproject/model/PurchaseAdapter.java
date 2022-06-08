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
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleList;

import java.util.ArrayList;
import java.util.List;

public class PurchaseAdapter extends RecyclerView.Adapter<PurchaseAdapter.PurchaseViewHolder> {

    private final LayoutInflater mInflater;
    private List<PurchaseList> mLists = new ArrayList<>();

    public PurchaseAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public PurchaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.sale_purchase_item, parent, false);
        return new PurchaseViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseViewHolder holder, int position) {
        if(mLists != null) {
            PurchaseList current = mLists.get(position);
            holder.listId.setText(current.getPurchaseId() + "");
            holder.listPrice.setText(current.getTotalPrice() + "");
            holder.listTime.setText(current.getPurchaseTime() + "");
        }
    }

    public void setmLists(List<PurchaseList> lists) {
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

    class PurchaseViewHolder extends RecyclerView.ViewHolder {
        private TextView listId, listPrice, listTime;
        private PurchaseViewHolder (View itemView) {
            super(itemView);
            listId = itemView.findViewById(R.id.list_id);
            listPrice = itemView.findViewById(R.id.total_price);
            listTime = itemView.findViewById(R.id.list_time);
        }
    }
}
