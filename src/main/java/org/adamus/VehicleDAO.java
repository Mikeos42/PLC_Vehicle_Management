package org.adamus;

import java.util.List;

public interface VehicleDAO {

	List<Vehicle> getVehicleList();

	Vehicle getVehicle(int id);

	void saveVehicle(Vehicle vehicle);

	void deleteVehicle(int id);
}