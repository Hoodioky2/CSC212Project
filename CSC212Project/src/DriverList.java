
public class DriverList implements IDriverList {
	private LinkedList<IDriver> drivers;
	private int driverCount;
	
	
	public DriverList() {
		drivers = new LinkedList<IDriver>();
		driverCount =0;
	}
	
	
	/*
	 * inserts  drivers in sorted order by ID
	 * while checks for duplicates
	 * worst case O(n)
	 */
	@Override
	public boolean add(IDriver driver) {
		if (drivers.empty()) {
			drivers.insert(driver);
			driverCount++;
			return true;
		}
		
		if(this.findByVehiclePlate(driver.getVehiclePlate()) != null) {
			return false;
		}
		
		drivers.findFirst();
		IDriver tmp;
		while (true) {
			tmp = drivers.retrieve();
			
			if(driver.compareTo(tmp) ==0 
					|| tmp.equals(driver.getVehiclePlate())) {
				
				return false;
			}
				

			if (driver.compareTo(tmp)< 0) {
				
				drivers.update(driver);
				drivers.insert(tmp);
				driverCount++;
				return true;
			}
			
			if(drivers.last()) {
				drivers.insert(driver);
				driverCount++;
				return true;
			}
			drivers.findNext();
		}
	}
	
	
	
	// returns the driver with the same unique ID
	@Override
	public IDriver findById(int driverId) {
		if(drivers.empty())
			return null;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getId() == driverId) {
				return drivers.retrieve();
			}
			drivers.findNext();
		}
		if(drivers.retrieve().getId() == driverId) {
			return drivers.retrieve();
		}
		return null;
	}
	
	
	
	// seaarch for all drivers with am exact matching name
	@Override
	public LinkedList<IDriver> findByName(String fullName){
		if(drivers.empty())
			return null;
		
		LinkedList<IDriver> res = new LinkedList<IDriver>();
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getName().equals(fullName))
				res.insert(drivers.retrieve());
			drivers.findNext();
		}
		if(drivers.retrieve().getName().equals(fullName))
			res.insert(drivers.retrieve());
		
		if(res.empty())
			return null;
		return res;
	}
	
	
	
	// retuens singel driver with the unique vehicle plate
	@Override
	public IDriver findByVehiclePlate(String vehiclePlate) {
		if(drivers.empty())
			return null;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getVehiclePlate().equals(vehiclePlate))
				return drivers.retrieve();
			drivers.findNext();
		}
		if(drivers.retrieve().getVehiclePlate().equals(vehiclePlate))
			return drivers.retrieve();
		
		return null;
	}
	
	
	
	// returns all drivers with the specified vehicle type
	@Override
	public LinkedList<IDriver> findByVehicleType(VehicleType vehicleType){
		LinkedList<IDriver> res = new LinkedList<IDriver>();
		if(drivers.empty())
			return res;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getVehicleType().equals(vehicleType))
				res.insert(drivers.retrieve());
			drivers.findNext();
		}
		if(drivers.retrieve().getVehicleType().equals(vehicleType))
			res.insert(drivers.retrieve());
		return res;
	}
	
	
	@Override
	public LinkedList<IDriver> getAll(){
		return drivers;
	}
	
	
	// remove a singel driver with the specified ID
	@Override
	public boolean removeById(int driverId) {
		if(drivers.empty())
			return false;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getId() == driverId) {
				drivers.remove();
				driverCount--;
				return true;
			}
			
			drivers.findNext();
		}
		if(drivers.retrieve().getId() == driverId) {
			drivers.remove();
			driverCount--;
			return true;
		}
		return false;
	}
	
	
	// remove a singel driver using specified vehicle plate
	@Override
	public boolean removeByVehiclePlate(String vehiclePlate) {
		if(drivers.empty())
			return false;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getVehiclePlate().equals(vehiclePlate)) {
				drivers.remove();
				driverCount--;
				return true;
			}
			
			drivers.findNext();
		}
		if(drivers.retrieve().getVehiclePlate().equals(vehiclePlate) ) {
			drivers.remove();
			driverCount--;
			return true;
		}
		return false;
	}
	
	
	/*
	 *  removes all drivers with the specified name
	 *  and returns thier number
	 */
	
	@Override
	public int removeByName(String fullName) {
		int count = 0;
		if(drivers.empty())
			return count;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getName().equals(fullName)) {
				drivers.remove();
				count++;
				driverCount--;
			}else
				drivers.findNext();
		}
		if(drivers.retrieve().getName().equals(fullName)) {
			drivers.remove();
			count++;
			driverCount--;
		}
		return count;
	}
	
	
	
	/*
	 * removes all drivers with the specified vehicle type
	 * and returns thier number
	 */
	@Override 
	public int removeByVehicleType(VehicleType vehicleType) {
		int count = 0;
		
		if(drivers.empty())
			return count;
		
		drivers.findFirst();
		while(!drivers.last()) {
			if(drivers.retrieve().getVehicleType().equals(vehicleType)) {
				drivers.remove();
				count++;
				driverCount--;
			}else
				drivers.findNext();
		}
		if(drivers.retrieve().getVehicleType().equals(vehicleType)) {
			drivers.remove();
			count++;
			driverCount--;
		}
		return count;
	}
	
	
	
	@Override
	public int size() {
		return driverCount;
	}
	
	
	@Override
	public String toString() {
		StringBuilder s1 =new StringBuilder();
		drivers.findFirst();
		for(int i =0; i<size(); i++) {
			s1.append(drivers.retrieve().toString()+ "\n");
			drivers.findNext();
		}
		return s1.toString();
	}

}
