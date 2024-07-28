package com.example.lalamove.View.Home.KhachHang.DonHang;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
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

public class FragmentDonHangDangTai extends Fragment implements DonHangDangTaiAdapter.onXemChiTietClickListener {
    private ListView lv_DonHang_DangTai;
    private String trangthai1 = "Chờ nhận hàng";
    private String trangthai2 = "Đang giao";
    private LinearLayout ln_hienthi_DangTai;
    int size = 0;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dang_tai, container, false);
        //Lấy dữ liệu từ share
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("ThongTinDangNhap", Context.MODE_PRIVATE);
        String soDienThoai = sharedPreferences.getString("sodienthoai","");

        //Anh Xa
        AnhXa(view, soDienThoai);

        if(size <= 0){
            lv_DonHang_DangTai.setVisibility(View.GONE);
            ln_hienthi_DangTai.setVisibility(View.VISIBLE);
        }else{
            lv_DonHang_DangTai.setVisibility(View.VISIBLE);
            ln_hienthi_DangTai.setVisibility(View.GONE);
        }
        return view;
    }

    void AnhXa(View v , String soDienThoai) {
        ln_hienthi_DangTai= v.findViewById(R.id.ln_hienthi_DangTai);
        lv_DonHang_DangTai = v.findViewById(R.id.lv_DonHang_DangTai);
        QuerySql_DonDatGiaoHang querySql = new QuerySql_DonDatGiaoHang();
        querySql.getDataAll2(requireContext(), lv_DonHang_DangTai, this::setAdapter, soDienThoai,trangthai1,trangthai2);

    }

    private void setAdapter(List<DonHang> listDonHang) {
        if (getActivity()!= null) {
            DonHangDangTaiAdapter adapter = new DonHangDangTaiAdapter(getActivity(), R.layout.layout_list_donhang, listDonHang);
            adapter.setOnNhanDonClickListener(this);
            lv_DonHang_DangTai.setAdapter(adapter);
            size = listDonHang.size();
            adapter.notifyDataSetChanged(); // Thêm dòng này

        }

    }

    @Override
    public void onXemChiTietClick(DonHang donHang) {

    }

}
