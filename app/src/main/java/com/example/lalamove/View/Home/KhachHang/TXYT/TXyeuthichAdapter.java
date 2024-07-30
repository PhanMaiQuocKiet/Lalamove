package com.example.lalamove.View.Home.KhachHang.TXYT;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lalamove.R;

import java.util.List;

public class TXyeuthichAdapter extends RecyclerView.Adapter<TXyeuthichAdapter.ViewHolder> {
    private List<TXyeuthich> drivers;

    public TXyeuthichAdapter(List<TXyeuthich> drivers) {
        this.drivers = drivers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_taixeyt, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TXyeuthich driver = drivers.get(position);
        if(driver!=null)
            {
        holder.textViewDriverPhone.setText("Tài xế: " + driver.getDriverPhone());
        holder.textViewRating.setText("Số đơn đã hoàn thành:" + driver.getRating());
            }
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView textViewDriverPhone;
        TextView textViewRating;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewDriverPhone = itemView.findViewById(R.id.textViewDriverPhone);
            textViewRating = itemView.findViewById(R.id.textViewRating);
        }
    }
}
