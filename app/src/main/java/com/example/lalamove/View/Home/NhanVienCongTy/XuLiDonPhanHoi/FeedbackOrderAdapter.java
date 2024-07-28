package com.example.lalamove.View.Home.NhanVienCongTy.XuLiDonPhanHoi;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.lalamove.R;

import java.util.List;
public class FeedbackOrderAdapter extends RecyclerView.Adapter<FeedbackOrderAdapter.ViewHolder> {
    private final List<FeedbackOrder> feedbackOrders;

    public FeedbackOrderAdapter(List<FeedbackOrder> feedbackOrders) {
        this.feedbackOrders = feedbackOrders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_item_xu_li_don, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        FeedbackOrder feedbackOrder = feedbackOrders.get(position);
        holder.tvTime.setText(feedbackOrder.getThoidiemguidon());
        holder.tvMaDon.setText(String.valueOf(feedbackOrder.getMadonphanhoi()));
        holder.tvTrangThai.setText(feedbackOrder.getTrangthaihotro());
        holder.btnXemChiTiet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context, ChiTietXuLiDonActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return feedbackOrders.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTime, tvMaDon, tvTrangThai;

        public Button btnXemChiTiet;
        ViewHolder(View itemView) {
            super(itemView);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvMaDon = itemView.findViewById(R.id.tv_MaDon);
            tvTrangThai = itemView.findViewById(R.id.tv_TrangThai);
            btnXemChiTiet = itemView.findViewById(R.id.btn_xemchitiet);
        }
    }
}
