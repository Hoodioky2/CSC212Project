
public class DriverList implements IDriverList {
	private LinkedList<IDriver> drivers;
	private int driverCount;
	
	
	public DriverList() {
		drivers = new LinkedList<IDriver>();
		driverCount =0;
	}
	
	
	
	@Override
	public boolean add(IDriver driver) {
		if (drivers.empty()) {
			drivers.insert(driver);
			driverCount++;
			return true;
		}
		drivers.findFirst();
		IDriver tmp;
		while (true) {
			
			if(driver.compareTo(drivers.retrieve()) ==0 
					|| drivers.retrieve().getVehiclePlate().equals(driver.getVehiclePlate())) {
				
				return false;
			}
				

			if (driver.compareTo(drivers.retrieve())< 0) {
				tmp = drivers.retrieve();
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
		String s1 ="";
		drivers.findFirst();
		for(int i =0; i<size(); i++) {
			s1 += String.format("%s%n", drivers.retrieve().toString());
			drivers.findNext();
		}
		return s1;
	}

}
