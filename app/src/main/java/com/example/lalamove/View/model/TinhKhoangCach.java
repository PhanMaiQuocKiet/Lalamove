package com.example.lalamove.View.model;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class TinhKhoangCach {

    private Context context;

    public TinhKhoangCach(Context context) {
        this.context = context;
    }

    public double getDistance(String startAddress, String endAddress) {
        try {
            Geocoder geocoder = new Geocoder(context, Locale.getDefault());

            // Chuyển đổi địa chỉ thành tọa độ
            List<Address> startAddresses = geocoder.getFromLocationName(startAddress, 1);
            List<Address> endAddresses = geocoder.getFromLocationName(endAddress, 1);

            if (startAddresses.size() > 0 && endAddresses.size() > 0) {
                double startLat = startAddresses.get(0).getLatitude();
                double startLon = startAddresses.get(0).getLongitude();
                double endLat = endAddresses.get(0).getLatitude();
                double endLon = endAddresses.get(0).getLongitude();

                // Tính khoảng cách
                return calculateDistance(startLat, startLon, endLat, endLon);
            } else {
                throw new IOException("Không thể tìm thấy địa chỉ");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1; // Trả về -1 nếu có lỗi
        }
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        // Bán kính Trái Đất tính bằng km
        final int R = 6371;
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;
        return Math.round(distance * 100.0) / 100.0; // Làm tròn đến 2 chữ số thập phân
    }
}