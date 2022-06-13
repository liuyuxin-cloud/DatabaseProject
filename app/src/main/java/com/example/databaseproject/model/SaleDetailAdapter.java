package com.example.databaseproject.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.R;
import com.example.databaseproject.entities.SaleInfo;

import java.util.ArrayList;
import java.util.List;

public class SaleDetailAdapter extends RecyclerView.Adapter<SaleDetailAdapter.DetailHolder> {
    public SaleDetailAdapter(List<SaleInfo> mList) {
        this.mList = mList;
    }

    public List<SaleInfo> getmList() {
        return mList;
    }

    public void setmList(List<SaleInfo> mList) {
        this.mList = mList;
    }

    private List<SaleInfo> mList = new ArrayList<>();

    @NonNull
    @Override
    public SaleDetailAdapter.DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_purchase_item,parent, false);
        return new DetailHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DetailHolder holder, int position) {
        if(mList != null) {
            holder.infoNum.setText(mList.get(position).getBookNum() + "本");
            holder.infoName.setText(mList.get(position).getBookId() + "");
            holder.infoPrice.setText(mList.get(position).getBookPrice() + "￥");
        }
    }

    @Override
    public int getItemCount() {
        if(mList != null) {
            return mList.size();
        }
        return 0;
    }

    class DetailHolder extends RecyclerView.ViewHolder{
        private TextView infoName, infoNum, infoPrice;
        public DetailHolder(@NonNull View itemView) {
            super(itemView);
            infoName = itemView.findViewById(R.id.list_id);
            infoNum = itemView.findViewById(R.id.list_time);
            infoPrice = itemView.findViewById(R.id.total_price);
        }
    }
}
