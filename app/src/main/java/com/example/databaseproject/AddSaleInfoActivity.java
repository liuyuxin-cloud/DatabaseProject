package com.example.databaseproject;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.model.Info;
import com.example.databaseproject.model.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddSaleInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button add;
    private TextView purId, info;
    private List<Info> list = new ArrayList<>();
    private InfoAdapter adapter = new InfoAdapter(list);

    @Override
    protected void onCreate(Bundle savedInstance) {
        super.onCreate(savedInstance);
        setContentView(R.layout.activity_add_purchase);
        initView();
    }
    void initView() {
        add = findViewById(R.id.add_pur_bt);
        purId = findViewById(R.id.pur_id);
        info = findViewById(R.id.list_info_tv);
        info.setText("新建销售订单");
        //TODO:查询id
        // 数据库中id号在purid显示
        recyclerView = findViewById(R.id.add_pur_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add.setOnClickListener(v -> {
            showDialog();
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
            list.add(new Info(name, num));
            adapter.setmList(list);
            dialog.dismiss();
        });
    }
}
