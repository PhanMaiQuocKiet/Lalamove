package com.example.lalamove.View.model.TableTaiKhoan;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class TaiKhoanSQL {
    Connection con;
    ConnectionHelper connectionHelper;
    String ten,sodienthoai,mk;

    public void sp_update_mkTaiKhoan(String sodienthoai, String mkmoi, Context context) {
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            CallableStatement callableStatement;

            if (con != null) {
                String sql = "{call sp_update_mkTaiKhoan(?, ?) }";
                callableStatement = con.prepareCall(sql);
                callableStatement.setString(1, sodienthoai);
                callableStatement.setString(2, mkmoi);
                callableStatement.execute();
                Toast.makeText(context, "Cập nhật mật khẩu thành công", Toast.LENGTH_SHORT).show();
                // Đóng kết nối và các resource
                callableStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau", Toast.LENGTH_SHORT).show();
            }
            con.close();
        } catch (Exception e) {
            Log.e(TAG, "sp_update_mkTaiKhoan: " + e.getMessage());
        }
    }

    public String getLoaiTaiKhoan(String sodienthoai, Context context) {
        String loaiTaiKhoan = "";

        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "SELECT loaitaikhoan " +
                        "FROM TaiKhoan " +
                        "WHERE sodienthoai = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                // Thiết lập giá trị cho tham số của câu truy vấn
                preparedStatement.setString(1, sodienthoai);

                ResultSet rs = preparedStatement.executeQuery(); // Không cần truyền tham số vào phương thức

                // Xử lý kết quả trả về từ ResultSet
                if (rs.next()) {
                    loaiTaiKhoan = rs.getString(1);
                } else {
                    Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                }
                preparedStatement.close();
                rs.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return loaiTaiKhoan;
    }
    public ArrayList<String> sp_select_taikhoan (String sdt, Context context)
    {
            ArrayList<String> kq = new ArrayList<>();
        try {
                connectionHelper = new ConnectionHelper();
                con = connectionHelper.connectionClass();
                if (con != null) {
                    String sql = "SELECT sodienthoai,ten,matkhau " +
                            "FROM TaiKhoan " +
                            "WHERE sodienthoai = ?";
                    PreparedStatement preparedStatement = con.prepareStatement(sql);
                    // Thiết lập giá trị cho tham số của câu truy vấn
                    preparedStatement.setString(1, sdt);

                    ResultSet rs = preparedStatement.executeQuery(); // Không cần truyền tham số vào phương thức

                    // Xử lý kết quả trả về từ ResultSet
                    if (rs.next()) {
                        ten = rs.getString("ten");
                        sdt=rs.getString("sodienthoai");
                        mk=rs.getString("matkhau");
                        kq.add(ten);
                        kq.add(sdt);
                        kq.add(mk);
                    } else {
                        Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
                    }
                preparedStatement.close();
                rs.close();
                con.close();
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TAG", "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
        }
        return kq;
    }
    public void updatetttk(String sodienthoai, String tenMoi, Context context) {
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();
            if (con != null) {
                String sql = "UPDATE TaiKhoan SET ten = ?  WHERE sodienthoai = ?";
                PreparedStatement preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, tenMoi);
                preparedStatement.setString(2, sodienthoai);
                preparedStatement.executeUpdate();
                Toast.makeText(context, "Cập nhật tên thành công", Toast.LENGTH_SHORT).show();
                preparedStatement.close();
                con.close();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "updateTenTaiKhoan: " + e.getMessage());
        }
    }

}
