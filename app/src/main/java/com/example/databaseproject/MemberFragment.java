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

import java.util.List;

public class MemberFragment extends Fragment {
    private RecyclerView recyclerView;
    private MemberViewModel viewModel;
    private FloatingActionButton button;
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
            public void onChanged(List<Membership> mems) {
                adapter.setmList(mems);
            }
        });
        button.setOnClickListener(v->{

        });
        return view;
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(getContext(), R.layout.dialog_purchase, null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_purchase);
        EditText nameEt = window.findViewById(R.id.name_et);
        EditText numEt = window.findViewById(R.id.num_et);
        Button yes = window.findViewById(R.id.yes_bt);
        TextView tv = window.findViewById(R.id.dialog_tv);
        tv.setText("请输入销售数量:");

        yes.setOnClickListener(v -> {
            String name = nameEt.getText().toString();
            int num = Integer.parseInt(numEt.getText().toString());

            dialog.dismiss();
        });
    }
}
