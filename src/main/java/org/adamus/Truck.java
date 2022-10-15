package org.adamus;

import java.util.Calendar;

public class Truck extends Vehicle {

    private static final long serialVersionUID = 2L;

    public Truck(int id, String brand, String model, int year, double base_price) {
        super(id, brand, model, year, base_price);
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