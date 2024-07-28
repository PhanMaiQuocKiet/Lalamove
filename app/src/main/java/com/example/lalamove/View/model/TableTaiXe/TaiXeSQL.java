package com.example.lalamove.View.model.TableTaiXe;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.lalamove.database.data.ConnectionHelper;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class TaiXeSQL {
    private Connection con;
    private ConnectionHelper connectionHelper;

    // Thực hiện việc đăng ký tài xế
    public void sp_insert_TaiKhoanTaiXe(String sodienthoai, String ten, String matkhau, String loaitaikhoan, String hinhdaidien,
                                        String sodienthoaitaixe, String bienso, String maphuongtien, String mavidientu, Context context) {
        CallableStatement callableStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "{call sp_insert_TaiKhoan_TaiXe(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
                callableStatement = con.prepareCall(sql);
                callableStatement.setString(1, sodienthoai);
                callableStatement.setString(2, ten);
                callableStatement.setString(3, matkhau);
                callableStatement.setString(4, loaitaikhoan);
                callableStatement.setString(5, hinhdaidien);
                callableStatement.setString(6, sodienthoaitaixe);
                callableStatement.setString(7, bienso);
                callableStatement.setString(8, maphuongtien);
                callableStatement.setString(9, mavidientu);

                callableStatement.execute();
                Toast.makeText(context, "Đăng ký tài xế thành công !!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(context, "Có lỗi xảy ra, thử lại sau", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "sp_insert_TaiKhoanTaiXe: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi đăng ký tài xế", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (callableStatement != null) {
                    callableStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }

    // Kiểm tra xem tài khoản đã tồn tại chưa
    public boolean isTaiKhoanTonTai(String sodienthoaiDK, Context context) {
        boolean flag = false;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "SELECT sodienthoai FROM TaiKhoan WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, sodienthoaiDK);

                rs = preparedStatement.executeQuery();

                if (rs.next()) {
                    flag = true;
                }
            } else {
                Toast.makeText(context, "Lỗi không truy xuất được dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi kiểm tra tài khoản: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi kiểm tra tài khoản", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (rs != null) {
                    rs.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
        return flag;
    }
    // Cập nhật tên
    public void updateName(String sodienthoai, String ten, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                String sql = "UPDATE TaiKhoan SET ten = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, ten);
                preparedStatement.setString(2, sodienthoai);

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
            Log.e(TAG, "Lỗi khi cập nhật tên: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật tên", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
    public void updatePhone(String oldPhone, String newPhone, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                // Cập nhật số điện thoại
                String sql = "UPDATE TaiKhoan SET sodienthoai = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newPhone);
                preparedStatement.setString(2, oldPhone);

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
            Log.e(TAG, "Lỗi khi cập nhật số điện thoại: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật số điện thoại", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
    public void updateChucVu(String phone, String newChucVu, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                // Cập nhật chức vụ
                String sql = "UPDATE TaiKhoan SET chucvu = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newChucVu);
                preparedStatement.setString(2, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Toast.makeText(context, "Chức vụ đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài khoản để cập nhật", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật chức vụ: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật chức vụ", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
    // Cập nhật mã phương tiện
    public void updateMaPhuongTien(String phone, String newMaPhuongTien, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                // Cập nhật mã phương tiện
                String sql = "UPDATE TaiKhoan SET maphuongtien = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newMaPhuongTien);
                preparedStatement.setString(2, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Toast.makeText(context, "Mã phương tiện đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài khoản để cập nhật", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật mã phương tiện: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật mã phương tiện", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
    // Cập nhật biển số phương tiện
    public void updateBienSoPhuongTien(String phone, String newBienSoPhuongTien, Context context) {
        PreparedStatement preparedStatement = null;
        try {
            connectionHelper = new ConnectionHelper();
            con = connectionHelper.connectionClass();

            if (con != null) {
                // Cập nhật biển số phương tiện
                String sql = "UPDATE TaiKhoan SET biensophuongtien = ? WHERE sodienthoai = ?";
                preparedStatement = con.prepareStatement(sql);
                preparedStatement.setString(1, newBienSoPhuongTien);
                preparedStatement.setString(2, phone);

                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    Toast.makeText(context, "Biển số phương tiện đã được cập nhật", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Không tìm thấy tài khoản để cập nhật", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(context, "Không thể kết nối đến cơ sở dữ liệu", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Log.e(TAG, "Lỗi khi cập nhật biển số phương tiện: " + e.getMessage());
            Toast.makeText(context, "Có lỗi xảy ra khi cập nhật biển số phương tiện", Toast.LENGTH_SHORT).show();
        } finally {
            // Đảm bảo tài nguyên được đóng đúng cách
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
                Log.e(TAG, "Lỗi khi đóng tài nguyên: " + e.getMessage());
            }
        }
    }
}
