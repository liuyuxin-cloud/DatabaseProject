package com.example.databaseproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleList;
import com.example.databaseproject.model.PurchaseAdapter;
import com.example.databaseproject.model.SaleAdapter;
import com.example.databaseproject.viewmodel.PurchaseListViewModel;
import com.example.databaseproject.viewmodel.SaleListViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class SaleFragment extends Fragment {
    private RecyclerView recyclerView;
    private SaleListViewModel viewModel;
    private FloatingActionButton button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_purchase,null);
        button = view.findViewById(R.id.purchase_add);
        recyclerView = view.findViewById(R.id.pur_rv);
        final SaleAdapter adapter = new SaleAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        viewModel = ViewModelProviders.of(this).get(SaleListViewModel.class);
        viewModel.getAllres().observe(getViewLifecycleOwner(), new Observer<List<SaleList>>() {
            @Override
            public void onChanged(List<SaleList> depositories) {
                adapter.setmLists(depositories);
            }
        });
        button.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), AddSaleInfoActivity.class);
            startActivity(intent);
        });
        return view;
    }
}
