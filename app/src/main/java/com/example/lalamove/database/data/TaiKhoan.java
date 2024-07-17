package com.example.lalamove.database.data;

public class TaiKhoan {

    public TaiKhoan(String sodienthoai, String gmail, String matkhau, String vaiTro) {
        this.sodienthoai = sodienthoai;
        this.gmail = gmail;
        this.matkhau = matkhau;
       this.VaiTro = vaiTro;
    }

    private String sodienthoai;
    private String gmail;

    private String matkhau;
    private String VaiTro ;

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }

    public String getVaiTro() {
        return VaiTro;
    }

    public void setVaiTro(String vaiTro) {
        VaiTro = vaiTro;
    }



}
