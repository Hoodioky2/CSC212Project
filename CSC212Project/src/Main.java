import java.util.*;

public class Main {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);

		RideSharingSystem system = new RideSharingSystem();
		//loads the initial riders, drivers , rides into the system
		system.loadDriversFromCSV("drivers_30.csv");
		system.loadRidersFromCSV("riders_100.csv");
		system.loadRidesFromCSV("rides_40.csv");

		int userSelection;
		boolean turnof = false;
		
		// keeps displaying the menu until the user choose to exit
		do {

			System.out.println("1. List all riders");
			System.out.println("2. Search for riders by home city");
			System.out.println("3. Search for riders by name");
			System.out.println("4. Add a rider");
			System.out.println("5. List all drivers");
			System.out.println("6. Search for drivers by vehicle type");
			System.out.println("7. Add a driver");
			System.out.println("8. List all rides alphabetically");
			System.out.println("9. Search for rides by pickup location");
			System.out.println("10.Search for rides by rider name");
			System.out.println("11.List the participants of a shared ride by pickup location");
			System.out.println("12.Add a ride");
			System.out.println("0.Exit");
			System.out.println(
					"=============================================================================================");
			System.out.print("Chose your operation: ");
			//handles invalids menu selections
			try {
				userSelection = input.nextInt();
				input.nextLine();
				
			}catch(Exception e) {
				userSelection = -1;
				input.nextLine();
			}
			// executes the operation selected by the user
			switch (userSelection) {
			case 1:
				if (system.getAllRiders().empty()) {
					System.out.println("There is no riders");
				} else {
					System.out.println(system.getAllRiders());
				}

				break;
			case 2:
				System.out.print("Enter the home city: ");
				String homecity = input.nextLine();
				LinkedList<IRider> ridersByHomecity = system.searchRidersByHomeCity(homecity);
				if (ridersByHomecity.empty()) {
					System.out.println("There is no riders with the home city you enterd");
				} else {
					System.out.println(system.searchRidersByHomeCity(homecity));
				}

				break;

			case 3:
				System.out.print("Enter the name:");
				String name = input.nextLine();
				LinkedList<IRider> riderByName = system.searchRidersByName(name);
				if (riderByName.empty()) {
					System.out.println("There is no riders with this name");
				} else {
					System.out.println(riderByName);

				}

				break;
			
			case 4:
				
				// collects the rider information and validates it before adding
				System.out.print("Enter the rider name: ");
				name = input.nextLine();
				System.out.print("Enter the phone number: ");
				String phoneNumber = input.nextLine();
				System.out.print("Enter the rider ID:");
				int riderId = input.nextInt();
				input.nextLine();
				System.out.print("Enter the email");
				String email = input.nextLine();
				System.out.print("Enter the home city");
				String homeCity = input.nextLine();

				IRider rider;
				try {
					 rider = new Rider(name, phoneNumber, riderId, email, homeCity);
					if (system.addRider(rider)) {
						System.out.println("Rider added successfully");
					} else {
						System.out.println("there is another rider with the same ID or Email");
					}
				} catch (IllegalArgumentException e) {
					System.out.println("Invalid phone number");

				}
				break;

			case 5:
				LinkedList<IDriver> allDrivers = system.getAllDrivers();
				if (allDrivers.empty()) {
					System.out.println("There is no drivers");
				} else {
					System.out.println(system.getAllDrivers());
				}

				break;

			case 6:
				System.out.print("Enter the vehicle type(SEDAN,LUXURY_SEDAN,VAN,SUV):");
				String vehicleTypeStr = input.nextLine();
				try {
					VehicleType vehicleType = VehicleType.valueOf(vehicleTypeStr);

					LinkedList<IDriver> driversByVehicleType = system.searchDriversByVehicleType(vehicleType);
					if (driversByVehicleType.empty()) {
						System.out.println("There is no drivers with this vehicle type");
					} else {
						System.out.println(system.searchDriversByVehicleType(vehicleType));
					}

				} catch (IllegalArgumentException e) {
					System.out.println("Invalid vehicle type");
				}
				break;

			case 7:
				/*
				 * collects the driver information
				 * and validates the vehicle type and plate
				 */
				System.out.print("Enter driver name: ");
				name = input.nextLine();

				System.out.print("Enter driver phone number: ");
				phoneNumber = input.nextLine();

				System.out.print("Enter driver ID: ");
				int driverId = input.nextInt();
				input.nextLine();

				System.out.print("Enter driver vehicle plate: ");
				String vehiclePlate = input.nextLine();

				System.out.print("Enter driver vehicle type: ");
				vehicleTypeStr = input.nextLine();

				try {
					VehicleType vehicleType = VehicleType.valueOf(vehicleTypeStr);

					Driver driver = new Driver(name, phoneNumber, driverId, vehiclePlate, vehicleType);
					if (system.addDriver(driver)) {
						System.out.println("Driver added successfully");
					} else {
						System.out.println("Another driver has the phone number or vehicle plate ");
					}

				} catch (IllegalArgumentException e) {
					System.out.println("Invalid vehicle plate or phone number");
				}

				break;

			case 8:
				LinkedList<IRide> allRide = system.getAllRidesAlphabetically();
				if (allRide.empty()) {
					System.out.println("There is no rides");

				} else {
					System.out.println(system.getAllRidesAlphabetically());
				}

				break;

			case 9:
				System.out.print("Enter pickup location: ");
				String pickupLocation = input.nextLine();

				LinkedList<IRide> rideByPickupLocation = system.searchRidesByPickupLocation(pickupLocation);
				if (rideByPickupLocation.empty()) {
					System.out.println("There is no rides with this pickup location");
				} else {
					System.out.println(rideByPickupLocation);
				}

				break;

			case 10:
				System.out.print("Enter rider name: ");
				name = input.nextLine();

				LinkedList<IRide> rideByriderName = system.searchRidesByRiderName(name);
				if (rideByriderName.empty()) {
					System.out.println("There is no riders with the name you enterd");
				} else {
					System.out.println(rideByriderName);
				}
				break;

			case 11:
				System.out.print("Enter the pickup location:");
				pickupLocation = input.nextLine();
				LinkedList<IRider> participants = system.getSharedRideParticipants(pickupLocation);

				if (participants.empty()) {
					System.out.println("There is no rides with the name you entered");
				}
				else {
					System.out.println(participants);
				}

				break;
				
			case 12:
				
				// schedules a private or shared ride based on the user's selection
				System.out.print("Do you want to add private or shared ride? :  ");
				String rideType = input.nextLine();

				System.out.print("Enter pickup location: ");
				pickupLocation = input.nextLine();

				System.out.print("Enter dropoff location: ");
				String dropoffLocation = input.nextLine();

				System.out.print("Enter pickup time by the order Y\\M\\D  H:Min: ");
				int year = input.nextInt();
				int month = input.nextInt();
				int day = input.nextInt();
				int hour = input.nextInt();
				int minute = input.nextInt();
				input.nextLine();
				DateTime pickupTime = new DateTime(year, month, day, hour, minute);

				System.out.print("Enter dropoff time by the order Y\\M\\D  H:Min: ");
				year = input.nextInt();
				month = input.nextInt();
				day = input.nextInt();
				hour = input.nextInt();
				minute = input.nextInt();
				input.nextLine();
				DateTime dropoffTime = new DateTime(year, month, day, hour, minute);

				System.out.print("Enter driver ID: ");
				driverId = input.nextInt();
				input.nextLine();
				

				if (rideType.trim().toLowerCase().equalsIgnoreCase("private")) {
					System.out.print("Enter rider ID: ");
					riderId = input.nextInt();
					input.nextLine();
					rider = system.searchRiderById(riderId);

					if (system.schedulePrivateRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation, riderId,
							driverId)) {
						System.out.println("Ride added successfully");
					}else {
						System.out.println("Invalid inputs ");
					}
					
						
				}
				else if(rideType.trim().toLowerCase().equalsIgnoreCase("shared")) {
					
					System.out.print("How many riders do you want to add into the shared ride: ");
					int riderNum = input.nextInt();
					input.nextLine();
					int riderIds [] = new int[riderNum];
					
					for(int i=0; i<riderNum; i++) {
						System.out.print("Enter the rider ID: ");
						riderId = input.nextInt();
						input.nextLine();
						riderIds[i] = riderId;
					}
					
					if(system.scheduleSharedRide(pickupLocation, pickupTime, dropoffTime, dropoffLocation, riderIds, driverId)) {
						System.out.println("Ride added successfully");
					}else {
						System.out.println("Invalid inputs ");

					}
				
					
				}else {
					System.out.println("Invalid ride type");
				}
				
				break;
				
			case 0 :
				turnof = true;
				break;
				
			default:
				System.out.println("Invalid operation");
				break;

			}
			
			System.out.println(
					"=============================================================================================");
		} while (!turnof);
		
		input.close();

	}

}
