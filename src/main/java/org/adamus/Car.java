package org.adamus;

import java.util.Calendar;

public class Car extends Vehicle {

    private int inspection_year;

    public Car(String brand, String model, int year, int vehicle_id, double base_price) {
        super(brand, model, year, vehicle_id, base_price);
    }

    @Override
    public double getDiscount() { // lol help
/*
        double discounted = getBase_price()
                - (getBase_price() * getAge() * 0.05)
                - (getBase_price() * (Calendar.getInstance().get(Calendar.YEAR) - inspection_year) * 0.02);
*/

        double ageDiscount = getAge() * 0.05;
        double inspectionDiscount = (Calendar.getInstance().get(Calendar.YEAR) - inspection_year) * 0.02;
        double discount = 1 - ageDiscount - inspectionDiscount; // ex. 0.9

        if(discount < 0.85) {
            return getBase_price() * 0.85;
        } else {
            return getBase_price() * discount;
        }
    }
}