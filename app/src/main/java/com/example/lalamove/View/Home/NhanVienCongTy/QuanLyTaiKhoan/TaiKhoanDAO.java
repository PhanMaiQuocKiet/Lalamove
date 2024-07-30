package com.example.lalamove.View.Home.NhanVienCongTy.QuanLyTaiKhoan;

import android.content.Context;
import android.util.Log;

import com.example.lalamove.database.data.ConnectionHelper;
import com.example.lalamove.View.Home.NhanVienCongTy.QuanLyTaiKhoan.QuanLiTatCaTaiKhoan;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO {

    private ConnectionHelper connectionHelper;

    public TaiKhoanDAO(Context context) {
        connectionHelper = new ConnectionHelper();
    }

    public List<QuanLiTatCaTaiKhoan> getAllTaiKhoan() {
        List<QuanLiTatCaTaiKhoan> taiKhoanList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "SELECT ten, matkhau, loaitaikhoan FROM TaiKhoan";
                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String ten = rs.getString("ten");
                    String matkhau = rs.getString("matkhau");
                    String loaitaikhoan = rs.getString("loaitaikhoan");
                    taiKhoanList.add(new QuanLiTatCaTaiKhoan(ten, matkhau, loaitaikhoan));
                }
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error fetching accounts: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e("ERROR", "Error closing resources: " + e.getMessage());
            }
        }
        return taiKhoanList;
    }

    public void deleteTaiKhoan(String ten) {
        Connection connection = null;
        CallableStatement stmt = null;
        try {
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "{call sp_DeleteTaiKhoan(?)}";
                stmt = connection.prepareCall(query);
                stmt.setString(1, ten);
                stmt.execute();
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error deleting account: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e("ERROR", "Error closing resources: " + e.getMessage());
            }
        }
    }
    public void updateTaiKhoan(String oldTen, QuanLiTatCaTaiKhoan taiKhoan) {
        Connection connection = null;
        CallableStatement stmt = null;
        try {
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "{call sp_UpdateTaiKhoan(?, ?, ?, ?)}";
                stmt = connection.prepareCall(query);
                stmt.setString(1, oldTen);
                stmt.setString(2, taiKhoan.getTen());
                stmt.setString(3, taiKhoan.getMatkhau());
                stmt.setString(4, taiKhoan.getLoaitaikhoan());
                stmt.execute();
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error updating account: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e("ERROR", "Error closing resources: " + e.getMessage());
            }
        }
    }
    /*Search tài khoản*/

    public List<QuanLiTatCaTaiKhoan> searchTaiKhoanByRole(String keyword, String role) {
        List<QuanLiTatCaTaiKhoan> taiKhoanList = new ArrayList<>();
        Connection connection = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            connection = connectionHelper.connectionClass();
            if (connection != null) {
                String query = "SELECT ten, matkhau, loaitaikhoan FROM TaiKhoan WHERE ten LIKE '%" + keyword + "%'";
                if (!role.equals("All")) {
                    query += " AND loaitaikhoan = '" + role + "'";
                }
                stmt = connection.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String ten = rs.getString("ten");
                    String matkhau = rs.getString("matkhau");
                    String loaitaikhoan = rs.getString("loaitaikhoan");
                    taiKhoanList.add(new QuanLiTatCaTaiKhoan(ten, matkhau, loaitaikhoan));
                }
            }
        } catch (Exception e) {
            Log.e("ERROR", "Error searching accounts: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (connection != null) connection.close();
            } catch (Exception e) {
                Log.e("ERROR", "Error closing resources: " + e.getMessage());
            }
        }
        return taiKhoanList;
    }
}