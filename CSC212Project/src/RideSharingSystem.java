import java.io.*;
import java.util.*;

public class RideSharingSystem implements IRideSharingSystem {
	private RiderList riders;
	private DriverList drivers;
	private RideList rides;

	public RideSharingSystem() {
		riders = new RiderList();
		drivers = new DriverList();
		rides = new RideList();
	}

	@Override
	public boolean loadRidersFromCSV(String ridersFilePath) {

		try {
			File f = new File(ridersFilePath);
			Scanner input = new Scanner(f);

			while (input.hasNextLine()) {
				int id = input.nextInt();
				String name = input.next();
				String email = input.next();
				String phoneNumber = input.next();
				String homeCity = input.next();
				riders.add(new Rider(name, phoneNumber, id, email, homeCity));

			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	
	@Override
	public boolean loadDriversFromCSV(String driversFilePath) {
		try {
			File f = new File(driversFilePath);
			Scanner input = new Scanner(f);

			while (input.hasNextLine()) {
				int id = input.nextInt();
				String name = input.next();
				String phoneNumber = input.next();
				String vehiclePlate = input.next();
				String vehicleTypetext = input.next();
				VehicleType type = VehicleType.valueOf(vehicleTypetext); 
				drivers.add(new Driver(name, phoneNumber, id,vehiclePlate,type));

			}
			return true;
			
		} catch (Exception e) {
			return false;
		}
	}
	
	
	
	@Override
	public boolean loadRidesFromCSV(String ridesFilePath) {
		try {
			File f = new File(ridesFilePath);
			Scanner input = new Scanner(f);
			input.useDelimiter(", | \\r?\\n");
			int rideId =1;
			
			while(input.hasNext()) {
				String rideType = input.next();
				String pickupLocation = input.next();
				
				String [] pTime = input.next().split("[/ ;]");
				
				IDateTime pickupTime = new DateTime(Integer.parseInt(pTime[2]),
						Integer.parseInt(pTime[0]), Integer.parseInt(pTime[1]),
						Integer.parseInt(pTime[3]), Integer.parseInt(pTime[4]));
				
				String [] dTime = input.next().split("[/ ;]");
				
				IDateTime dropoffTime = new DateTime(Integer.parseInt(dTime[2]),
						Integer.parseInt(dTime[0]),Integer.parseInt(dTime[1]),
						Integer.parseInt(dTime[3]),Integer.parseInt(dTime[4]));
				
				String dropoffLocation = input.next();
				int driverId = input.nextInt();
				int riderId = input.nextInt();
				
				if(rideType.equalsIgnoreCase("private")) {
					if(riders.findById(riderId) == null && drivers.findById(driverId)== null) {
						return false;
					}
					else {
						rides.addRide(new PrivateRide(rideId, pickupLocation,dropoffLocation,
								pickupTime, dropoffTime,drivers.findById(driverId), riders.findById(riderId)));
						rideId++;
					}
				}
			}
			
		}catch(Exception e) {
			return false;
		}
	}

}
