package com.example.lalamove.View.Home.KhachHang.TXYT;

public class TXyeuthich {
    private String driverPhone;
    private String customerPhone;
    private int sodon;

    public TXyeuthich(String driverPhone, String customerPhone, int rating) {
        this.driverPhone = driverPhone;
        this.customerPhone = customerPhone;
        this.sodon = rating;
    }

    // Getters
    public String getDriverPhone() { return driverPhone; }
    public String getCustomerPhone() { return customerPhone; }
    public int getRating() { return sodon; }
}
