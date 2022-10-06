package org.adamus;

public class Main {
    public static void main(String[] args) {
        Car c = new Car("Tesla", "R1", 2019, 1, 120);

        SerializedVehicleDAO dao = new SerializedVehicleDAO("All.ser");

        dao.saveVehicle(c);
        dao.getVehicleList().forEach(System.out::println);
    }
}