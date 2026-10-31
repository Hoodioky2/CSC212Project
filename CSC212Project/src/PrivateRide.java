
public class PrivateRide extends Ride implements IPrivateRide {
	private IRider rider;
	
	public PrivateRide(int rideId, String pickupLocation, String dropofflocation
			, IDateTime pickupTime, IDateTime dropoffTime, IDriver driver, IRider rider) {
		
		super(rideId, pickupLocation ,dropofflocation, pickupTime, dropoffTime, driver);
		
		this.rider= rider;
	}
	
	
	@Override
	public IRider getRider() {
		return rider;
	}
	
	
	@Override
	public void setRider(IRider r) {
		rider =r;
	}
	
	
	@Override
	public boolean hasRider(int riderId) {
		
		return this.rider != null && this.rider.getId() == riderId;
	}
	
	
	public String toString() {
		return String.format("%s      RideType:%s",this.toString(), "Private" );
	}
	

}
