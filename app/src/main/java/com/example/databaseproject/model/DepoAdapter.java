package com.example.databaseproject.model;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.BookInfoActivity;
import com.example.databaseproject.R;
import com.example.databaseproject.entities.Depository;

import java.util.List;

public class DepoAdapter extends RecyclerView.Adapter<DepoAdapter.DepoViewHolder> {

    private final LayoutInflater mInflater;
    private List<Depository> mDepos;
    private Context con;

    public DepoAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
        con = context;
    }
    @NonNull
    @Override
    public DepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = mInflater.inflate(R.layout.depo_item, parent, false);
        return new DepoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DepoViewHolder holder, int position) {
        if(mDepos != null) {
            Depository current = mDepos.get(position);
            holder.layout.setOnClickListener(v -> {
                Intent intent = new Intent(con, BookInfoActivity.class);
                intent.putExtra("name", current.getBookName());
                con.startActivity(intent);
            });
            holder.bookId.setText(current.getBookId() + "");
            holder.bookName.setText(current.getBookName());
            holder.bookNum.setText(current.getBookNum() + "本");
        }
    }

    public void setmDepos(List<Depository> depos) {
        mDepos = depos;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if(mDepos != null) {
            return mDepos.size();
        }
        return 0;
    }

    class DepoViewHolder extends RecyclerView.ViewHolder {
        private TextView bookId, bookName, bookNum;
        private ConstraintLayout layout;
        private DepoViewHolder (View itemView) {
            super(itemView);
            layout = itemView.findViewById(R.id.item_layout);
            bookId = itemView.findViewById(R.id.repo_id);
            bookName = itemView.findViewById(R.id.repo_book_name);
            bookNum = itemView.findViewById(R.id.repo_book_num);
        }
    }
}
