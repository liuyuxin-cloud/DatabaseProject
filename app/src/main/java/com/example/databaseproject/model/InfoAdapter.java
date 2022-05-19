package com.example.databaseproject.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.R;
import java.util.List;

public class InfoAdapter extends RecyclerView.Adapter<InfoAdapter.InfoHolder> {
    public InfoAdapter(List<Info> mList) {
        this.mList = mList;
    }

    public List<Info> getmList() {
        return mList;
    }

    public void setmList(List<Info> mList) {
        this.mList = mList;
    }

    private List<Info> mList;

    @NonNull
    @Override
    public InfoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_purchase_info,parent, false);
        return new InfoHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull InfoHolder holder, int position) {
        if(mList != null) {
            holder.infoNum.setText(mList.get(position).num + "");
            holder.infoName.setText(mList.get(position).name);
        }
    }

    @Override
    public int getItemCount() {
        if(mList != null) {
            return mList.size();
        }
        return 0;
    }

    class InfoHolder extends RecyclerView.ViewHolder{
        private TextView infoName, infoNum;
        public InfoHolder(@NonNull View itemView) {
            super(itemView);
            infoName = itemView.findViewById(R.id.pur_book_name_tv);
            infoNum = itemView.findViewById(R.id.pur_num_tv);
        }
    }
}
