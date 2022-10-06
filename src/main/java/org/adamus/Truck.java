package org.adamus;

import java.util.Calendar;

public class Truck extends Vehicle {

    public Truck(String brand, String model, int year, int vehicle_id, double base_price) {
        super(brand, model, year, vehicle_id, base_price);
    }

    @Override
    public double getDiscount() { // lol help
        double discount = 1 - (getAge() * 0.05);

        if(discount < 0.80) {
            return getBase_price() * 0.80;
        } else {
            return getBase_price() * discount;
        }
    }
}