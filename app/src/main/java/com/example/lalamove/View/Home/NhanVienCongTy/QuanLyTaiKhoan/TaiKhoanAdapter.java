package com.example.lalamove.View.Home.NhanVienCongTy.QuanLyTaiKhoan;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lalamove.R;
import com.example.lalamove.database.data.TaiKhoan;
import java.util.List;

public class TaiKhoanAdapter extends RecyclerView.Adapter<TaiKhoanAdapter.TaiKhoanViewHolder> {

    private List<QuanLiTatCaTaiKhoan> taiKhoanList;
    private TaiKhoanDAO taiKhoanDAO;

    public TaiKhoanAdapter(List<QuanLiTatCaTaiKhoan> taiKhoanList, TaiKhoanDAO taiKhoanDAO) {
        this.taiKhoanList = taiKhoanList;
        this.taiKhoanDAO = taiKhoanDAO;
    }

    @NonNull
    @Override
    public TaiKhoanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_quan_li_tai_khoan, parent, false);
        return new TaiKhoanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaiKhoanViewHolder holder, int position) {
        QuanLiTatCaTaiKhoan taiKhoan = taiKhoanList.get(position);
        holder.tvTenTaiKhoan.setText(taiKhoan.getTen());
        holder.tvMatKhau.setText(taiKhoan.getMatkhau());
        holder.tvVaiTro.setText(taiKhoan.getLoaitaikhoan());

        holder.btnDelete.setOnClickListener(v -> {
            taiKhoanDAO.deleteTaiKhoan(taiKhoan.getTen());
            taiKhoanList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, taiKhoanList.size());
        });
        holder.btnFix.setOnClickListener(v -> {
            if ("KhachHang".equalsIgnoreCase(taiKhoan.getLoaitaikhoan())) {
                Toast.makeText(holder.itemView.getContext(), "Không được sửa tài khoản khách hàng", Toast.LENGTH_SHORT).show();
                return;
            }

            // Capture new data from EditText fields
            String oldTen = taiKhoan.getTen();
            String newTen = holder.tvTenTaiKhoan.getText().toString();
            String newMatkhau = holder.tvMatKhau.getText().toString();
            String newLoaitaikhoan = holder.tvVaiTro.getText().toString();

            // Update the QuanLiTatCaTaiKhoan object
            taiKhoan.setTen(newTen);
            taiKhoan.setMatkhau(newMatkhau);
            taiKhoan.setLoaitaikhoan(newLoaitaikhoan);

            // Update the database
            taiKhoanDAO.updateTaiKhoan(oldTen, taiKhoan);

            // Notify the adapter about the change
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return taiKhoanList.size();
    }

    public static class TaiKhoanViewHolder extends RecyclerView.ViewHolder {
        TextView tvTenTaiKhoan, tvMatKhau, tvVaiTro;
        ImageView btnDelete, btnFix;

        public TaiKhoanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTenTaiKhoan = itemView.findViewById(R.id.tv_TenTaiKhoan);
            tvMatKhau = itemView.findViewById(R.id.tv_MatKhau);
            tvVaiTro = itemView.findViewById(R.id.tv_VaiTro);
            btnDelete = itemView.findViewById(R.id.btn_Delete);
            btnFix = itemView.findViewById(R.id.btn_Fix);
        }
    }
    public void setTaiKhoanList(List<QuanLiTatCaTaiKhoan> taiKhoanList) {
        this.taiKhoanList = taiKhoanList;
        notifyDataSetChanged();
    }
}