package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Day {

	private Date date;
	private ArrayList<Sleep> sleepPeriods = new ArrayList<Sleep>();
	private Time totalSleep;
	
	public Day(String date) throws ParseException {
		this.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
	}
	
	
	public void calculateTotalSleep() {
		int totalHours = 0;
		int totalMinutes = 0;
		for (Sleep i : sleepPeriods) {
			totalHours += i.getTotalSleep().getHours();
			totalMinutes += i.getTotalSleep().getMinutes();
			
			totalHours += totalMinutes / 60;
			totalMinutes = totalMinutes % 60;
		}
		
		totalSleep = new Time(totalHours, totalMinutes);
	}
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ArrayList<Sleep> getSleepPeriods() {
		return sleepPeriods;
	}

	public void setSleepPeriods(ArrayList<Sleep> sleepPeriods) {
		this.sleepPeriods = sleepPeriods;
	}
	
	public Time getTotalSleep() {
		return totalSleep;
	}

	public void setTotalSleep(Time totalSleep) {
		this.totalSleep = totalSleep;
	}



}
