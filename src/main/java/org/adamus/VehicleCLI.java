/**
 * @author Mikolaj Jan Adamus
 * @id 12030638
 */
package org.adamus;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VehicleCLI {

	public static void main(String[] args) {
		try {


			List<String> input = new ArrayList<>(Arrays.stream(args).toList());

			if(input.size() < 2) {
				throw new RuntimeException("Error: Invalid parameter.");
				//System.err.println("Error: Invalid parameter.");
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

			if(command.equalsIgnoreCase("show")) { // show command
				if(input.size() != 0) {
					try {
						mgt.getVehicle(Integer.parseInt(input.get(0)));
					} catch (Exception e) {

					}
				} else {
					mgt.getVehicles();
				}
			} else if (command.equalsIgnoreCase("add")) { // add command
				if(input.size() == 7 && input.get(0).equalsIgnoreCase("car")) {
					if(!(Vehicle.isNumeric(input.get(1)) &&
							Vehicle.isNumeric(input.get(4)) &&
							Vehicle.isNumeric(input.get(5)) &&
							Vehicle.isNumeric(input.get(6)))
					) {
						throw new RuntimeException("Error: Invalid parameter.");
					}
					mgt.addVehicle(new Car(
							Integer.parseInt(input.get(1)), // id
							input.get(2), // brand
							input.get(3), // model
							Integer.parseInt(input.get(4)), // year
							Double.parseDouble(input.get(5)), // base price
							Integer.parseInt(input.get(6)) // inspection year
					));
				} else if (input.size() == 6 && input.get(0).equalsIgnoreCase("truck")) {
					if(!(Vehicle.isNumeric(input.get(1)) &&
							Vehicle.isNumeric(input.get(4)) &&
							Vehicle.isNumeric(input.get(5)))
					) {
						throw new RuntimeException("Error: Invalid parameter.");
					}
					mgt.addVehicle(new Truck(
							Integer.parseInt(input.get(1)), // id
							input.get(2), // brand
							input.get(3), // model
							Integer.parseInt(input.get(4)), // year
							Double.parseDouble(input.get(5)) // base price
					));
				} else {
					throw new RuntimeException("Error: Invalid parameter.");
				}
			} else if (command.equalsIgnoreCase("del")) { // delete command
				mgt.deleteVehicle(Integer.parseInt(input.get(0)));
			} else if (command.equalsIgnoreCase("meanprice")) {// meanprice command
				mgt.meanPrice();
			} else if (command.equalsIgnoreCase("count")) {// count command
				if (input.isEmpty()) {
					mgt.countVehicles();
				} else if (input.get(0).equalsIgnoreCase("car")) {
					mgt.countCars();
				} else if (input.get(0).equalsIgnoreCase("truck")) {
					mgt.countTrucks();
				} else {
					System.err.println("what do you want to count?");
				}
			} else if (command.equalsIgnoreCase("oldest")) {// oldest command
				mgt.oldestVehicle();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}