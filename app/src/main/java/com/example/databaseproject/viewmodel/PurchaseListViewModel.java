package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.repository.DepoRepository;
import com.example.databaseproject.repository.PurchaseListRepository;

import java.util.List;

public class PurchaseListViewModel extends AndroidViewModel {

    private PurchaseListRepository repository;
    public LiveData<List<PurchaseList>> getAllres() {
        return allres;
    }

    private LiveData<List<PurchaseList>> allres;

    public PurchaseListViewModel(@NonNull Application application) {
        super(application);
        repository = new PurchaseListRepository(application);
        allres = repository.getPurchaseList();
    }
    public void insert(PurchaseList purchaseList) {
        repository.insert(purchaseList);
    }
}
