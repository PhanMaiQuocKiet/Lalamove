package com.example.lalamove;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import com.example.lalamove.Fragment.FragmentDaHoanThanh;
import com.example.lalamove.Fragment.FragmentDaHuy;
import com.example.lalamove.Fragment.FragmentDangTai;
import com.example.lalamove.Fragment.ViewPagerAdapter;

public class DonHangActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_donhang);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.view_pager);
        setupViewPager(viewPager);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        adapter.addFragment(new FragmentDangTai(), "Đang tải");
        adapter.addFragment(new FragmentDaHoanThanh(), "Đã hoàn thành");
        adapter.addFragment(new FragmentDaHuy(), "Đã hủy");
        viewPager.setAdapter(adapter);
    }
}
