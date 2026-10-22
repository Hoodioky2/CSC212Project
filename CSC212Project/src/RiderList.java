
public class RiderList implements IRiderList {
	private LinkedList<IRider> riders;
	private int ridersCount;

	public RiderList() {
		riders = new LinkedList<IRider>();
		ridersCount = 0;
	}

	@Override
	public boolean add(IRider rider) {
		if (riders.empty()) {
			riders.insert(rider);
			ridersCount++;
			return true;
		}
		riders.findFirst();
		IRider tmp;
		while (true) {
			
			if(rider.compareTo(riders.retrieve()) ==0)
				return false;

			if (rider.compareTo(riders.retrieve())< 0) {
				tmp = riders.retrieve();
				riders.update(rider);
				riders.insert(tmp);
				ridersCount++;
				return true;
			}
			
			if(riders.last()) {
				riders.insert(rider);
				ridersCount++;
				return true;
			}
			riders.findNext();
		}
	}
	
	
	
	
	@Override
	public IRider findById(int riderId) {
		if(riders.empty())
			return null;
		
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getId() == riderId) {
				return riders.retrieve();
			}
			riders.findNext();
		}
		if(riders.retrieve().getId() == riderId) {
			return riders.retrieve();
		}
		return null;
		
	}
	
	
	
	
	@Override
	public LinkedList<IRider> findByName(String fullName){
		LinkedList<IRider> res = new LinkedList<IRider>();
		
		if(riders.empty())
			return res;
		
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getName().equals(fullName))
				res.insert(riders.retrieve());
			riders.findNext();
		}
		if(riders.retrieve().getName().equals(fullName))
			res.insert(riders.retrieve());
		
		return res;
	}
	
	
	@Override
	public IRider findByEmail(String email) {
		if(riders.empty())
			return null;
		
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getEmail().equals(email))
				return riders.retrieve();
			riders.findNext();
		}
		if(riders.retrieve().getEmail().equals(email))
			return riders.retrieve();
		
		return null;
	}
	
	
	
	
	@Override
	public LinkedList<IRider> findByHomeCity(String homeCity){
		LinkedList<IRider> res = new LinkedList<IRider>();
		
		if(riders.empty())
			return res;
		
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getHomeCity().equals(homeCity))
				res.insert(riders.retrieve());
			riders.findNext();
				
		}
		if(riders.retrieve().getHomeCity().equals(homeCity))
			res.insert(riders.retrieve());
		
		return res;
		}
	
	
	@Override
	public LinkedList<IRider> getAll(){
		return riders;
	}
	
	
	

	@Override
	public boolean removeById(int riderId) {
		if(riders.empty())
			return false;
		
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getId() == riderId) {
				riders.remove();
				ridersCount--;
				return true;
			}
			
			riders.findNext();
		}
		if(riders.retrieve().getId() == riderId) {
			riders.remove();
			ridersCount--;
			return true;
		}
		return false;
	}
	
	
	

	@Override
	public boolean removeByEmail(String email) {
		if(riders.empty())
			return false;
		
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getEmail().equals(email)) {
				riders.remove();
				ridersCount--;
				return true;
			}
			
			riders.findNext();
		}
		if(riders.retrieve().getEmail().equals(email)) {
			riders.remove();
			ridersCount--;
			return true;
		}
		return false;
	}
	
	
	
	

	@Override
	public int removeByName(String fullName) {
		if(riders.empty())
			return 0;
		
		int count = 0;
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getName().equals(fullName)) {
				riders.remove();
				count++;
				ridersCount--;
			}else
				riders.findNext();
		}
		if(riders.retrieve().getName().equals(fullName)) {
			riders.remove();
			count++;
			ridersCount--;
		}
		return count;
	}
	
	
	
	

	@Override
	public int removeByHomeCity(String homeCity) {
		if(riders.empty())
			return 0;
		
		int count = 0;
		riders.findFirst();
		while(!riders.last()) {
			if(riders.retrieve().getHomeCity().equals(homeCity)) {
				riders.remove();
				count++;
				ridersCount--;
			}else
				riders.findNext();
				
		}
		if(riders.retrieve().getHomeCity().equals(homeCity)) {
			riders.remove();
			count++;
			ridersCount--;
		}
		return count;
	}
	
	

	@Override
	public int size() {
		return ridersCount;
	}
}
