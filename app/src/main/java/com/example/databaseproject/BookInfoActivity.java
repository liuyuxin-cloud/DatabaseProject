package com.example.databaseproject;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
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
        repository = new BookInfoRepository(getApplication());
        bookInfo = repository.getBookInfo(bookName);
        initView();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    void initView() {
        id = findViewById(R.id.info_id);
        name = findViewById(R.id.info_name);
        press = findViewById(R.id.info_press);
        price = findViewById(R.id.info_price);
        type = findViewById(R.id.info_type);
        auth = findViewById(R.id.info_auth);
        if(bookInfo.getValue() != null) {
            auth.setText(bookInfo.getValue().getBookAuth());
            id.setText(bookInfo.getValue().getBookId() + "");
            name.setText(bookInfo.getValue().getBookName());
            press.setText(bookInfo.getValue().getBookPress());
            price.setText(bookInfo.getValue().getBookPrice() + "");
            type.setText(bookInfo.getValue().getBookType());
        }
    }
}
