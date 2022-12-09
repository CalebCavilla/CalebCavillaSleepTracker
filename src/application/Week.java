package application;

import java.time.LocalDate;
import java.util.ArrayList;

/**
* Represents a given 7 day period in history.
* All weeks start on Sunday and end on Saturday.
*/
public class Week {

	private ArrayList<Day> daysOfWeek = new ArrayList<Day>();
	private Time totalSleepDebt;

	/**
	* Constructs the week that the specified date falls into. 
	* If the day specified is a Tuesday, the week created will be a Sunday-Saturday period containing the Tuesday
	* @param date the LocalDate that the generated week is to contain
	*/
	public Week(LocalDate date) {
		// get the start of the week based on the given date
		date = calculateStartOfWeek(date);
		this.daysOfWeek.add(new Day(date));
		
		// constructs day objects for the rest of the week to be put into the daysOfWeek List
		for (int i = 1; i < 7; i++) {
			daysOfWeek.add(new Day(date.plusDays(i)));
		}
	}
	
	/**
	* Calculates the closest previous Sunday to the specified day to be the start of the week.
	* @param date the LocalDate that is to be contained in the generated week
	*/
	public LocalDate calculateStartOfWeek(LocalDate date) {
		// How to get the day of the week from a LocalDate: https://www.tutorialspoint.com/java-program-to-get-day-of-week-as-string
    	String dayOfWeek = date.getDayOfWeek().name();
    	LocalDate startOfWeek = null;
    	if (dayOfWeek.equals("SUNDAY")) {
    		startOfWeek = date.minusDays(0);
    	} else if (dayOfWeek.equals("MONDAY")){
    		startOfWeek = date.minusDays(1);
    	} else if (dayOfWeek.equals("TUESDAY")){
    		startOfWeek = date.minusDays(2);
    	} else if (dayOfWeek.equals("WEDNESDAY")){
    		startOfWeek = date.minusDays(3);
    	} else if (dayOfWeek.equals("THURSDAY")){
    		startOfWeek = date.minusDays(4);
    	} else if (dayOfWeek.equals("FRIDAY")){
    		startOfWeek = date.minusDays(5);
    	} else if (dayOfWeek.equals("SATURDAY")){
    		startOfWeek = date.minusDays(6);
    	}
    	return startOfWeek;
    }
	
	/**
	* produces a readable string describing the range of days that the week object covers 
	* @return the string to be read
	*/
	public String toString() {
		String startMonth = daysOfWeek.get(0).getDate().getMonth().name().substring(0,1)+daysOfWeek.get(0).getDate().getMonth().name().substring(1).toLowerCase();
		String endMonth = daysOfWeek.get(6).getDate().getMonth().name().substring(0,1)+daysOfWeek.get(6).getDate().getMonth().name().substring(1).toLowerCase();
		return startMonth + " " + daysOfWeek.get(0).getDate().getDayOfMonth() + " - " + endMonth + " " + daysOfWeek.get(6).getDate().getDayOfMonth() + " (" + daysOfWeek.get(0).getDate().getYear() + ")";
	}
	
	/**
	* Calculates the total amount of sleep debt accumulated by the user over the days in the week.
	* @param user the users profile
	* @return the total sleep debt as a Time object
	*/
	public Time calculateSleepDebt(User user) {
    	int sleepDebtHours = 0;
    	int sleepDebtMinutes = 0;
    	
    	// for each day in the diary that matches a day in the current week
    	for (Day i : user.getDiary()) {
    		for (Day j : this.getDaysOfWeek()) {
    			if (i.getDate().equals(j.getDate())){
    				// if the day has a sleep debt, add the sleep debt to the running total
        			if (i.getSleepDebt() != null) {
        				sleepDebtHours += i.getSleepDebt().getHours();
        				sleepDebtMinutes += i.getSleepDebt().getMinutes();
        			}
        		}
    		}
    	}
    	// if minutes goes over 60, that means we have an extra hour, so we need to add the extra minutes to the hours and get the remainder for the minutes
    	sleepDebtHours += sleepDebtMinutes / 60;
    	sleepDebtMinutes = sleepDebtMinutes % 60;
    	totalSleepDebt = new Time(sleepDebtHours, sleepDebtMinutes);
    	return totalSleepDebt;
    }
	

	/**
	* gets a list of the days in the week
	* @return the ArrayList of days in the current week
	*/
	public ArrayList<Day> getDaysOfWeek() {
		return daysOfWeek;
	}

}

	
