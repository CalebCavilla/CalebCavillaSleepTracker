package application;

public class Time {

	int hours;
	int minutes;
	String period;
	
	public Time(int hours, int minutes, String period) {
		this.hours = hours;
		this.minutes = minutes;
		this.period = period;
	}

	
	public String toString() {
		return hours + ":" + minutes + " " + period;
	}
	
	public String difference(Time other) {
		
		String timeDifference = null;
		
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
    			timeDifference = (endHours - startHours) + " hours " + (endMinutes - startMinutes) + " minutes";

    		} else if (startMinutes <= endMinutes) {
    			timeDifference = (endHours - startHours) + " hours " + (endMinutes - startMinutes) + " minutes";
    		}
			
		// Going from the afternoon to the morning
    	} else if (this.period.equals("pm") && other.period.equals("am")){
    		startHours += 12;
			if (startMinutes > 0) {
				timeDifference = (((23 - startHours) + endHours) + ((60 - startMinutes) + endMinutes) / 60) + " hours " + ((60 - startMinutes) + endMinutes) % 60 + " minutes";

    		} else if (startMinutes == 0) {
    			timeDifference = (24 - startHours) + endHours + " hours " + endMinutes + " minutes";
    		}
		
		// Going from the afternoon to the afternoon
    	} else if (this.period.equals("pm") && other.period.equals("pm")){
    		if (startMinutes > endMinutes) {
    			endHours -= 1;
    			endMinutes += 60;
    			timeDifference = (endHours - startHours) + " hours " + (endMinutes - startMinutes) + " minutes";

    		} else if (startMinutes <= endMinutes) {
    			timeDifference = (endHours - startHours) + " hours " + (endMinutes - startMinutes) + " minutes";
    		}
    
    	// Going from morning to the afternoon
    	} else if (this.period.equals("am") && other.period.equals("pm")){
    		endHours += 12;
    		if (startMinutes > endMinutes) {
    			endHours -= 1;
    			endMinutes += 60;
    			timeDifference = (endHours - startHours) + " hours " + (endMinutes - startMinutes) + " minutes";

    		} else if (startMinutes <= endMinutes) {
    			timeDifference = (endHours - startHours) + " hours " + (endMinutes - startMinutes) + " minutes";
    		}
    	
    	}
		return timeDifference;
    }
}
