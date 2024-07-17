package com.example.lalamove.ListLoaiXe;

public class Vehicle {
    private int icon;
    private String name;
    private String description;
    private String dimensions;

    public Vehicle(int icon, String name, String description, String dimensions) {
        this.icon = icon;
        this.name = name;
        this.description = description;
        this.dimensions = dimensions;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getDimensions() {
        return dimensions;
    }
}