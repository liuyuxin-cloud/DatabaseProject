package com.example.databaseproject;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.model.DepoAdapter;
import com.example.databaseproject.viewmodel.DepositoryViewModel;

import java.util.List;

public class DepositoryFragment extends Fragment {
    private RecyclerView recyclerView;
    private DepositoryViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_depository,null);
        recyclerView = view.findViewById(R.id.depo_rv);
        final DepoAdapter adapter = new DepoAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = ViewModelProviders.of(this).get(DepositoryViewModel.class);
        viewModel.getAllres().observe(getViewLifecycleOwner(), new Observer<List<Depository>>() {
            @Override
            public void onChanged(List<Depository> depositories) {
                adapter.setmDepos(depositories);
            }
        });
        return view;
    }
}
