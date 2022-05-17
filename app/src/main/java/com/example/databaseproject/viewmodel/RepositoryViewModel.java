package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.databaseproject.entities.Repository;
import com.example.databaseproject.repository.ReRepository;

import java.util.List;

public class RepositoryViewModel extends AndroidViewModel {

    private ReRepository repository;

    public LiveData<List<Repository>> getAllres() {
        return allres;
    }

    private LiveData<List<Repository>> allres;
    public RepositoryViewModel(@NonNull Application application) {
        super(application);
        repository = new ReRepository(application);
        allres = repository.getRepositories();
    }

    public void insert(Repository repo) {
        repository.insert(repo);
    }
}
