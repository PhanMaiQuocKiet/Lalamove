package com.example.lalamove.View.Home.KhachHang;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.lalamove.DTO.KhungGioCam;
import com.example.lalamove.ListLoaiXe.PhuongTien;
import com.example.lalamove.ListLoaiXe.VehicleAdapter;
import com.example.lalamove.R;
import com.example.lalamove.View.Home.KhachHang.DonHang.DonHangActivity;
import com.example.lalamove.View.Login.DangNhapActivity;
import com.example.lalamove.View.model.TableKhungGioCam.KhungGioCam_QuerySql;
import com.example.lalamove.View.model.TableLoaiPhuongTien.QuerySql;
import com.example.lalamove.View.model.TinhKhoangCach;
import com.google.android.material.navigation.NavigationView;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class Home_KhachHang extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText edt_NoiGiao, edt_NoiNhan;
    private LinearLayout additionalInterface, ln_listXeMay;
    private ListView vehicleList;
    private Button btn_next;
    private CheckBox cb_item;
    private TextView tv_tongtien;
    private LinearLayout thanhtoantt;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    private int tongTien;

    @SuppressLint("InflateParams")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu_khachhang);

        // AnhXa
        AnhXa();

        //Khai báo sharedPreferences
        sharedPreferences = Home_KhachHang.this.getSharedPreferences("LuongDatHang", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        thanhtoantt.setVisibility(View.GONE); // Ẩn thanh toán ban đầu

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                drawerLayout.closeDrawers();
                int menuID = item.getItemId();
                if (menuID == R.id.nav_orders) {
                    //Di chuyển tới trang đơn hàng
                    ChuyenTrang(Home_KhachHang.this, DonHangActivity.class);
                    return true;
                } else if (menuID == R.id.nav_recharge) {
                    return true;
                } else if (menuID == R.id.nav_favorite_drivers) {
                    Intent intent = new Intent(Home_KhachHang.this, ThongTinTK_KhachHang.class);
                    startActivity(intent);
                    return true;
                } else if (menuID == R.id.nav_settings) {
                    return true;
                } else if (menuID == R.id.nav_help_center) {
                    ChuyenTrang(Home_KhachHang.this, TrungTamTroGiupActivity.class);
                    return true;
                } else if (menuID == R.id.nav_logout) {
//                    ChuyenTrang(Home_KhachHang.this, DangNhapActivity.class);
                    Intent intent = new Intent(Home_KhachHang.this, DangNhapActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                    return true;
                } else
                    return true;
            }
        });

        // Tải dữ liệu từ database và thiết lập danh sách phương tiện
        QuerySql querySql = new QuerySql();
        ArrayList<PhuongTien> vehicles = new ArrayList<>(querySql.getDataPhuongTien());

        VehicleAdapter adapter = new VehicleAdapter(this, vehicles);
        List<KhungGioCam> khungGioCamList = getKhungGioCamList();
        adapter.setKhungGioCamList(khungGioCamList);
        vehicleList.setAdapter(adapter);
        adapter.setOnVehicleSelectedListener(new VehicleAdapter.OnVehicleSelectedListener() {
            @Override
            public void onVehicleSelected(int position) {
                checkAndShowPayment();
            }
        });

        // Xử lý sự kiện nhấn vào icon menu
        ImageView menuIcon = findViewById(R.id.menu_icon);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });

        // Xử lý sự kiện nhấn vào icon mũi tên trong navigation view
        View headerView = navigationView.getHeaderView(0);
        ImageView arrowIcon = headerView.findViewById(R.id.arrow_icon);
        arrowIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String noiGiao = edt_NoiGiao.getText().toString().trim();
                String noiNhan = edt_NoiNhan.getText().toString().trim();

                VehicleAdapter adapter = (VehicleAdapter) vehicleList.getAdapter();
                String maPhuongTien = adapter.getSelectedVehicleId();

                if (maPhuongTien != null) {
                    editor.putString("noinhan", noiNhan);
                    editor.putString("noigiao", noiGiao);
                    editor.putString("maphuongtien", maPhuongTien);
                    editor.putInt("tongtien",tongTien); // Lưu tổng tiền
                    editor.apply();

                    Intent intent = new Intent(Home_KhachHang.this, BuocTiepTheoActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(Home_KhachHang.this, "Vui lòng chọn phương tiện", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edt_NoiGiao.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkAndShowPayment();
            }
        });

        edt_NoiNhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                checkAndShowPayment();
            }
        });
    }
    private List<KhungGioCam> getKhungGioCamList() {
        KhungGioCam_QuerySql khungGioCamQuerySql = new KhungGioCam_QuerySql(this);
        return khungGioCamQuerySql.getKhungGioCamList();
    }

    @SuppressLint("DefaultLocale")
    private void checkAndShowPayment() {
        String noiGiao = edt_NoiGiao.getText().toString().trim();
        String noiNhan = edt_NoiNhan.getText().toString().trim();
        boolean isAddressFilled = !noiGiao.isEmpty() && !noiNhan.isEmpty();
        VehicleAdapter adapter = (VehicleAdapter) vehicleList.getAdapter();
        boolean isVehicleSelected = adapter != null && adapter.getSelectedPosition() != -1;

        if (isAddressFilled && isVehicleSelected) {
            TinhKhoangCach calculator = new TinhKhoangCach(this);
            double distance = calculator.getDistance(noiNhan, noiGiao);

            if (distance >= 0) {
                // Tính giá dựa trên khoảng cách và phương tiện được chọn
                tongTien = calculatePrice(distance, adapter.getSelectedPosition());

                // Hiển thị khoảng cách và giá
                DecimalFormat df = new DecimalFormat("#,###");
                int intDistance = (int) Math.round(distance);
                int intTongTien = (int) Math.round(tongTien);

                String formattedDistance = df.format(intDistance);
                String formattedTongTien = df.format(intTongTien);

                tv_tongtien.setText(String.format("%s km - %s đ", formattedDistance, formattedTongTien));
                thanhtoantt.setVisibility(View.VISIBLE);
                btn_next.setEnabled(true);
            } else {
                // Xử lý lỗi khi không thể tính khoảng cách
                Toast.makeText(this, "Không thể tính khoảng cách. Vui lòng kiểm tra lại địa chỉ.", Toast.LENGTH_SHORT).show();
                thanhtoantt.setVisibility(View.GONE);
                btn_next.setEnabled(false);
                tongTien = 0;
            }
        } else {
            thanhtoantt.setVisibility(View.GONE);
            btn_next.setEnabled(false);
            tongTien = 0;
        }
    }

    private int calculatePrice(double distance, int vehicleType) {
        double basePrice;
        switch (vehicleType) {
            case 0: // Xe máy
                basePrice = 10000;
                break;
            case 1: // Xe tai nhỏ
                basePrice = 35000;
                break;
            case 2: // Xe van nhỏ
                basePrice = 30000;
                break;
            case 3: // Xe tải trung
                basePrice = 65000;
                break;
            case 4: // Xe van trung
                basePrice = 50000;
                break;
            case 5: // Xe tải lớn
                basePrice = 100000;
                break;
            case 6: // Xe van lớn
                basePrice = 90000;
                break;
            default: // Xe tải lớn
                basePrice = 0;
                break;
        }
        return  (int)(basePrice * distance);
    }

    @SuppressLint({"InflateParams", "CutPasteId"})
    void AnhXa() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        edt_NoiGiao = findViewById(R.id.edt_diadiemtrakhach_Home_KhachHang);
        edt_NoiNhan = findViewById(R.id.edt_chondiadiem_Home_KhachHang);
        vehicleList = findViewById(R.id.vehicle_list);
        tv_tongtien = findViewById(R.id.tv_tongtien);
        btn_next = findViewById(R.id.btn_buoctieptheo);
        btn_next.setEnabled(false); // Nút "btn_next" vô hiệu hóa khi bắt đầu
        ln_listXeMay = (LinearLayout) getLayoutInflater().inflate(R.layout.vehicle_list_item, null);
        cb_item = ln_listXeMay.findViewById(R.id.check_icon);
        thanhtoantt = findViewById(R.id.thanhtoantt);
    }

    void ChuyenTrang(Context context, Class<?> lop) {
        Intent i = new Intent(context, lop);
        startActivity(i);
    }
}