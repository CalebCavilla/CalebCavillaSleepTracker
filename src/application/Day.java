package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

public class Day {

	private LocalDate date;
	private ArrayList<Sleep> sleepPeriods = new ArrayList<Sleep>();
	private Time totalSleep;
	
	public Day(LocalDate date) throws ParseException {
		this.setDate(date);
	}
	
	
	public void calculateTotalSleep() {
		int totalHours = 0;
		int totalMinutes = 0;
		for (Sleep i : sleepPeriods) {
			totalHours += i.getDuration().getHours();
			totalMinutes += i.getDuration().getMinutes();
			
			totalHours += totalMinutes / 60;
			totalMinutes = totalMinutes % 60;
		}
		
		totalSleep = new Time(totalHours, totalMinutes);
	}
	
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ArrayList<Sleep> getSleepPeriods() {
		return sleepPeriods;
	}

	
	public Time getTotalSleep() {
		return totalSleep;
	}

	public void setTotalSleep(Time totalSleep) {
		this.totalSleep = totalSleep;
	}



}
