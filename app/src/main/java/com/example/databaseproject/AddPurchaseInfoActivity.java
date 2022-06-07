package com.example.databaseproject;

import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.entities.BookInfo;
import com.example.databaseproject.entities.Depository;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.model.Info;
import com.example.databaseproject.model.InfoAdapter;
import com.example.databaseproject.viewmodel.BookInfoViewModel;
import com.example.databaseproject.viewmodel.DepositoryViewModel;
import com.example.databaseproject.viewmodel.PurchaseListViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddPurchaseInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button add;
    private TextView purId;
    private ImageView done;
    private List<Info> list = new ArrayList<>();
    private InfoAdapter adapter = new InfoAdapter(list);
    private PurchaseListViewModel viewModel;
    private BookInfoViewModel bookInfoViewModel;
    private DepositoryViewModel depositoryViewModel;
    private List<BookInfo> bookInfos = new ArrayList<>();
    private List<PurchaseList> purchaseLists;
    private List<Depository> depos = new ArrayList<>();
    private ConstraintLayout layout;

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_add_purchase);
        initView();
        getId();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN |
                            View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }
    }
    void initView() {
        layout = findViewById(R.id.mem);
        layout.setVisibility(View.GONE);
        viewModel = ViewModelProviders.of(this).get(PurchaseListViewModel.class);
        bookInfoViewModel = ViewModelProviders.of(this).get(BookInfoViewModel.class);
        depositoryViewModel = ViewModelProviders.of(this).get(DepositoryViewModel.class);
        bookInfoViewModel.getBookInfos().observe(this, new Observer<List<BookInfo>>() {
            @Override
            public void onChanged(List<BookInfo> bookInfs) {
                bookInfos.clear();
                bookInfos.addAll(bookInfs);
            }
        });
        depositoryViewModel.getAllres().observe(this, new Observer<List<Depository>>() {
            @Override
            public void onChanged(List<Depository> depositories) {
                depos.clear();
                depos.addAll(depositories);
            }
        });
        add = findViewById(R.id.add_pur_bt);
        purId = findViewById(R.id.pur_id);
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
                    for(BookInfo f : bookInfos) {
                        if(i.getName().equals(f.getBookName())){
                            price = f.getBookPrice();
                            break;
                        }
                    }
                    for(Depository d : depos) {
                        if (i.getName().equals(d.getBookName())) {
                            num = i.getNum();
                            break;
                        }
                    }
                    total += price * num;
                }
                Toast.makeText(this, "总价为" + total + " 元", Toast.LENGTH_SHORT).show();
            }

            //todo:更新list仓库中每项数量。
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.CUPCAKE)
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

        yes.setOnClickListener(v -> {
            String name = nameEt.getText().toString();
            int num = Integer.parseInt(numEt.getText().toString());
            list.add(new Info(name, num));
            adapter.setmList(list);
            dialog.dismiss();
        });
    }

    public void getId() {
        int id;
        purchaseLists = viewModel.getAllres().getValue();
        if(purchaseLists == null) {
            id = 1;
        }else {
            int count = 0;
            for(PurchaseList i : purchaseLists) {
                count++;
            }
            id = count+1;
        }
        purId.setText(id + "");
    }
}
