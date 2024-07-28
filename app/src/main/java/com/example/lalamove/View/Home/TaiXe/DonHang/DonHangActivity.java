package com.example.lalamove.View.Home.TaiXe.DonHang;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lalamove.R;
import com.google.android.material.tabs.TabLayout;

import com.example.lalamove.Fragment.FragmentDaHoanThanh;
import com.example.lalamove.Fragment.FragmentDaHuy;
import com.example.lalamove.Fragment.FragmentDangTai;
import com.example.lalamove.Fragment.ViewPagerAdapter;

public class DonHangActivity extends AppCompatActivity {
    ViewPager viewPager;
    TabLayout tabLayout;
    Toolbar toolbar;
    ImageView img_TaiXe_DonHang_QuayLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhang_taixe);
        //Anh Xạ
        AnhXa();
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        //click
        img_TaiXe_DonHang_QuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FragmentChoNhanDon(), "Đơn đang chờ");
        adapter.addFragment(new FragmentDonDaHoanThanh(), "Đã hoàn thành");
        adapter.addFragment(new FragmentDonHangDaHuy(), "Đã hủy");
        viewPager.setAdapter(adapter);
    }
    void AnhXa(){
        img_TaiXe_DonHang_QuayLai = findViewById(R.id.img_TaiXe_DonHang_QuayLai);
        toolbar = findViewById(R.id.toolbar_DonHang_TaiXe);
        viewPager = findViewById(R.id.view_pager_DonHang_TaiXe);
        tabLayout = findViewById(R.id.tab_layout_Donhang_TaiXe);
    }
}
