package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
/**
* Represents a given day in history.
* Each day has a date, list of sleep periods, a total time spent asleep, and a sleep debt to the rest of the week.
*/
public class Day {

	private LocalDate date;
	private ArrayList<Sleep> sleepPeriods = new ArrayList<Sleep>();
	private Time totalSleep;
	private Time sleepDebt;
	
	/**
   	* Creates a day with a respective date.
   	* @param date a LocalDate representing a actual date in history.
   	*/
	public Day(LocalDate date) {
		this.setDate(date);
	}
	
	/**
   	* calculates the total sleep that occurs on a given day
   	*/
	public void calculateTotalSleep() {
		int totalHours = 0;
		int totalMinutes = 0;
		// for each sleep period that occurred in the day, add the hours and the minutes to the running total
		for (Sleep i : sleepPeriods) {
			totalHours += i.getDuration().getHours();
			totalMinutes += i.getDuration().getMinutes();
			
			// every 60 minutes is an hour, convert excess minutes to hours
			totalHours += totalMinutes / 60;
			totalMinutes = totalMinutes % 60;
		}
		
		totalSleep = new Time(totalHours, totalMinutes);
	}

	/**
   	* gets the date of the day
   	* @return the LocalDate of the day
   	*/
	public LocalDate getDate() {
		return date;
	}

	/**
   	* sets the date of the day object
   	* @param the date to be set
   	*/
	public void setDate(LocalDate date) {
		this.date = date;
	}

	/**
   	* gets the list of sleep periods in the day
   	* @return the ArrayList of sleep periods 
   	*/
	public ArrayList<Sleep> getSleepPeriods() {
		return sleepPeriods;
	}

	/**
   	* gets the total sleep that occurred during the day
   	* @return the duration of total sleep
   	*/
	public Time getTotalSleep() {
		calculateTotalSleep();
		return totalSleep;
	}

	/**
   	* sets the total sleep for the given day
   	*/
	public void setTotalSleep(Time totalSleep) {
		this.totalSleep = totalSleep;
	}

	/**
   	* gets the total sleep debt that occurred during that day
   	* @return the time duration of sleep debt
   	*/
	public Time getSleepDebt() {
		return sleepDebt;
	}

	/**
   	* sets the total sleep debt that occurred on that day
   	*/
	public void setSleepDebt(Time sleepDebt) {
		this.sleepDebt = sleepDebt;
		
	}





}
