
public class Driver extends Person implements IDriver {
	private String vehiclePlate;
	private VehicleType vehicleType;

	public Driver(String name, String phoneNumber, int id,
			String vehiclePlate, VehicleType vehicleType) {

		super(name, phoneNumber, id);
		setVehiclePlate(vehiclePlate);
		this.vehicleType = vehicleType;
	}

	@Override
	public void setVehiclePlate(String vp) {
		String rgex = "[A-Z]{3}[0-9]{4}";
		if (!vp.matches(rgex))
			throw new IllegalArgumentException("Invalid vehiclePlate");

		vehiclePlate = vp;

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
	 * if returns 1 greater than the other driver
	 * if returns -1 less than the other driver
	 * if returns 0 equals to each other
	 */
	@Override
	public int compareTo(IDriver other) {

		if (this.getId() > other.getId())
			return 1;
		else if (this.getId() < other.getId())
			return -1;

		return 0;
	}

	@Override
	public String toString() {

		return String.format("%s	Plates: %s		VehicleType: %s", 
				super.toString(), vehiclePlate, vehicleType);
	}

}
