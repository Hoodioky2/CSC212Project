
public class RiderList implements IRiderList {
	private LinkedList<IRider> riders;
	private int ridersCount;

	public RiderList() {
		riders = new LinkedList<IRider>();
		ridersCount = 0;
	}

	@Override
	/*
	 * add a rider in sorted order by ID
	 * and checks for duplicates
	 * worst case O(n)
	 */
	public boolean add(IRider rider)  {
		if (riders.empty()) { 
			riders.insert(rider);
			ridersCount++;
			return true;
		}
		
		if(this.findByEmail(rider.getEmail())!= null) {
			return false;
		}
		
		riders.findFirst();  
		IRider tmp; 
		while (true) {
			tmp =riders.retrieve();
			
			
			
			if(rider.compareTo(tmp) ==0)
				return false;

			if (rider.compareTo(tmp)< 0) {
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
	
	
	
	// searches for a rider using thier ID
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
	
	
	
	// returns all riders with matching full name
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
	
	
	// searches for a rider using email
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
	
	
	
	//retuens all riders have the same home city
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
	
	
	
	//remove a singel rider by his ID
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
	
	
	
	// remove a singel rider by his email
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
	
	
	
	
	/*
	 * remove all riders matching the given name
	 * returns the number of removed riders
	 */
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
	
	
	
	
	/*
	 * remove all riders from specified home city
	 * returns the number of riders removed
	 */
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
	
	
	
	@Override
	public String toString() {
		StringBuilder s1 =new StringBuilder();
		
		riders.findFirst();
		for(int i=0; i<size(); i++) {
			s1.append(riders.retrieve().toString()+ "\n");
			riders.findNext();
		}
		return s1.toString();
	}
}
