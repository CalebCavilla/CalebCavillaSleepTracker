package application;

import java.util.ArrayList;

/**
* Time represents an actual moment or amount of time that can occur during a 24 hour period. Time is not associated with a date.
* time has an amount of hours, minutes, and an optional period representing am or pm.
*/
public class Time {

	private int hours;
	private int minutes;
	private String period;
	
	
	/**
	* Constructs a time object with a given hour, minute, and period. Usually used to represent the time of a day 
	* @param hours the integer number of hours from 1-12
	* @param mintes the integer number of minutes from 0-59
	* @param the string representing the period of the day, either am or pm
	*/
	public Time(int hours, int minutes, String period) {
		this.hours = hours;
		this.minutes = minutes;
		this.period = period;
	}
	
	/**
	* Constructs a time object with a given hour and minute. Usually used to represent a duration of time
	* @param hours the integer number of hours from 1-12
	* @param mintes the integer number of minutes from 0-59
	*/
	public Time(int hours, int minutes) {
		this.hours = hours;
		this.minutes = minutes;
	}
	
	/**
	* Constructs an exact copy of a given time object
	* @param toCopy the time object to be copied
	*/
	public Time(Time toCopy) {
		this.hours = toCopy.hours;
		this.minutes = toCopy.minutes;
		this.period = toCopy.period;
	}

	/**
	* Converts the Time into a format describing the amount of hours and minutes
	* @return a string describing the amount of hours and minutes of the time object
	*/
	public String printDifferenceFormat() {
		return hours + " hrs " + minutes + " min";
	}
	
	/**
	* Converts the time into a readable string in standard 12 hour time format. Can specify if the period should be included.
	* @param includePeriod boolean determining if the period should be included in the final string
	* @return a string showing the time in standard 12 hour time format
	*/
	public String printTimeFormat(Boolean includePeriod) {
		String strMinutes;
		// since Time object converts 00 --> 0, if we want to print the time object we need convert 0's back into 00's
		if (minutes == 0) {
			strMinutes = "00";
		}else {
			strMinutes = String.valueOf(minutes);
		}
		// include the period if required
		if (includePeriod) {
			return hours + ":" + strMinutes + " " + period;
		}else {
			return hours + ":" + strMinutes + " ";
		}
	}
	
	/**
	* Calculates the time difference between two given time objects.
	* @param the time to find the difference from
	* @return the time difference between time the method is called on and the time passed into the method
	*/
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
    		if (startHours == 12 ) {
    			startHours = 0;
    		}
    		if (endHours == 12 ) {
    			endHours = 0;
    		}
    		
    		// if the start minutes are higher then the end minutes, that means we are going to be going below 0 minutes, or losing an hour.
			if (startMinutes > endMinutes) {
    			endHours -= 1;
    			endMinutes += 60;
    			hoursDifference = endHours - startHours;
    			minutesDifference = endMinutes - startMinutes;

    		// if the start minutes are equal to or less than the end minutes, we don't need to worry about the minutes going below 0.
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
    		if (endHours != 12) {
    			endHours += 12;
    		}
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
	
	/**
	* gets the number of hours in the time
	* @return the integer number of hours
	*/
	public int getHours() {
		return hours;
	}

	/**
	* sets the number of hours in the time object
	* @param the integer number of hours to be set
	*/
	public void setHours(int hours) {
		this.hours = hours;
	}

	/**
	* gets the number of minutes in the time
	* @return the integer number of minutes
	*/
	public int getMinutes() {
		return minutes;
	}

	/**
	* sets the number of minutes in the time object
	* @param the integer number of minutes to be set
	*/
	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	/**
	* gets the 12-hour period of the time
	* @return the string time period
	*/
	public String getPeriod() {
		return period;
	}

	/**
	* sets the 12-hour period of the time
	* @param the string time period
	*/
	public void setPeriod(String period) {
		this.period = period;
	}

}
