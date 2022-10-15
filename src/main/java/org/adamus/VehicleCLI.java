package org.adamus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleCLI {

	public static void main(String[] args) {
		List<String> input = new ArrayList<>(Arrays.stream(args).toList());

		if(input.size() < 2) {
			System.err.println("Usage: java VehicleCLI <file> <command>");
			return;
		}


		String file = input.remove(0);
		String command = input.remove(0);

/*
		if(!new File(file).exists()) {
			try {
				new File(file).createNewFile();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}

 */
		SerializedVehicleDAO dao = new SerializedVehicleDAO(file);
		VehicleManagement mgt = new VehicleManagement(dao);

		if(command.equalsIgnoreCase("show")) {
			mgt.getVehicles();
		} else if (command.equalsIgnoreCase("add")) {
			if(input.size() != 7) {
				System.err.println("Not right amount of parameters for creating vehicle");
				return;
			}
			if(input.get(0).equalsIgnoreCase("car")) {
				mgt.addVehicle(new Car(
						Integer.parseInt(input.get(1)), // id
						input.get(2), // brand
						input.get(3), // model
						Integer.parseInt(input.get(4)), // year
						Double.parseDouble(input.get(5)), // base price
						Integer.parseInt(input.get(6)) // inspection year
				));
			} else if (input.get(0).equalsIgnoreCase("truck")) {
				mgt.addVehicle(new Truck(
						Integer.parseInt(input.get(1)), // id
						input.get(2), // brand
						input.get(3), // model
						Integer.parseInt(input.get(4)), // year
						Double.parseDouble(input.get(5)) // base price
				));
			} else {
				System.err.println("Vehicle not valid");
			}
		}
	}
	
}