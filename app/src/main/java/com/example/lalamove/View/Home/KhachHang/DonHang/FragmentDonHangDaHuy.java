package com.example.lalamove.View.Home.KhachHang.DonHang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.ListView;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lalamove.DTO.DonHang;
import com.example.lalamove.R;
import com.example.lalamove.View.model.TableDonDatGiaoHang.QuerySql_DonDatGiaoHang;

import java.util.List;

public class FragmentDonHangDaHuy extends Fragment implements DonHangDangTaiAdapter.onXemChiTietClickListener{
    private ListView lv_DonHang_DaHuy;

    private LinearLayout ln_hienthi_DaHuy;

    int size = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_da_huy, container, false);

        // Lấy dữ liệu từ SharedPreferences
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String soDienThoai = sharedPreferences.getString("sodienthoai", "");
        Log.d("FragmentDonHangDaHuy", "onCreateView: frmDonHuy " + soDienThoai);
        // Ánh xạ các view và tải dữ liệu
        AnhXa(v, soDienThoai);

        return v;

    }

    private void AnhXa(View v, String soDienThoai) {
        ln_hienthi_DaHuy = v.findViewById(R.id.ln_hienthi_DaHuy);
        lv_DonHang_DaHuy = v.findViewById(R.id.lv_DonHang_DaHuy);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDataAllHuy(requireContext(), lv_DonHang_DaHuy, this::setAdapter, soDienThoai, "Hủy");
    }

    private void setAdapter(List<DonHang> listDonHang) {
        if (getActivity() != null) {
            DonHangDangTaiAdapter adapter = new DonHangDangTaiAdapter(getActivity(), R.layout.layout_list_donhang, listDonHang);
            adapter.setOnNhanDonClickListener(this);
            lv_DonHang_DaHuy.setAdapter(adapter);
            size = listDonHang.size();
            adapter.notifyDataSetChanged();

            if (size <= 0) {
                lv_DonHang_DaHuy.setVisibility(View.GONE);
                ln_hienthi_DaHuy.setVisibility(View.VISIBLE);
            } else {
                lv_DonHang_DaHuy.setVisibility(View.VISIBLE);
                ln_hienthi_DaHuy.setVisibility(View.GONE);
            }
        }

    }

    @Override
    public void onXemChiTietClick(DonHang donHang) {

    }
}
