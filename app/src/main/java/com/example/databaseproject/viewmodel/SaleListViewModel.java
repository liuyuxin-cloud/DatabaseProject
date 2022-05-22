package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleList;
import com.example.databaseproject.repository.PurchaseListRepository;
import com.example.databaseproject.repository.SaleListRepository;

import java.util.List;

public class SaleListViewModel extends AndroidViewModel {
    private SaleListRepository repository;
    public LiveData<List<SaleList>> getAllres() {
        return allres;
    }

    private LiveData<List<SaleList>> allres;

    public SaleListViewModel(@NonNull Application application) {
        super(application);
        repository = new SaleListRepository(application);
        allres = repository.getSaleList();
    }

    public void insert(SaleList saleList) {
        repository.insert(saleList);
    }
}
