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
			input.useDelimiter(",|\\r?\\n");

			while (input.hasNextLine()) {
				int id = input.nextInt();
				String name = input.next();
				String email = input.next();
				String phoneNumber = input.next();
				String homeCity = input.next();
				riders.add(new Rider(name, phoneNumber, id, email, homeCity));

			}
			input.close();
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
			input.useDelimiter(",|\\r?\\n");

			while (input.hasNextLine()) {
				int id = input.nextInt();
				String name = input.next();
				String phoneNumber = input.next();
				String vehiclePlate = input.next();
				String vehicleTypetext = input.next();
				VehicleType type = VehicleType.valueOf(vehicleTypetext);
				drivers.add(new Driver(name, phoneNumber, id, vehiclePlate, type));
			}
			input.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	private IDateTime parseDateTime(String timeStr) {
		String[] t = timeStr.split("[/ :]");
		return new DateTime(Integer.parseInt(t[2]), Integer.parseInt(t[0]), Integer.parseInt(t[1]),
				Integer.parseInt(t[3]), Integer.parseInt(t[4]));
	}

	@Override
	public boolean loadRidesFromCSV(String ridesFilePath) {

		try {
			File f = new File(ridesFilePath);
			Scanner input = new Scanner(f);
			input.useDelimiter(",|\\r?\\n");

			while (input.hasNext()) {
				String rideType = input.next();
				String pickupLocation = input.next();
				IDateTime pickupTime = parseDateTime(input.next());
				IDateTime dropoffTime = parseDateTime(input.next());
				String dropoffLocation = input.next();
				int driverId = input.nextInt();

				IDriver driver = drivers.findById(driverId);
				
				if (driver == null) {
					return false;
				}
				
				if(hasTimeConflict(driver.getRideHistory(),pickupTime,dropoffTime)) {
					return false;
				}
				

				if (rideType.equalsIgnoreCase("Private")) {
					int riderId = input.nextInt();

					IRider rider = riders.findById(riderId);

					if (rider == null) {
						return false;
					}
					
					if(hasTimeConflict(rider.getRideHistory(),pickupTime,dropoffTime)) {
						return false;
					}

					PrivateRide pr = new PrivateRide(rides.size() + 1, pickupLocation, dropoffLocation, pickupTime,
							dropoffTime, driver, rider);

					rides.addRide(pr);
					driver.getRideHistory().insert(pr);
					rider.getRideHistory().insert(pr);

				} else {
					String[] riderIdsStr = input.next().split(";");

					IRider[] sharedRider = new IRider[riderIdsStr.length];

					for (int i = 0; i < riderIdsStr.length; i++) {
						IRider rider = riders.findById(Integer.parseInt(riderIdsStr[i]));
						if (rider == null) {
							return false;
						}
						
						if(hasTimeConflict(rider.getRideHistory(), pickupTime,dropoffTime)) {
							return false;
						}
						
						sharedRider[i] = rider;
					}
					
					SharedRide sr = new SharedRide(rides.size() + 1, pickupLocation, dropoffLocation, pickupTime,
							dropoffTime, driver);
					
					for(int i=0; i<sharedRider.length; i++) {
						sr.addParticipant(sharedRider[i]);
						sharedRider[i].getRideHistory().insert(sr);
					}
					
					rides.addRide(sr);
					driver.getRideHistory().insert(sr);
				}

			}
			input.close();
			return true;

		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean addRider(IRider rider) {
		return riders.add(rider);
	}

	@Override
	public boolean addDriver(IDriver driver) {
		if (drivers.findById(driver.getId()) != null || drivers.findByVehiclePlate(driver.getVehiclePlate()) != null) {
			return false;
		}
		return drivers.add(driver);
	}

	@Override
	public IRider searchRiderById(int riderId) {
		return riders.findById(riderId);
	}

	@Override
	public IRider searchRiderByEmail(String email) {
		return riders.findByEmail(email);
	}

	@Override
	public LinkedList<IRider> searchRidersByName(String fullName) {
		return riders.findByName(fullName);
	}

	@Override
	public LinkedList<IRider> searchRidersByHomeCity(String homeCity) {
		return riders.findByHomeCity(homeCity);
	}

	@Override
	public LinkedList<IRider> getAllRiders() {
		return riders.getAll();
	}

	@Override
	public IDriver searchDriverById(int driverId) {
		return drivers.findById(driverId);
	}

	@Override
	public IDriver searchDriverByVehiclePlate(String vehiclePlate) {
		return drivers.findByVehiclePlate(vehiclePlate);
	}

	@Override
	public LinkedList<IDriver> searchDriversByVehicleType(VehicleType vehicleType) {
		return drivers.findByVehicleType(vehicleType);
	}

	@Override
	public LinkedList<IDriver> getAllDrivers() {
		return drivers.getAll();
	}

	@Override
	public boolean removeRider(int riderId) {
		if (riders.findById(riderId) == null) {
			return false;
		}
		// this is the rides that rider participite on
		LinkedList<IRide> riderRides = riders.findById(riderId).getRideHistory();

		if (!riderRides.empty()) {
			riderRides.findFirst();

			while (true) {
				IRide currentRide = riderRides.retrieve();
				// case 1 private ride
				if (currentRide instanceof PrivateRide) {
					rides.removeRideById(currentRide.getRideId());
				}
				// case 2 shared ride
				else if (currentRide instanceof SharedRide) {
					SharedRide sr = (SharedRide) currentRide;
					sr.removeParticipantById(riderId);

					if (sr.getParticipants().empty()) {
						rides.removeRideById(sr.getRideId());
					}
				}
				// after procces the last ride exit the loop
				if (riderRides.last()) {
					break;
				}
				riderRides.findNext();

			}
		}

		riders.removeById(riderId);
		return true;

	}

	@Override
	public boolean removeDriver(int driverId) {
		if (drivers.findById(driverId) == null) {
			return false;
		}
		LinkedList<IRide> driverRides = drivers.findById(driverId).getRideHistory();

		if (!driverRides.empty()) {
			driverRides.findFirst();
			while (!driverRides.last()) {
				rides.removeRideById(driverRides.retrieve().getRideId());
				driverRides.findNext();
			}
			rides.removeRideById(driverRides.retrieve().getRideId());
			drivers.removeById(driverId);
			return true;

		}
		return false;

	}

	private boolean hasTimeConflict(LinkedList<IRide> history, IDateTime newPickup, IDateTime newDropoff) {
		if (history.empty()) {
			return false;
		}
		history.findFirst();
		while (true) {
			if (newPickup.compareTo(history.retrieve().getDropoffTime()) < 0
					&& history.retrieve().getPickupTime().compareTo(newDropoff) < 0) {
				return true;
			}

			if (history.last()) {
				break;
			}
			history.findNext();
		}
		return false;
	}

	@Override
	public boolean schedulePrivateRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
			String dropoffLocation, int riderId, int driverId) {
		IDriver driver = drivers.findById(driverId);
		IRider rider = riders.findById(riderId);

		if (driver == null || rider == null) {
			return false;
		}

		if (hasTimeConflict(driver.getRideHistory(), pickupTime, dropoffTime)
				|| hasTimeConflict(rider.getRideHistory(), pickupTime, dropoffTime)) {
			return false;
		}

		PrivateRide pr = new PrivateRide(rides.size() + 1, pickupLocation, dropoffLocation, pickupTime, dropoffTime,
				driver, rider);

		rides.addRide(pr);
		driver.getRideHistory().insert(pr);
		rider.getRideHistory().insert(pr);
		return true;

	}

	@Override
	public boolean scheduleSharedRide(String pickupLocation, IDateTime pickupTime, IDateTime dropoffTime,
			String dropoffLocation, int[] riderIds, int driverId) {
		// checks if the driver exsits
		if (drivers.findById(driverId) == null) {
			return false;
		}

		IDriver driver = drivers.findById(driverId);
		// checks if the driver have time conflict
		if (hasTimeConflict(driver.getRideHistory(), pickupTime, dropoffTime)) {
			return false;
		}

		IRider rider;
		for (int i = 0; i < riderIds.length; i++) {
			// check if all riders are exsist
			if (riders.findById(riderIds[i]) == null) {
				return false;
			}
			rider = riders.findById(riderIds[i]);
			// checks if the riders have time conflict
			if (hasTimeConflict(rider.getRideHistory(), pickupTime, dropoffTime)) {
				return false;
			}
		}
		SharedRide sr = new SharedRide(rides.size() + 1, pickupLocation, dropoffLocation, pickupTime, dropoffTime,
				driver);
		for (int i = 0; i < riderIds.length; i++) {
			sr.addParticipant(riders.findById(riderIds[i]));
			riders.findById(riderIds[i]).getRideHistory().insert(sr);
		}
		driver.getRideHistory().insert(sr);
		rides.addRide(sr);
		return true;

	}

	@Override
	public LinkedList<IRide> searchRidesByPickupLocation(String pickupLocation) {
		return rides.findByPickupLocation(pickupLocation);
	}

	@Override
	public LinkedList<IRide> searchRidesByRiderName(String riderName) {
		return rides.findByRiderName(riderName);
	}

	@Override
	public LinkedList<IRider> getSharedRideParticipants(String pickupLocation) {
		LinkedList<IRider> res = new LinkedList<IRider>();
		LinkedList<IRide> rideByPickupLocation = rides.findByPickupLocation(pickupLocation);

		if (rideByPickupLocation.empty()) {
			return res;
		}
		rideByPickupLocation.findFirst();
		while (true) {
			if (rideByPickupLocation.retrieve() instanceof SharedRide) {

				SharedRide sr = (SharedRide) rideByPickupLocation.retrieve();
				if (!sr.getParticipants().empty()) {

					sr.getParticipants().findFirst();
					while (true) {
						res.insert(sr.getParticipants().retrieve());
						if (sr.getParticipants().last()) {
							break;
						}
						sr.getParticipants().findNext();
					}
				}
			}
			if (rideByPickupLocation.last()) {
				break;
			}
			rideByPickupLocation.findNext();

		}
		return res;
	}

	@Override
	public LinkedList<IRide> getAllRidesAlphabetically() {
		return rides.getAllAlphabetically();
	}

}
