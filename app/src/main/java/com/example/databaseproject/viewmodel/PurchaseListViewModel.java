package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.repository.DepoRepository;
import com.example.databaseproject.repository.PurchaseInfoRepository;
import com.example.databaseproject.repository.PurchaseListRepository;

import java.util.List;

public class PurchaseListViewModel extends AndroidViewModel {

    private PurchaseListRepository repository;
    private PurchaseInfoRepository purchaseInfoRepository;
    public LiveData<List<PurchaseList>> getAllres() {
        return allres;
    }

    private LiveData<List<PurchaseList>> allres;

    public PurchaseListViewModel(@NonNull Application application) {
        super(application);
        repository = new PurchaseListRepository(application);
        purchaseInfoRepository = new PurchaseInfoRepository(application);
        allres = repository.getPurchaseList();
    }
    public void insertList(PurchaseList purchaseList) {
        repository.insert(purchaseList);
    }

    public void insertInfo(PurchaseInfo purchaseInfo) {
        purchaseInfoRepository.insert(purchaseInfo);
    }
}
