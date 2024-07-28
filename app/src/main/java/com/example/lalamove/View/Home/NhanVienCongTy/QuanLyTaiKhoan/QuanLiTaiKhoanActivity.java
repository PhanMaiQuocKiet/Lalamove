package com.example.lalamove.View.Home.NhanVienCongTy.QuanLyTaiKhoan;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lalamove.R;
import com.example.lalamove.database.data.TaiKhoan;
import java.util.List;

public class QuanLiTaiKhoanActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TaiKhoanAdapter adapter;
    private TaiKhoanDAO taiKhoanDAO;
    private EditText etSearch;
    private Spinner spinnerRole;
    private TextView tvNoData; // Add this line

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_quan_li_tai_khoan);

        taiKhoanDAO = new TaiKhoanDAO(this);
        List<QuanLiTatCaTaiKhoan> taiKhoanList = taiKhoanDAO.getAllTaiKhoan();

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new TaiKhoanAdapter(taiKhoanList, taiKhoanDAO);
        recyclerView.setAdapter(adapter);

        etSearch = findViewById(R.id.et_search);
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterAccounts();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        spinnerRole = findViewById(R.id.spinner_role);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.roles_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRole.setAdapter(adapter);
        spinnerRole.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                filterAccounts();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        tvNoData = findViewById(R.id.tv_no_data); // Add this line
    }

    private void filterAccounts() {
        String keyword = etSearch.getText().toString();
        String role = spinnerRole.getSelectedItem().toString();
        List<QuanLiTatCaTaiKhoan> filteredList = taiKhoanDAO.searchTaiKhoanByRole(keyword, role);
        adapter.setTaiKhoanList(filteredList);

        if (filteredList.isEmpty()) {
            tvNoData.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            tvNoData.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}