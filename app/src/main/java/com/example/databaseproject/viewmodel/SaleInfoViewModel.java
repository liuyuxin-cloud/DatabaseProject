package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.repository.SaleInfoRepository;

import java.util.List;

public class SaleInfoViewModel extends AndroidViewModel {
    private SaleInfoRepository repository;
    public LiveData<List<SaleInfo>> getAllres() {
        return allres;
    }

    private LiveData<List<SaleInfo>> allres;

    public SaleInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new SaleInfoRepository(application);
        allres = repository.getSaleInfo();
    }

    public void insert(SaleInfo saleInfo) {
        repository.insert(saleInfo);
    }
}

