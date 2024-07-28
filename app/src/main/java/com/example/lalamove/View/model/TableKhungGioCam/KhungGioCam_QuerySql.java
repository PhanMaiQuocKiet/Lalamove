package com.example.lalamove.View.model.TableKhungGioCam;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.DTO.KhungGioCam;
import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhungGioCam_QuerySql {
    private Context context;
    ConnectionHelper connectionHelper;
    Connection con;

    public KhungGioCam_QuerySql(Context context) {
        this.context = context;
    }



    public List<KhungGioCam> getKhungGioCamList() {
        List<KhungGioCam> khungGioCamList = new ArrayList<>();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;

        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT kg.makhunggio, kg.giobatdau, kg.gioketthuc, ckgx.maphuongtien " +
                        "FROM KhungGioCam kg " +
                        "JOIN ChiTietKhungGioXe ckgx ON kg.makhunggio = ckgx.makhunggio";
                preparedStatement = con.prepareStatement(sql);

                rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    String makhunggio = rs.getString("makhunggio");
                    String gioBatDau = rs.getString("giobatdau");
                    String gioKetThuc = rs.getString("gioketthuc");
                    String maPhuongTienBiCam = rs.getString("maphuongtien");

                    // Tìm KhungGioCam hiện có hoặc tạo mới nếu chưa tồn tại
                    KhungGioCam khungGioCam = khungGioCamList.stream()
                            .filter(k -> k.getMaKhungGio().equals(makhunggio))
                            .findFirst()
                            .orElse(null);

                    if (khungGioCam == null) {
                        khungGioCam = new KhungGioCam(makhunggio, gioBatDau, gioKetThuc, new ArrayList<>());
                        khungGioCamList.add(khungGioCam);
                    }

                    // Thêm mã phương tiện bị cấm vào danh sách
                    khungGioCam.getMaPhuongTienBiCam().add(maPhuongTienBiCam);
                }
            } else {
                Toast.makeText(context, "Lỗi kết nối cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi lấy dữ liệu khung giờ cấm: " + e.getMessage());
            Toast.makeText(context, "Lỗi khi truy xuất dữ liệu khung giờ cấm", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (rs != null) rs.close();
                if (preparedStatement != null) preparedStatement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
        return khungGioCamList;
    }
}
