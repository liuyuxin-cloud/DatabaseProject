package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.repository.BookInfoRepository;

public class BookInfoViewModel extends AndroidViewModel {
    BookInfoRepository repository;

    LiveData<BookInfo> bookInfo;

    public BookInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new BookInfoRepository(application);
    }
    public LiveData<BookInfo> getBookInfo(String name) {
        return repository.getBookInfo(name);
    }
}
