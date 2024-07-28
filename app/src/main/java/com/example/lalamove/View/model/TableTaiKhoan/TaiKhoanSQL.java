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

public class TaiKhoanSQL {
    Connection con;
    ConnectionHelper connectionHelper;

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

    // Cập nhật tên tài khoản
    public void updateTenTaiKhoan(String phone, String newTen, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "UPDATE TaiKhoan SET ten = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newTen);
                preparedStatement.setString(2, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Toast.makeText(context, "Tên đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài khoản để cập nhật", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TaiKhoanSQL", "Lỗi khi cập nhật tên tài khoản: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật tên tài khoản", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e("TaiKhoanSQL", "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    // Cập nhật số điện thoại
    public void updateSdtTaiKhoan(String oldSdt, String newSdt, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "UPDATE TaiKhoan SET sodienthoai = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newSdt);
                preparedStatement.setString(2, oldSdt);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Toast.makeText(context, "Số điện thoại đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài khoản để cập nhật", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TaiKhoanSQL", "Lỗi khi cập nhật số điện thoại: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật số điện thoại", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e("TaiKhoanSQL", "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    // Cập nhật Gmail
    public void updateGmailTaiKhoan(String phone, String newGmail, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "UPDATE TaiKhoan SET gmail = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newGmail);
                preparedStatement.setString(2, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Toast.makeText(context, "Gmail đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài khoản để cập nhật", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e("TaiKhoanSQL", "Lỗi khi cập nhật Gmail: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật Gmail", Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e("TaiKhoanSQL", "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
}
