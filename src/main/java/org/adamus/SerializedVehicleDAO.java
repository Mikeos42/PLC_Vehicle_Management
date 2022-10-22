package org.adamus;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SerializedVehicleDAO implements VehicleDAO, Serializable {

    private final String fileName;
    private List<Vehicle> allVehicles;


    public SerializedVehicleDAO(String fileName) {
        this.fileName = fileName;
    }

    private void serializeVehicle(List<Vehicle> vehicles) {
        try {
            FileOutputStream outstream = new FileOutputStream(fileName);
            ObjectOutputStream writer = new ObjectOutputStream(outstream);
            writer.writeObject(vehicles);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        File file = new File(fileName);
//        if(file.exists()) file.delete();
//
//        try {
//            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(fileName, true));
//            writer.writeObject(vehicles);
//            writer.close();
//        } catch (IOException e) {
//            System.err.println("Error during serialization: " + e.getMessage());
//            System.exit(1);
//        }
//        fileExists = true;
    }

    @SuppressWarnings("unchecked")
    private List<Vehicle> deserializeVehicles() {
        if(!new File(fileName).exists()) return null;
        List<Vehicle> vehicles = null;
        try {
            FileInputStream instream = new FileInputStream(fileName);
            ObjectInputStream reader = new ObjectInputStream(instream);

            vehicles = (List<Vehicle>) reader.readObject();
            reader.close();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
//        if(!fileExists) return null;
//        File file = new File(fileName);
//
//        List<Vehicle> vehicles = null;
//        try {
//            ObjectInputStream reader = new ObjectInputStream(new FileInputStream(fileName));
//            vehicles = (List<Vehicle>) reader.readObject();
//            reader.close();
//        } catch (IOException | ClassNotFoundException e) {
//            System.err.println("Error during deserialization: " + e.getMessage());
//            System.exit(1);
//        }
//        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleList() {
        return deserializeVehicles();
    }

    @Override
    public Vehicle getVehicle(int id) {
        allVehicles = deserializeVehicles();
        for (Vehicle vehicle : allVehicles) {
            if(vehicle.getVehicle_id() == id) {
                return vehicle;
            }
        }
        throw new RuntimeException("Error: Vehicle not found. (id="+id+")");
    }

    @Override
    public void saveVehicle(Vehicle vehicle) {
        allVehicles = deserializeVehicles();
        if(allVehicles == null) {
            allVehicles = new ArrayList<>();
        }
        for (Vehicle v : allVehicles) {
            if(v.getVehicle_id() == vehicle.getVehicle_id()) {
                throw new RuntimeException("Error: Vehicle already exists. (id="+vehicle.getVehicle_id()+")");
            }
        }
        allVehicles.add(vehicle);
        serializeVehicle(allVehicles);
    }

    @Override
    public void deleteVehicle(int id) {
        allVehicles = deserializeVehicles();
        if(allVehicles == null) {
            throw new RuntimeException("Error: Vehicle not found. (id="+id+")");
        }
        for (Vehicle vehicle : allVehicles) {
            if(vehicle.getVehicle_id() == id) {
                allVehicles.remove(vehicle);
                serializeVehicle(allVehicles);
                return;
            }
        }
        serializeVehicle(allVehicles);
        throw new RuntimeException("Error: Vehicle not found. (id="+id+")");
    }
}