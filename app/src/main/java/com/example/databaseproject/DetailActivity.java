package com.example.databaseproject;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.model.PurchaseDetailAdapter;
import com.example.databaseproject.model.SaleDetailAdapter;
import com.example.databaseproject.viewmodel.PurchaseListViewModel;
import com.example.databaseproject.viewmodel.SaleInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class DetailActivity extends AppCompatActivity {
    private TextView listId, memId;
    private RecyclerView recyclerView;
    private ConstraintLayout layout;
    private SaleInfoViewModel saleInfoViewModel;
    private PurchaseListViewModel purchaseListViewModel;
    private List<SaleInfo> saleInfoList = new ArrayList<>();
    private List<PurchaseInfo> purchaseInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_detail);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    void initView() {
        Intent intent = getIntent();
        String source = intent.getStringExtra("source");
        int id = intent.getIntExtra("id",0);
        saleInfoViewModel = ViewModelProviders.of(this).get(SaleInfoViewModel.class);
        purchaseListViewModel = ViewModelProviders.of(this).get(PurchaseListViewModel.class);
        listId = findViewById(R.id.detail_id);
        listId.setText(id + "");
        recyclerView = findViewById(R.id.detail_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        layout = findViewById(R.id.detail_mem);
        if(source.equals("sale")){
            layout.setVisibility(View.GONE);
            saleInfoViewModel.getAllres().observe(this, new Observer<List<SaleInfo>>() {
                @Override
                public void onChanged(List<SaleInfo> saleInfos) {
                    if(saleInfos != null) {
                        saleInfoList.clear();
                        saleInfoList.addAll(saleInfos);
                        List<SaleInfo> list = new ArrayList<>();
                        for(SaleInfo s : saleInfoList){
                            if(s.getSaleId() == id){
                                list.add(s);
                            }
                        }
                        SaleDetailAdapter adapter = new SaleDetailAdapter(list);
                        recyclerView.setAdapter(adapter);
                    }
                }
            });
        }else{
            layout.setVisibility(View.GONE);
            purchaseListViewModel.getPurchaseInfos().observe(this, new Observer<List<PurchaseInfo>>() {
                @Override
                public void onChanged(List<PurchaseInfo> purchaseInfos) {
                    if(purchaseInfos != null) {
                        purchaseInfoList.clear();
                        purchaseInfoList.addAll(purchaseInfos);
                        List<PurchaseInfo> list1 = new ArrayList<>();
                        for(PurchaseInfo s : purchaseInfoList){
                            if(s.getPurchaseId() == id){
                                list1.add(s);
                            }
                        }
                        PurchaseDetailAdapter adapter = new PurchaseDetailAdapter(list1);
                        recyclerView.setAdapter(adapter);
                    }
                }
            });

        }
    }
}
