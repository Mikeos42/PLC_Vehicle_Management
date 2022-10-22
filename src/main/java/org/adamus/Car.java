package org.adamus;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Calendar;

public class Car extends Vehicle {

    private int inspection_year;
    private static final long serialVersionUID = 1L;

    public void setInspection_year(int inspection_year) {
        this.inspection_year = inspection_year;
    }

    public Car(int id, String brand, String model, int year, double base_price, int inspection_year) {
        super(id, brand, model, year, base_price);
        if(inspection_year > 2022) {
            throw new RuntimeException("Error: Inspection year invalid.");
        }
        this.inspection_year = inspection_year;
    }

    @Override
    public double getDiscount() { // lol help
/*
        double discounted = getBase_price()
                - (getBase_price() * getAge() * 0.05)
                - (getBase_price() * (Calendar.getInstance().get(Calendar.YEAR) - inspection_year) * 0.02);
*/

        double ageDiscount = getAge() * 5;
        double inspectionDiscount = (Calendar.getInstance().get(Calendar.YEAR) - inspection_year) * 2;
        double discount = (100 - ageDiscount - inspectionDiscount) / 100; // ex. 0.9

        if(discount < 0.85) {
            return getBase_price() * 0.85;
        } else {
            return getBase_price() * discount;
        }
    }

    @Override
    public String toString() {
        DecimalFormat df = Vehicle.getDecimalFormat();
        return "Type:       Car\n" +
                "Id:         " + getVehicle_id() + "\n" +
                "Brand:      " + getBrand() + "\n" +
                "Model:      " + getModel() + "\n" +
                "Year:       " + getYear() + "\n" +
                "Inspection: " + inspection_year + "\n" +
                "Base price: " + df.format(getBase_price()) + "\n" +
                "Price:      " + df.format(getPrice()) + "\n";
    }
}