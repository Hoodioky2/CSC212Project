
public class RideList implements IRideList {
	private LinkedList<IRide> rides;
	private int rideCount;

	public RideList() {
		rides = new LinkedList<IRide>();
		rideCount = 0;
	}
	
	

	@Override
	public boolean addRide(IRide ride) {
		if (rides.empty()) {
			rides.insert(ride);
			rideCount++;
			return true;
		}

		rides.findFirst();
		while (true) {
			if (ride.compareTo(rides.retrieve()) < 0) {
				IRide tmp = rides.retrieve();
				rides.update(ride);
				rides.insert(tmp);
				rideCount++;
				return true;
			}
			if (rides.last()) {
				rides.insert(ride);
				rideCount++;
				return true;
			}
			rides.findNext();
		}
	}
	
	
	
	@Override
	public boolean removeRideById(int rideId) {
		if(rides.empty())
			return false;
		
		rides.findFirst();
		while(true) {
			if(rides.retrieve().getRideId() == rideId) {
				rides.remove();
				rideCount--;
				return true;
			}
			if(rides.last())
				break;
			rides.findNext();
		}
		return false;
	}
	
	
	
	

	@Override
	public LinkedList<IRide> getAllAlphabetically() {
		return rides;
	}
	
	

	@Override
	public LinkedList<IRide> findByPickupLocation(String pickupLocation) {
		LinkedList<IRide> res = new LinkedList<IRide>();
		if (rides.empty())
			return res;
		
		rides.findFirst();
		while (!rides.last()) {
			if (rides.retrieve().getPickupLocation().equals(pickupLocation))
				res.insert(rides.retrieve());
			rides.findNext();
		}
		if (rides.retrieve().getPickupLocation().equals(pickupLocation))
			res.insert(rides.retrieve());
		return res;
	}
	
	
	
	@Override
	public LinkedList<IRide> findByRiderName(String riderFullName){
		LinkedList<IRide> res = new LinkedList<IRide>();
		if(rides.empty()) {
			return res;
		}
		
		rides.findFirst();
		while(true) {
			//case 1 if the ride was private ride
			if(rides.retrieve() instanceof IPrivateRide) {
				IPrivateRide ride = (IPrivateRide)rides.retrieve();
				if(ride.getRider().getName().equals(riderFullName))
					res.insert(ride);
			}
			//case 2 if the ride was a shared ride
			else if(rides.retrieve() instanceof ISharedRide) {
				ISharedRide ride = (ISharedRide)rides.retrieve();
				if(!ride.getParticipants().empty()) {
					ride.getParticipants().findFirst();
					while(true) {
						if(ride.getParticipants().retrieve().getName().equals(riderFullName)) {
							res.insert(ride);
							break;
						}
						
						if(ride.getParticipants().last())
							break;
						ride.getParticipants().findNext();
							
					}
					
				}
			
			}
			if(rides.last())
				break;
			rides.findNext();
		}
		return res;

	}
	
	
	
	@Override
	public int size() {
		return rideCount;
	}
		
}

