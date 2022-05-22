package com.example.databaseproject;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.repository.BookInfoRepository;

public class BookInfoActivity extends AppCompatActivity {
    private TextView id, name, press, auth, price, type;
    private LiveData<BookInfo> bookInfo;
    private BookInfoRepository repository;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_bookinfo);
        String bookName = getIntent().getStringExtra("name");
        repository = new BookInfoRepository(getApplication(), bookName);
        bookInfo = repository.getBookInfo();
        initView();
    }

    void initView() {

        id = findViewById(R.id.info_id);
        id.setText(bookInfo.getValue().getBookId() + "");
        name = findViewById(R.id.info_name);
        name.setText(bookInfo.getValue().getBookName());
        press = findViewById(R.id.info_press);
        press.setText(bookInfo.getValue().getBookPress());
        price = findViewById(R.id.info_price);
        price.setText(bookInfo.getValue().getBookPrice() + "");
        type = findViewById(R.id.info_type);
        type.setText(bookInfo.getValue().getBookType());
        auth = findViewById(R.id.info_auth);
        auth.setText(bookInfo.getValue().getBookAuth());
    }
}
