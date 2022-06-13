package com.example.databaseproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.model.DepoAdapter;
import com.example.databaseproject.model.Info;
import com.example.databaseproject.model.MemberAdapter;
import com.example.databaseproject.viewmodel.DepositoryViewModel;
import com.example.databaseproject.viewmodel.MemberViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberFragment extends Fragment {
    private RecyclerView recyclerView;
    private MemberViewModel viewModel;
    private FloatingActionButton button;
    private List<Membership> mems = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_member, null);
        recyclerView = view.findViewById(R.id.mem_rv);
        button = view.findViewById(R.id.add_mem);
        final MemberAdapter adapter = new MemberAdapter();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = ViewModelProviders.of(this).get(MemberViewModel.class);
        viewModel.getAllres().observe(getViewLifecycleOwner(), new Observer<List<Membership>>() {
            @Override
            public void onChanged(List<Membership> mem) {
                if(mem != null){
                    mems.clear();
                    mems.addAll(mem);
                    adapter.setmList(mem);
                }
            }
        });
        button.setOnClickListener(v->{
            showDialog();
        });
        return view;
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getContext(), R.layout.dialog_mem, null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_mem);
        TextView idTv = window.findViewById(R.id.dialog_mem_id);
        EditText nameEt = window.findViewById(R.id.dialog_mem_name);
        Button yes = window.findViewById(R.id.mem_yes_bt);
        int id = 0;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String d = formatter.format(date);
        if(mems != null) {
            id = mems.size() + 1;
        }else {
            id = 1;
        }

        int finalId = id;
        idTv.setText(id + "");
        yes.setOnClickListener(v -> {
            String name = nameEt.getText().toString();
            Membership membership = new Membership(finalId, name, d, 0.0, "青铜");
            viewModel.insert(membership);
            dialog.dismiss();
        });
    }
}
