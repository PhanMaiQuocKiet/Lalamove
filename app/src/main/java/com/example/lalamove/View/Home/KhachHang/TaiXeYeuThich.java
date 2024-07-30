package com.example.lalamove.View.Home.KhachHang;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalamove.R;
import com.example.lalamove.View.Home.KhachHang.TXYT.TXyeuthich;
import com.example.lalamove.View.Home.KhachHang.TXYT.TXyeuthichAdapter;
import com.example.lalamove.View.model.TableTaiXeYeuThich.TXYT_QuerySql;

import java.util.List;

public class TaiXeYeuThich extends AppCompatActivity {
private TXYT_QuerySql txytsql;
private RelativeLayout layout_chuacoTX;
private SharedPreferences sf;
private RecyclerView rcView;
private List<TXyeuthich> datas;
private String sdt;
private Button btn_themtx;
private ImageButton btn_back;
private TXyeuthichAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_taixeyeuthich);
        sf = getSharedPreferences("ThongTinDangNhap",MODE_PRIVATE);
        sdt = sf.getString("sodienthoai","");
        init();
        loadData();
        btn_back.setOnClickListener(c->{
            finish();
        });
        btn_themtx.setOnClickListener(c->{
        showAddFavoriteDriverDialog();
        });

    }
    void loadData()
    {
        if (!datas.isEmpty())
        {
            rcView.setVisibility(View.VISIBLE);
            layout_chuacoTX.setVisibility(View.GONE);
        }
        else {

        }
    }
    private void showAddFavoriteDriverDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thêm tài xế yêu thích");

        // Set up the input
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_PHONE);
        input.setHint("Nhập số điện thoại tài xế");
        builder.setView(input);

        // Set up the buttons
        builder.setPositiveButton("Thêm", (dialog, which) -> {
            String driverPhone = input.getText().toString();
            if (!driverPhone.isEmpty()) {
                addFavoriteDriver(driverPhone);
            } else {
                Toast.makeText(TaiXeYeuThich.this, "Vui lòng nhập số điện thoại", Toast.LENGTH_SHORT).show();
            }
        });
        builder.setNegativeButton("Hủy", (dialog, which) -> dialog.cancel());

        builder.show();
    }
    private void addFavoriteDriver(String driverPhone) {
        // Thêm logic để thêm tài xế yêu thích vào cơ sở dữ liệu ở đây
        // Ví dụ:
        boolean success =txytsql.ThemTXyeuthich(sdt, driverPhone);
        if (success) {
            // Nếu thêm thành công, cập nhật danh sách và RecyclerView
            TXyeuthich newDriver = new TXyeuthich(driverPhone, sdt, 0);
            datas.add(newDriver);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "Đã thêm tài xế yêu thích", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Không thể thêm tài xế yêu thích", Toast.LENGTH_SHORT).show();
        }
    }

    void init()
    {
        btn_back=findViewById(R.id.btn_back_TXYT);
        btn_themtx = findViewById(R.id.btn_themtxyt);
        layout_chuacoTX=findViewById(R.id.img_chuacotx);
        txytsql = new TXYT_QuerySql();
        rcView = findViewById(R.id.recyclerViewFavoriteDrivers);
        rcView.setLayoutManager(new LinearLayoutManager(this));
        datas = txytsql.getFavoriteDrivers(sdt, this);
        adapter = new TXyeuthichAdapter(datas);
        rcView.setAdapter(adapter);
    }
}