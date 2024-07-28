package com.example.lalamove.ListLoaiXe;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lalamove.DTO.KhungGioCam;
import com.example.lalamove.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class VehicleAdapter extends ArrayAdapter<PhuongTien> {
    private int selectedPosition = -1;
    private List<KhungGioCam> khungGioCamList;
    private OnVehicleSelectedListener listener;

    public String getSelectedVehicleId() {
        if (selectedPosition != -1) {
            return getItem(selectedPosition).getMaPhuongTien();
        }
        return null;
    }

    public interface OnVehicleSelectedListener {
        void onVehicleSelected(int position);
    }

    public void setOnVehicleSelectedListener(OnVehicleSelectedListener listener) {
        this.listener = listener;
    }

    public int getSelectedPosition() {
        return selectedPosition;
    }

    public VehicleAdapter(Context context, ArrayList<PhuongTien> vehicles) {
        super(context, 0, vehicles);
        khungGioCamList = new ArrayList<>(); // Khởi tạo danh sách để tránh null
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PhuongTien vehicle = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.vehicle_list_item, parent, false);
        }

        // Kiểm tra xem phương tiện có bị cấm không
        String gioHienTai = getCurrentTime();
        boolean isBiCam = isPhuongTienBiCam(vehicle.getMaPhuongTien(), gioHienTai);

        ImageView vehicleIcon = convertView.findViewById(R.id.vehicle_icon);
        TextView vehicleName = convertView.findViewById(R.id.vehicle_name);
        CheckBox checkIcon = convertView.findViewById(R.id.check_icon);

        // Cập nhật tên và icon cho phương tiện
        vehicleName.setText(vehicle.getTenPhuongTien() + " " + vehicle.getTrongLuong() + "Kg");

        // Thiết lập icon tùy thuộc vào loại phương tiện
        if (vehicle.getTenPhuongTien().equals("Xe May")) {
            vehicleIcon.setImageResource(R.drawable.ic_scooter);
        } else if (vehicle.getTenPhuongTien().equals("Xe Tai Nho") || vehicle.getTenPhuongTien().equals("Xe Van Nho")) {
            vehicleIcon.setImageResource(R.drawable.ic_truck);
        } else {
            vehicleIcon.setImageResource(R.drawable.ic_semi_truck);
        }

        // Quản lý hiển thị trạng thái đã chọn
        if (position == selectedPosition) {
            convertView.setBackgroundResource(R.drawable.vehicle_item_background_after);
            checkIcon.setChecked(true);
        } else {
            convertView.setBackgroundResource(R.drawable.vehicle_item_background);
            checkIcon.setChecked(false);
        }

        // Đặt enable và alpha dựa trên trạng thái cấm
        convertView.setEnabled(!isBiCam);
        convertView.setAlpha(isBiCam ? 0.5f : 1.0f);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isBiCam) {
                    selectedPosition = (selectedPosition == position) ? -1 : position;
                    notifyDataSetChanged();
                    if (listener != null) {
                        listener.onVehicleSelected(selectedPosition);
                    }
                }
            }
        });

        return convertView;
    }

    public void setKhungGioCamList(List<KhungGioCam> khungGioCamList) {
        this.khungGioCamList = khungGioCamList;
    }

    private boolean isPhuongTienBiCam(String maPhuongTien, String gioHienTai) {
        for (KhungGioCam khungGio : khungGioCamList) {
            if (gioHienTai.compareTo(khungGio.getGioBatDau()) >= 0 &&
                    gioHienTai.compareTo(khungGio.getGioKetThuc()) <= 0 &&
                    khungGio.getMaPhuongTienBiCam().contains(maPhuongTien)) {
                return true;
            }
        }
        return false;
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return sdf.format(new Date());
    }
}
