package org.adamus;

import java.util.Comparator;
import java.util.List;

public class VehicleManagement {
    private final SerializedVehicleDAO dao;

    public VehicleManagement(SerializedVehicleDAO dao) {
        this.dao = dao;
    }

    public void getVehicles() {
        dao.getVehicleList().forEach(System.out::println);
    }

    public void getVehicle(int id) {
        System.out.println(dao.getVehicle(id));
    }

    public void addVehicle(Vehicle vehicle) {
        dao.saveVehicle(vehicle);
    }

    public void deleteVehicle(Vehicle vehicle) {
        dao.deleteVehicle(vehicle.getVehicle_id());
    }

    public void deleteVehicle(int id) {
        dao.deleteVehicle(id);
    }

    public void countVehicles() {
        System.out.println(dao.getVehicleList().size());
    }

    public void countCars() {
        System.out.println(dao.getVehicleList().stream().filter(x -> x instanceof Car).count());
    }

    public void countTrucks() {
        System.out.println(dao.getVehicleList().stream().filter(x -> x instanceof Truck).count());
    }

    public void meanPrice() {
        System.out.println(Vehicle.getDecimalFormat().format(dao.getVehicleList().stream().mapToDouble(Vehicle::getPrice).sum() / dao.getVehicleList().size()));
    }

    public void oldestVehicle() {
        int age = dao.getVehicleList().stream().max(Comparator.comparingInt(Vehicle::getAge)).get().getAge();
        dao.getVehicleList().stream().filter(o -> o.getAge() == age).forEach(x -> System.out.println("Id: " + x.getVehicle_id()));
    }
}