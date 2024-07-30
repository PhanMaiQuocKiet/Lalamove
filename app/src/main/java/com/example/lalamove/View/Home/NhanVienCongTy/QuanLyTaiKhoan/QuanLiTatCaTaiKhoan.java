package com.example.lalamove.View.Home.NhanVienCongTy.QuanLyTaiKhoan;

public class QuanLiTatCaTaiKhoan {
    private String ten;
    private String matkhau;
    private String loaitaikhoan;

    public QuanLiTatCaTaiKhoan(String ten, String matkhau, String loaitaikhoan) {
        this.ten = ten;
        this.matkhau = matkhau;
        this.loaitaikhoan = loaitaikhoan;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getLoaitaikhoan() {
        return loaitaikhoan;
    }

    public void setLoaitaikhoan(String loaitaikhoan) {
        this.loaitaikhoan = loaitaikhoan;
    }
}
