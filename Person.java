
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
		try {
			Long.parseLong(pn);
		}catch(NumberFormatException e) {
			throw new IllegalArgumentException("the number must contains only digits");
		}
		if(pn.length() != 10) {
			throw new IllegalArgumentException("The number of digits must be 10");
		}
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
