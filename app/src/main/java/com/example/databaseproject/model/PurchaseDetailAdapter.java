package com.example.databaseproject.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.R;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.SaleInfo;

import java.util.ArrayList;
import java.util.List;

public class PurchaseDetailAdapter extends RecyclerView.Adapter<PurchaseDetailAdapter.DetailHolder>{
    public PurchaseDetailAdapter(List<PurchaseInfo> mList) {
        this.mList = mList;
    }

    public List<PurchaseInfo> getmList() {
        return mList;
    }

    public void setmList(List<PurchaseInfo> mList) {
        this.mList = mList;
    }

    private List<PurchaseInfo> mList = new ArrayList<>();

    @NonNull
    @Override
    public PurchaseDetailAdapter.DetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.sale_purchase_item,parent, false);
        return new PurchaseDetailAdapter.DetailHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PurchaseDetailAdapter.DetailHolder holder, int position) {
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
