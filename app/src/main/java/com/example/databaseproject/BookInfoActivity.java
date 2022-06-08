package com.example.databaseproject;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.repository.BookInfoRepository;
import com.example.databaseproject.viewmodel.BookInfoViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookInfoActivity extends AppCompatActivity {
    private TextView id, name, press, auth, price, type;
    private LiveData<BookInfo> bookInfos;
    private BookInfo bookInfo;
    private BookInfoViewModel viewModel;
    private List<BookInfo> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_bookinfo);
        String bookName = getIntent().getStringExtra("name");
        viewModel = ViewModelProviders.of(this).get(BookInfoViewModel.class);
        viewModel.getBookInfos().observe(this, new Observer<List<BookInfo>>() {
            @Override
            public void onChanged(List<BookInfo> bookInfos) {
                list.clear();
                list.addAll(bookInfos);
                if(list != null) {
                    for(BookInfo i : list) {
                        if(i.getBookName().equals(bookName)){
                            bookInfo = i;
                        }
                    }
                }
                initView();
            }
        });

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
        if(bookInfo != null) {
            auth.setText(bookInfo.getBookAuth());
            id.setText(bookInfo.getBookId() + "");
            name.setText(bookInfo.getBookName());
            press.setText(bookInfo.getBookPress());
            price.setText(bookInfo.getBookOutPrice() + "");
            type.setText(bookInfo.getBookType());
        }
    }
}
