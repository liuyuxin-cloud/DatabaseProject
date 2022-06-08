package com.example.databaseproject;

import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.viewmodel.BookInfoViewModel;
import com.example.databaseproject.viewmodel.DepositoryViewModel;

public class AddDepoActivity extends AppCompatActivity {
    private EditText name, id, auth, type, press, inprice, outprice;
    private ImageView done;
    private DepositoryViewModel depositoryViewModel;
    private BookInfoViewModel bookInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_add_depo);
        initView();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }

    void initView(){
        depositoryViewModel = ViewModelProviders.of(this).get(DepositoryViewModel.class);
        bookInfoViewModel = ViewModelProviders.of(this).get(BookInfoViewModel.class);
        name = findViewById(R.id.add_name);
        id = findViewById(R.id.add_id);
        auth = findViewById(R.id.add_auth);
        type = findViewById(R.id.add_type);
        press = findViewById(R.id.add_press);
        inprice = findViewById(R.id.add_pprice);
        outprice = findViewById(R.id.add_sprice);
        done = findViewById(R.id.done_depo);
        done.setOnClickListener(v -> {
            String sname = name.getText().toString();
            int sid = Integer.parseInt(id.getText().toString());
            String sauth = auth.getText().toString();
            String stype = type.getText().toString();
            String spress = press.getText().toString();
            double sinprice = Double.parseDouble(inprice.getText().toString());
            double soutprice = Double.parseDouble(outprice.getText().toString());
            depositoryViewModel.insert(new Depository(sid, sname, 0));
            bookInfoViewModel.insert(new BookInfo(sid, sname, stype, sinprice, soutprice, spress, sauth));
            finish();
        });
    }
}
