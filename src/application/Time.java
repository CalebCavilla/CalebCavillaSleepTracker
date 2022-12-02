package application;

import java.util.ArrayList;

public class Time {

	private int hours;
	private int minutes;
	private String period;
	
	public Time(int hours, int minutes, String period) {
		this.hours = hours;
		this.minutes = minutes;
		this.period = period;
	}
	
	public Time(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}
	
	public Time(Time toCopy) {
		this.hours = toCopy.hours;
		this.minutes = toCopy.minutes;
		this.period = toCopy.period;
	}

	
	public String printDifferenceFormat() {
		return hours + " hrs " + minutes + " min";
	}
	
	public String printTimeFormat() {
		String strMinutes;
		if (minutes == 0) {
			strMinutes = "00";
		}else {
			strMinutes = String.valueOf(minutes);
		}
		
		return hours + ":" + strMinutes + " " + period;
	}
	
	public Time difference(Time other) {
		
		int hoursDifference = 0;
		int minutesDifference = 0;
		
		// DefaultVariables for the goal sleep time
    	int startHours = this.hours;
    	int startMinutes = this.minutes;
    	
    	
    	// Default Variable for the goal awake time
    	int endHours = other.hours;
    	int endMinutes = other.minutes;
    	
    	
    	// calculate the users total goal for hours of sleep
    	// Going from the morning to the morning
    	if (this.period.equals("am") && other.period.equals("am")){
    		if (startHours == 12) {
    			startHours = 0;
    		}
    		
			if (startMinutes > endMinutes) {
    			endHours -= 1;
    			endMinutes += 60;
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;

    		} else if (startMinutes <= endMinutes) {
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;
    		}
			
		// Going from the afternoon to the morning
    	} else if (this.period.equals("pm") && other.period.equals("am")){
    		if (startHours != 12) {
    			startHours += 12;
    		}
    		if (endHours == 12) {
    			endHours = 0;
    		}
    		
			if (startMinutes > 0) {
				hoursDifference = ((23 - startHours) + endHours) + ((60 - startMinutes) + endMinutes) / 60;
				minutesDifference = ((60 - startMinutes) + endMinutes) % 60;

    		} else if (startMinutes == 0) {
        		hoursDifference = (24 - startHours) + endHours;
    			minutesDifference = endMinutes;
    		}
		
		// Going from the afternoon to the afternoon
    	} else if (this.period.equals("pm") && other.period.equals("pm")){
    		if (startHours == 12) {
    			startHours = 0;
    		}
    		
    		if (startMinutes > endMinutes) {
    			endHours -= 1;
    			endMinutes += 60;
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;

    		} else if (startMinutes <= endMinutes) {
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;
    		}
    
    	// Going from morning to the afternoon
    	} else if (this.period.equals("am") && other.period.equals("pm")){
    		endHours += 12;
    		if (startHours == 12) {
    			startHours = 0;
    		}
    		if (startMinutes > endMinutes) {
    			endHours -= 1;
    			endMinutes += 60;
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;

    		} else if (startMinutes <= endMinutes) {
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;
    		}
    	
    	}
    	Time difference = new Time(hoursDifference, minutesDifference);
		return difference;
    }
	
	
	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

}
