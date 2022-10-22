package org.adamus;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Objects;

import static java.lang.Math.abs;

public abstract class Vehicle implements Serializable {
    private final String brand;
    private final String model;
    private final int year;
    private final int vehicle_id;
    private final double base_price;


    public Vehicle(int id, String brand, String model, int year, double base_price) {
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
        this.vehicle_id = id;
        this.base_price = base_price;
    }

    public static DecimalFormat getDecimalFormat() {
        return new DecimalFormat("0.00");
    }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
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
    public double getPrice() { return getDiscount(); }

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

    @Override
    public int hashCode() {
        return Objects.hash(brand, model, year, base_price);
    }
}