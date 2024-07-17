package com.example.lalamove.ListLoaiXe;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;


import com.example.lalamove.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class FragmentLoaiXe extends AppCompatActivity {
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private EditText diadiemtrakhach;
    private EditText chondiadiem;
    private LinearLayout additionalInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_trangchu_khachhang);

        // Ánh xạ các view
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        diadiemtrakhach = findViewById(R.id.diadiemtrakhach);
        chondiadiem = findViewById(R.id.chondiadiem);
        ListView vehicleList = findViewById(R.id.vehicle_list);

        // Inflate the additional interface layout
        additionalInterface = (LinearLayout) getLayoutInflater().inflate(R.layout.layout_trangchu_menuthanhtoan, null);

        // Set click listeners
        diadiemtrakhach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdditionalInterface();
            }
        });

        chondiadiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAdditionalInterface();
            }
        });

        diadiemtrakhach.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hideAdditionalInterface();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        chondiadiem.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() == 0) {
                    hideAdditionalInterface();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                showAdditionalInterface();
                drawerLayout.closeDrawers();
                return true;
            }
        });

        // Thiết lập danh sách phương tiện
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        vehicles.add(new Vehicle(R.drawable.ic_scooter, "Xe Máy", "Vận chuyển mặt hàng nhỏ giá trị đến 3 triệu đồng", "0.5 x 0.4 x 0.5 Mét - Lên đến 30 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_van_500kg, "Xe Van 500 kg", "Vận chuyển hàng hóa lớn hơn", "1.0 x 0.8 x 0.8 Mét - Lên đến 500 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_van_500kg, "Xe Van 750 kg", "Vận chuyển hàng hóa lớn hơn", "1.2 x 1.0 x 1.0 Mét - Lên đến 750 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_van_500kg, "Xe Van 1000 kg", "Vận chuyển hàng hóa lớn hơn", "1.5 x 1.2 x 1.2 Mét - Lên đến 1000 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_truck, "Xe Bán Tải", "Vận chuyển hàng hóa lớn hơn", "2.0 x 1.5 x 1.5 Mét - Lên đến 1500 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_semi_truck, "Xe Tải 500 kg", "Vận chuyển hàng hóa lớn hơn", "2.5 x 2.0 x 2.0 Mét - Lên đến 2000 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_semi_truck, "Xe Tải 1000 kg", "Vận chuyển hàng hóa lớn hơn", "3.0 x 2.5 x 2.5 Mét - Lên đến 3000 kg"));
        vehicles.add(new Vehicle(R.drawable.ic_semi_truck, "Xe Tải 1250 kg", "Vận chuyển hàng hóa lớn hơn", "3.5 x 3.0 x 3.0 Mét - Lên đến 3500 kg"));

        VehicleAdapter adapter = new VehicleAdapter(this, vehicles);
        vehicleList.setAdapter(adapter);

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

        // Xử lý sự kiện nhấn vào button trong layout_trangchu_menuthanhtoan
        Button loginButton = additionalInterface.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FragmentLoaiXe.this, BuocTiepTheoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void showAdditionalInterface() {
        // Thêm giao diện bổ sung vào layout chính
        FrameLayout mainLayout = findViewById(R.id.main_layout);
        if (mainLayout.indexOfChild(additionalInterface) == -1) {
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
            );
            params.gravity = Gravity.BOTTOM;
            mainLayout.addView(additionalInterface, params);
        }
    }

    private void hideAdditionalInterface() {
        // Ẩn giao diện bổ sung khỏi layout chính
        FrameLayout mainLayout = findViewById(R.id.main_layout);
        if (mainLayout.indexOfChild(additionalInterface) != -1) {
            mainLayout.removeView(additionalInterface);
        }
    }
}