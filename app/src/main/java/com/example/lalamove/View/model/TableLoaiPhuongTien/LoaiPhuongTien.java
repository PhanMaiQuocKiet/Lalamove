package com.example.lalamove.View.model.TableLoaiPhuongTien;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhuongTien {
    private Context context;
    private Spinner spinner;
    private ConnectionHelper connectionHelper;
    private Connection con;
    public LoaiPhuongTien() {

    }
    public LoaiPhuongTien(Context context, Spinner spinner) {
        this.context = context;
        this.spinner = spinner;
        this.connectionHelper = new ConnectionHelper();
    }

    public void loadDataTenPhuongTien() {
        List<String> phuongTienList = new ArrayList<>();
        try {
            con = connectionHelper.connectionClass();
            if (con != null) {
                String query = "SELECT tenphuongtien FROM LoaiPhuongTien";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                boolean flags = false;
                while (rs.next()) {
                    flags = true;
                    phuongTienList.add(rs.getString("tenphuongtien"));
                }

                if (!flags) {
                    Toast.makeText(context, "Loại phương tiện hiện chưa tồn tại", Toast.LENGTH_SHORT).show();
                } else {
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, phuongTienList);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinner.setAdapter(adapter);
                }

                rs.close();
                stmt.close();
                con.close();
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(context, "Lỗi: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
    // Tìm Kiếm mã phương tiện theo tên
    public String getMaPhuongTien(String tenphuongtien, Context context) {
        String maphuongtien = "";
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT maphuongtien FROM LoaiPhuongTien WHERE tenphuongtien = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);

                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, tenphuongtien);

                // Thực thi truy vấn
                ResultSet rs = preparedStatement.executeQuery();

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    maphuongtien = rs.getString(1);
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu ", Toast.LENGTH_SHORT).show();
                }

                // Đóng kết nối và các resource
                rs.close();
                preparedStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return maphuongtien;
    }

}