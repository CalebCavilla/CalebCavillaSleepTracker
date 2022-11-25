package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Day {

	private Date date;
	private ArrayList<Sleep> sleepPeriods = new ArrayList<Sleep>();
	private int totalSleep = 0;
	
	public Day(String date) throws ParseException {
		this.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
	}
	
	
	public void calculateTotalSleep() {
		int totalHours = 0;
		int toalMinutes = 0;
		for (Sleep i : sleepPeriods) {
			i.getTotalSleep()
		}
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
	
	public int getTotalSleep() {
		return totalSleep;
	}

	public void setTotalSleep(int totalSleep) {
		this.totalSleep = totalSleep;
	}



}
