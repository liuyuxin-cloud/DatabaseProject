package com.example.databaseproject.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.repository.BookInfoRepository;

import java.util.List;

public class BookInfoViewModel extends AndroidViewModel {
    BookInfoRepository repository;

    LiveData<BookInfo> bookInfo;
    LiveData<List<BookInfo>> bookInfos;

    public BookInfoViewModel(@NonNull Application application) {
        super(application);
        repository = new BookInfoRepository(application);
        bookInfos = repository.getBookInfos();
    }
    public void insert(BookInfo repo) {
        repository.insert(repo);
    }


    public LiveData<List<BookInfo>> getBookInfos() {
        return bookInfos;
    }
    public LiveData<BookInfo> getBookInfo(String name) {
        return repository.getBookInfo(name);
    }
}
