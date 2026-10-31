
public class SharedRide extends Ride implements ISharedRide {
	private LinkedList<IRider> participants;
	
	public SharedRide(int rideId, String pickupLocation, String dropofflocation
			, IDateTime pickupTime, IDateTime dropoffTime, IDriver driver){
		
		super(rideId, pickupLocation ,dropofflocation, pickupTime, dropoffTime, driver);
		
		participants = new LinkedList<IRider>();
	}
	
	
	@Override
	public LinkedList<IRider> getParticipants(){
		return participants;
	}
	
	
	
	@Override
	public boolean addParticipant(IRider rider) {
		participants.insert(rider);
		return true;
	}
	
	
	@Override
	public boolean removeParticipantById(int riderId) {
		if(!participants.empty()) {
			participants.findFirst();
			while(!participants.last()) {
				if(participants.retrieve().getId() == riderId) {
					participants.remove();
					return true;
				}
				participants.findNext();
			}
			
			if(participants.retrieve().getId() == riderId) {
				participants.remove();
				return true;
			}
		}
		return false;
	}
	
	
	
	
	@Override
	public boolean hasRider(int riderId) {
		if(!participants.empty()) {
			participants.findFirst();
			while(!participants.last()) {
				
				if(participants.retrieve().getId() == riderId)
					return true;
				
				participants.findNext();
			}

			if(participants.retrieve().getId() == riderId)
				return true;
			
		}
		return false;
		
	}
	
	
	
	
	@Override
	public boolean isEmpty() {
		return participants.empty();
	}
	
	
	public String toString() {
		
		return String.format("%s      RideType:%s",super.toString(), "Shared" );
		
		
	}

}
