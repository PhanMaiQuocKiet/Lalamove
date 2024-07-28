package com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.lalamove.Fragment.ViewPagerAdapter;
import com.example.lalamove.R;
import com.google.android.material.tabs.TabLayout;

public class XuLiDonPhanHoiActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_xu_li_don_phan_hoi);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new HoTroActivity(), "Hỗ trợ");
        adapter.addFragment(new KhieuNaiActivity(), "Khiếu nại");
        adapter.addFragment(new GopYActivity(), "Góp ý");
        viewPager.setAdapter(adapter);
    }
}
