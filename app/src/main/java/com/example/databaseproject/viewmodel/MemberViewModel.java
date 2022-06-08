package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.repository.MemberRepository;
import com.example.databaseproject.repository.PurchaseListRepository;

import java.util.List;

public class MemberViewModel extends AndroidViewModel {

    private MemberRepository repository;
    public LiveData<List<Membership>> getAllres() {
        return allres;
    }

    private LiveData<List<Membership>> allres;

    public MemberViewModel(@NonNull Application application) {
        super(application);
        repository = new MemberRepository(application);
        allres = repository.getMemberships();
    }

    public void insert(Membership membership) {
        repository.insert(membership);
    }

}
