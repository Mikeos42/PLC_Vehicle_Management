package org.adamus;

public class Main {
    public static void main(String[] args) {
        Car c = new Car("Tesla", "R1", 2019, 1, 120);
        Car d = new Car("Woop", "Slo", 2000, 2, 430);
        Car d2 = new Car("Waa", "grr", 2000, 3, 431);

        SerializedVehicleDAO dao = new SerializedVehicleDAO("All.txt");
        VehicleManagement mgt = new VehicleManagement(dao);

        mgt.oldestVehicle();
    }
}