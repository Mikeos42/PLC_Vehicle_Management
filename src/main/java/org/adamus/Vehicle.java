package org.adamus;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Vehicle implements Serializable {
    private String brand, model;
    private int year, vehicle_id;
    private double base_price;


    public Vehicle(String brand, String model, int year, int vehicle_id, double base_price) {
        if(brand == null || model == null || brand.isEmpty() || model.isEmpty()) {
            throw new IllegalArgumentException("Error: Invalid parameter.");
        }
        if(year > Calendar.getInstance().get(Calendar.YEAR) || year < 1886) { // cars were invented
            throw new IllegalArgumentException("Error: Year built invalid.");
        }
        if(base_price <= 0) {
            throw new IllegalArgumentException("Error: Base price invalid.");
        }
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.vehicle_id = vehicle_id;
        this.base_price = base_price;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getVehicle_id() {
        return vehicle_id;
    }

    public double getBase_price() {
        return base_price;
    }

    public int getAge() { return Calendar.getInstance().get(Calendar.YEAR) - year; }

    public abstract double getDiscount();
    public double getPrice() { return base_price - getDiscount(); }

    @Override
    public String toString() {
        return "Vehicle{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", year=" + year +
                ", vehicle_id=" + vehicle_id +
                ", base_price=" + base_price +
                '}';
    }
}