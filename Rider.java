
public class Rider extends Person implements IRider {
	
	private String email;
	private String homeCity;
	
	public Rider(String name, String phoneNumber, int id, String email, String homeCity) {
		super(name, phoneNumber , id);
		this.email = email;
		this.homeCity = homeCity;
	}
	
	@Override
	public void setEmail(String e ) {
		email = e;
	}
	
	
	@Override
	public String getEmail() {
		return email;
	}
	
	
	@Override
	public void setHomeCity(String hc) {
		homeCity =hc;
	}
	
	
	
	@Override
	public String getHomeCity() {
		return homeCity;
	}
	
	/*
	 * if returns 1 greater than the other rider
	 * if returns -1 less than the other rider
	 * if returns 0 equals to each other
	 */
	@Override
	public int compareTo(IRider other) {
		
		if(this.getId()> other.getId())
			return 1;
		else if(this.getId()< other.getId())
			return -1;
		
		return 0;
		
	}

}
