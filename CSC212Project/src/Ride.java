
public abstract class Ride implements IRide {
	private int rideId;
	private String pickupLocation;
	private String dropoffLocation;
	private IDateTime pickupTime;
	private IDateTime dropoffTime;
	private IDriver driver;
	
	
	public Ride(int rideId, String pickupLocation, String dropofflocation
			, IDateTime pickupTime, IDateTime dropoffTime, IDriver driver) {
		
		this.rideId = rideId;
		this.pickupLocation = pickupLocation;
		this.dropoffLocation = dropofflocation;
		this.pickupTime = pickupTime;
		this.dropoffTime = dropoffTime;
		this.driver = driver;
	}
	
	
	@Override
	public int getRideId() {
		return rideId;
	}
	
	
	@Override
	public String getPickupLocation() {
		return pickupLocation;
	}
	
	@Override
	public void setPickupLocation(String pl) {
		pickupLocation =pl;
	}
	
	
	@Override
	public IDateTime getPickupTime() {
		return pickupTime;
	}
	
	
	
	@Override
	public IDateTime getDropoffTime() {
		return dropoffTime;
	}
	
	
	
	@Override
	public String getDropoffLocation() {
		return dropoffLocation;
	}
	
	
	@Override
	public void setDropoffLocation(String dl) {
		dropoffLocation = dl;
	}
	
	
	
	@Override
	public IDriver getDriver() {
		return driver;
	}
	
	
	@Override
	public void setDriver(IDriver d) {
		driver = d;
	}
	
	
	@Override
	public abstract boolean hasRider(int riderId);
	
	
	@Override
	public int compareTo(IRide other) {
		
		return this.getPickupLocation().compareTo(other.getPickupLocation());
		
	}
	
	
	@Override
	public String toString() {
		
		return String.format("ID: %d 	PickUP Location: %s 		PickUpTime: %s 		"
				+ "DropOff Location: %s 		DropOffTime: %s 		Driver: %s",rideId
				,pickupLocation, pickupTime.format(),dropoffLocation, dropoffTime.format(), driver );
	}

}
