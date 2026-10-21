
public class DateTime implements IDateTime {
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minute;

	public DateTime(int year, int month, int day, int hour, int minute) {

		this.year = year;
		this.month = month;
		this.day = day;
		this.hour = hour;
		this.minute = minute;
	}

	@Override
	public int getYear() {
		return year;
	}

	@Override
	public int getMonth() {
		return month;
	}

	@Override
	public int getDay() {
		return day;
	}

	@Override
	public int getHour() {
		return hour;
	}

	@Override
	public int getMinute() {
		return minute;
	}
	
	
	@Override
	public String format() {
		return String.format("%d/%d/%d  %d:%d" , year, month, day, hour, minute);
	}
	
	

	@Override
	public int compareTo(IDateTime other) {
		
		if(this.getYear() != other.getYear()) 
			return Integer.compare(this.getYear(), other.getYear());
		
		if(this.getMonth() != other.getMonth()) 
			return Integer.compare(this.getMonth(), other.getMonth());
		
		if(this.getDay() != other.getDay())
			return Integer.compare(this.getDay(), other.getDay());
		
		if(this.getHour() != other.getHour())
			return Integer.compare(this.getHour(), other.getHour());
		
		if(this.getMinute() != other.getMinute())
			return Integer.compare(this.getMinute() , other.getMinute());
		
		return 0;
		
	}

}
