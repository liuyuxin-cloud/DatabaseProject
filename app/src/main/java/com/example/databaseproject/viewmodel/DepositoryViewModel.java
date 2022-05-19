package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.repository.DepoRepository;

import java.util.List;

public class DepositoryViewModel extends AndroidViewModel {

    private DepoRepository repository;

    public LiveData<List<Depository>> getAllres() {
        return allres;
    }

    private LiveData<List<Depository>> allres;

    public DepositoryViewModel(@NonNull Application application) {
        super(application);
        repository = new DepoRepository(application);
        allres = repository.getRepositories();
    }

    public void insert(Depository repo) {
        repository.insert(repo);
    }
}
