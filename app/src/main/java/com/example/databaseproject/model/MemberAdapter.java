package com.example.databaseproject.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.R;
import com.example.databaseproject.entities.Membership;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberHolder> {

    public MemberAdapter() {
    }

    public List<Membership> getmList() {
        return mList;
    }

    public void setmList(List<Membership> mList) {
        this.mList = mList;
    }

    private List<Membership> mList;

    @NonNull
    @Override
    public MemberAdapter.MemberHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_member,parent, false);
        return new MemberAdapter.MemberHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberHolder holder, int position) {
        if(mList != null) {
            holder.name.setText(mList.get(position).getMemberName() + "");
            holder.id.setText(mList.get(position).getMemberId() + "");
            holder.time.setText(mList.get(position).getRegisterTime());
            holder.total.setText(mList.get(position).getTotalConsumption() + "￥");
            holder.level.setText(mList.get(position).getLevel());
        }
    }

    @Override
    public int getItemCount() {
        if(mList != null) {
            return mList.size();
        }
        return 0;
    }

    class MemberHolder extends RecyclerView.ViewHolder{
        private TextView name, id, time, total, level;
        public MemberHolder(@NonNull View itemView) {
            super(itemView);
            level = itemView.findViewById(R.id.mem_level);
            name = itemView.findViewById(R.id.mem_name);
            id = itemView.findViewById(R.id.mem_id);
            time = itemView.findViewById(R.id.mem_time);
            total = itemView.findViewById(R.id.mem_total);
        }
    }
}
