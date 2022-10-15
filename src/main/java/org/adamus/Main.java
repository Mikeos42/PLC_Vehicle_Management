package org.adamus;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {
        Car c = new Car(1,"Tesla", "R1", 2021, 120);
        c.setInspection_year(2021);
        Car d = new Car(2,"Woop", "Slo", 2000, 430);
        d.setInspection_year(2015);
        Car d2 = new Car(3,"Waa", "grr", 2000, 431);
        d2.setInspection_year(2018);


        SerializedVehicleDAO dao = new SerializedVehicleDAO("All");
        VehicleManagement mgt = new VehicleManagement(dao);

        mgt.addVehicle(c);
        mgt.addVehicle(d);
        mgt.addVehicle(d2);
    }
}