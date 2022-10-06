package org.adamus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO, Serializable {

    private String fileName;
    private List<Vehicle> allVehicles;


    public SerializedVehicleDAO(String fileName) {
        this.fileName = fileName;
    }

    private void serialize() {
        try {
            FileOutputStream file = new FileOutputStream(fileName);
            ObjectOutputStream out = new ObjectOutputStream(file);

            // object persists here

            out.writeObject(allVehicles);

            out.close();
            file.close();
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e);
        }
    }

    private void deserialize() {
        try {
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream in = new ObjectInputStream(file);

            // object persists here

            allVehicles = (List<Vehicle>) in.readObject();

            in.close();
            file.close();
        } catch (IOException e) {
            System.err.println("Error during serialization: " + e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Vehicle> getVehicleList() {
        deserialize();
        return allVehicles;
    }

    @Override
    public Vehicle getVehicle(int id) {
        deserialize();
        for (Vehicle vehicle : allVehicles) {
            if(vehicle.getVehicle_id() == id) {
                return vehicle;
            }
        }
        return null;
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        deserialize();
        if(allVehicles == null) {
            allVehicles = new ArrayList<>();
        }
        allVehicles.add(vehicle);
        serialize();
    }

    @Override
    public void deleteVehicle(int id) {
        deserialize();
        for (Vehicle vehicle : allVehicles) {
            if(vehicle.getVehicle_id() == id) {
                allVehicles.remove(vehicle);
                break;
            }
        }
        serialize();
    }
}