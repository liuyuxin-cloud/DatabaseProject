package com.example.databaseproject;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.databaseproject.model.Info;
import com.example.databaseproject.model.InfoAdapter;

import java.util.ArrayList;
import java.util.List;

public class AddPurchaseInfoActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private Button add;
    private EditText purId;
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

        yes.setOnClickListener(v -> {
            String name = nameEt.getText().toString();
            int num = Integer.parseInt(numEt.getText().toString());
            list.add(new Info(name, num));
            adapter.setmList(list);
            dialog.dismiss();
        });
    }

    void show() {
        View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.dialog_purchase, null, false);
        final PopupWindow popupWindow = new PopupWindow(v, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        //参数为1.View 2.宽度 3.高度
        popupWindow.setOutsideTouchable(true);//设置点击外部区域可以取消popupWindow
        popupWindow.setFocusable(true);
        backgroundAlpha(0.5f);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                backgroundAlpha(1.0f);
            }
        });


        EditText nameEt = v.findViewById(R.id.name_et);
        EditText numEt = v.findViewById(R.id.num_et);
        Button yes = v.findViewById(R.id.yes_bt);

        String name = nameEt.getText().toString();
       // int num = Integer.parseInt(numEt.getText().toString());

        yes.setOnClickListener(view -> {
           // list.add(new Info(name, num));
            adapter.setmList(list);
            popupWindow.dismiss();
        });

        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }
    public void backgroundAlpha(float v) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha = v; //0.0-1.0
        getWindow().setAttributes(lp);
    }
}
