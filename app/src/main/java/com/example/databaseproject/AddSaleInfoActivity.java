package com.example.databaseproject;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.SaleList;
import com.example.databaseproject.model.Info;
import com.example.databaseproject.model.InfoAdapter;
import com.example.databaseproject.viewmodel.BookInfoViewModel;
import com.example.databaseproject.viewmodel.DepositoryViewModel;
import com.example.databaseproject.viewmodel.SaleListViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddSaleInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button add;
    private TextView saleId, info;
    private ImageView done;
    private List<Info> list = new ArrayList<>();
    private InfoAdapter adapter = new InfoAdapter(list);
    private SaleListViewModel viewModel;
    private List<SaleList> saleList;
    private List<Depository> depos = new ArrayList<>();
    private List<BookInfo> books = new ArrayList<>();
    private DepositoryViewModel depositoryViewModel;
    private BookInfoViewModel bookInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_add_purchase);
        viewModel = ViewModelProviders.of(this).get(SaleListViewModel.class);
        depositoryViewModel = ViewModelProviders.of(this).get(DepositoryViewModel.class);
        depositoryViewModel.getAllres().observe(this, new Observer<List<Depository>>() {
            @Override
            public void onChanged(List<Depository> depositories) {
                depos.clear();
                depos.addAll(depositories);
            }
        });
        bookInfoViewModel = ViewModelProviders.of(this).get(BookInfoViewModel.class);
        bookInfoViewModel.getBookInfos().observe(this, new Observer<List<BookInfo>>() {
            @Override
            public void onChanged(List<BookInfo> bookInfos) {
                books.clear();
                books.addAll(bookInfos);
            }
        });
        initView();
        getId();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

    }
    void initView() {
        add = findViewById(R.id.add_pur_bt);
        saleId = findViewById(R.id.pur_id);
        info = findViewById(R.id.list_info_tv);
        info.setText("新建销售订单");
        recyclerView = findViewById(R.id.add_pur_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add.setOnClickListener(v -> {
            showDialog();
        });
        done = findViewById(R.id.done);
        done.setOnClickListener(v -> {
            if(list != null) {
                double total = 0.0;
                for(Info i : list) {
                    double price = 0.0;
                    int num = 0;
                    for(BookInfo f : books) {
                        if(i.getName().equals(f.getBookName())){
                            price = f.getBookPrice();
                        }
                    }
                    for(Depository d : depos) {
                        if(i.getName().equals(d.getBookName())) {
                            num = i.getNum();
                            if(i.getNum() > d.getBookNum()) {
                                Toast.makeText(this, i.getName() + "数量不够，将所有库存" + num + "本卖出",Toast.LENGTH_SHORT).show();
                                num = d.getBookNum();
                            }
                        }
                    }
                    total += price * num;
                }
                Toast.makeText(this, "总价为" + total + " 元", Toast.LENGTH_SHORT).show();
            }

            //todo:更新list仓库中每项数量。
        });
    }

    void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        builder.setView(View.inflate(this, R.layout.dialog_purchase, null));
        dialog.show();
        dialog.getWindow().clearFlags(
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                        | WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
        Window window = dialog.getWindow();
        window.setContentView(R.layout.dialog_purchase);
        EditText nameEt = window.findViewById(R.id.name_et);
        EditText numEt = window.findViewById(R.id.num_et);
        Button yes = window.findViewById(R.id.yes_bt);
        TextView tv = window.findViewById(R.id.dialog_tv);
        tv.setText("请输入销售数量:");

        yes.setOnClickListener(v -> {
            String name = nameEt.getText().toString();
            int num = Integer.parseInt(numEt.getText().toString());
            //TODO:根据名字查询库中数量进行比较，最多将库中所有书卖出
            list.add(new Info(name, num));
            adapter.setmList(list);
            dialog.dismiss();
        });
    }

    public void getId() {
        int id;
        saleList = viewModel.getAllres().getValue();
        if(saleList == null) {
            id = 1;
        }else {
            int count = 0;
            for(SaleList i : saleList) {
                count++;
            }
            id = count+1;
        }
        saleId.setText(id + "");
    }
}
