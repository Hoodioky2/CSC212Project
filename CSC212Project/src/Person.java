
public abstract class Person implements IPerson {
	private String name;
	private String phoneNumber;
	private int id;
	private LinkedList<IRide> rideHistory;
	
	public Person(String name, String phoneNumber, int id) {
		this.name = name;
		setPhoneNumber(phoneNumber);
		this.id = id;
		rideHistory = new LinkedList<IRide>();
	}
	@Override
	public void setName(String n) {
		name = n;
	}
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void setPhoneNumber(String pn) {
		String rgex = "[0-9]{10}";
		if(pn.matches(rgex)) {
			phoneNumber = pn;
			return;
		}
		throw new IllegalArgumentException("Invalid phoneNumber");
		
	}
	
	@Override
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	
	@Override
	public int getId() {
		return id;
	}
	
	
	@Override
	public LinkedList<IRide> getRideHistory(){
		return rideHistory;
	}
	
	@Override
	public String toString() {
		return String.format("Name: %s	Phonenumber: %s	 ID: %d", name, phoneNumber, id);
		
	}

}
