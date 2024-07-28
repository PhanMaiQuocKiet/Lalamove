package com.example.lalamove.View.Home.KhachHang.DonHang;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lalamove.Fragment.FragmentDaHuy;
import com.example.lalamove.Fragment.ViewPagerAdapter;
import com.example.lalamove.R;
import com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi.GopYActivity;
import com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi.HoTroActivity;
import com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi.KhieuNaiActivity;
import com.google.android.material.tabs.TabLayout;

public class DonHangActivity extends AppCompatActivity {
    ImageView img_QuayLai;
    TabLayout tab_layout_Donhang;
    ViewPager view_pager_DonHang;
    Toolbar toolbar_DonHang;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhang);
        //Anh Xa
        AnhXa();
        //set toolBar
        setSupportActionBar(toolbar_DonHang);
        //set view
        setupViewPager(view_pager_DonHang);
        //set tab
        tab_layout_Donhang.setupWithViewPager(view_pager_DonHang);
        img_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
    void AnhXa(){
        view_pager_DonHang = findViewById(R.id.view_pager_DonHang);
        tab_layout_Donhang = findViewById(R.id.tab_layout_Donhang);
        img_QuayLai = findViewById(R.id.img_QuayLai);
        toolbar_DonHang = findViewById(R.id.toolbar_DonHang);
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FragmentDonHangDangTai(), "Đang tải");
        adapter.addFragment(new FragmentDonHangHoanThanh(), "Hoàn Thành");
        adapter.addFragment(new FragmentDonHangDaHuy(), "Đã hủy");
        viewPager.setAdapter(adapter);
    }
}
