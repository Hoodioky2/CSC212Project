
public class Driver extends Person implements IDriver {
	private String vehiclePlate;
	private VehicleType vehicleType;
	
	public Driver(String name, String phoneNumber, int id, 
			String vehiclePlate , VehicleType vehicleType ) {
		
		super(name, phoneNumber, id);
		this.vehiclePlate = vehiclePlate;
		this.vehicleType = vehicleType;
	}
	
	@Override
	public void setVehiclePlate(String vp) {
		String rgex = "[A-Z]{4}[0-9]{4}";
		if(!vp.matches(rgex))
			throw new IllegalArgumentException("Invalid vehiclePlate"); 
		
	}
	
	
	@Override
	public String getVehiclePlate() {
		return vehiclePlate;
	}
	
	
	
	@Override
	public void setVehicleType(VehicleType vt) {
		vehicleType = vt;
	}
	
	
	@Override
	public VehicleType getVehicleType() {
		return vehicleType;
	}
	
	
	/*
	 * if returns 1 it means greater than the other rider
	 * if returns -1 it means less than the other rider
	 * if returns 0 it means equals to each other
	 */
	@Override
	public int compareTo(IDriver other) {
		
		if(this.getId()> other.getId())
			return 1;
		else if(this.getId()< other.getId())
			return -1;
		
		return 0;
	}

}
