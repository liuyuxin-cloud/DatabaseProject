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
import com.example.databaseproject.entities.Membership;
import com.example.databaseproject.entities.PurchaseInfo;
import com.example.databaseproject.entities.PurchaseList;
import com.example.databaseproject.entities.SaleInfo;
import com.example.databaseproject.entities.SaleList;
import com.example.databaseproject.model.Info;
import com.example.databaseproject.model.InfoAdapter;
import com.example.databaseproject.viewmodel.BookInfoViewModel;
import com.example.databaseproject.viewmodel.DepositoryViewModel;
import com.example.databaseproject.viewmodel.MemberViewModel;
import com.example.databaseproject.viewmodel.SaleInfoViewModel;
import com.example.databaseproject.viewmodel.SaleListViewModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddSaleInfoActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Button add;
    private TextView saleId, info, vipId;
    private ImageView done;
    private List<Info> list = new ArrayList<>();
    private InfoAdapter adapter = new InfoAdapter(list);
    private SaleListViewModel viewModel;
    private List<SaleList> saleList = new ArrayList<>();
    private List<Depository> depos = new ArrayList<>();
    private List<BookInfo> books = new ArrayList<>();
    private List<Membership> mems = new ArrayList<>();
    private DepositoryViewModel depositoryViewModel;
    private BookInfoViewModel bookInfoViewModel;
    private SaleListViewModel saleListViewModel;
    private SaleInfoViewModel saleInfoViewModel;
    private MemberViewModel memberViewModel;

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
        saleInfoViewModel = ViewModelProviders.of(this).get(SaleInfoViewModel.class);
        saleListViewModel = ViewModelProviders.of(this).get(SaleListViewModel.class);

        memberViewModel = ViewModelProviders.of(this).get(MemberViewModel.class);
        memberViewModel.getAllres().observe(this, new Observer<List<Membership>>() {
            @Override
            public void onChanged(List<Membership> memberships) {
                if(memberships != null) {
                    mems.clear();
                    mems.addAll(memberships);
                }
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
        vipId = findViewById(R.id.mem_pur_id);
        recyclerView = findViewById(R.id.add_pur_rv);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        saleId.setText(getId() + "");
        add.setOnClickListener(v -> {
            showDialog();
        });
        done = findViewById(R.id.done);
        done.setOnClickListener(v -> {
            if(list != null) {
                double total = 0.0;
                int num = 0;
                int preNum = 0;
                for(Info i : list) {
                    double price = 0.0;
                    int id = 0;
                    for(BookInfo f : books) {
                        if(i.getName().equals(f.getBookName())){
                            price = f.getBookOutPrice();
                            id = f.getBookId();
                        }
                    }
                    for(Depository d : depos) {
                        if(i.getName().equals(d.getBookName())) {
                            num = i.getNum();
                            preNum = d.getBookNum();
                            if(i.getNum() > d.getBookNum()) {
                                Toast.makeText(this, i.getName() + "数量不够，将所有库存" + d.getBookNum() + "本卖出",Toast.LENGTH_SHORT).show();
                                num = d.getBookNum();
                            }
                        }
                    }
                    total += price * num;
                    saleInfoViewModel.insert(new SaleInfo(getId(), id, price, num));
                    depositoryViewModel.update(new Depository(id, i.getName(), preNum - num));
                }
                Toast.makeText(this, "总价为" + total + " 元", Toast.LENGTH_SHORT).show();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
                Date date = new Date(System.currentTimeMillis());
                String d = formatter.format(date);
                int vid = Integer.parseInt(vipId.getText().toString());
                saleListViewModel.insert(new SaleList(getId(), total, d, vid));
                String vName = "", vTime = "", vlevel = "青铜";
                double vTotal = 0.0;
                for(Membership m: mems) {
                    if(m.getMemberId() == vid){
                        vName = m.getMemberName();
                        vTime = m.getRegisterTime();
                        String preLevel = m.getLevel();
                        vTotal = m.getTotalConsumption() + total;
                        if(vTotal > 1000 && vTotal <= 2000) {
                            vlevel = "白银";
                        }else if(vTotal > 2000 && vTotal <= 5000){
                            vlevel = "黄金";
                        }else if(vTotal > 5000 && vTotal <= 10000){
                            vlevel = "钻石";
                        }else if(vTotal > 10000) {
                            vlevel = "王者";
                        }
                        if(!preLevel.equals(vlevel)) {
                            Toast.makeText(this, "恭喜您成为了" + vlevel + "vip！", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                memberViewModel.update(new Membership(vid, vName, vTime, vTotal, vlevel));
                finish();
            }

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

    public int getId() {
        int id;
        saleListViewModel.getAllres().observe(this, new Observer<List<SaleList>>() {
            @Override
            public void onChanged(List<SaleList> saleLists) {
                if(saleLists != null) {
                    saleList.clear();
                    saleList.addAll(saleLists);
                }
            }
        });
        if(saleList == null) {
            id = 1;
        }else {
            id = saleList.size();
        }
        return id+1;
    }
}
