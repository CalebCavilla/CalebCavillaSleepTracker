package application;

import java.time.LocalDate;
import java.util.ArrayList;


public class Week {

	private ArrayList<LocalDate> daysOfWeek = new ArrayList<LocalDate>();
	

	public Week(LocalDate startDate) {
		this.daysOfWeek.add(startDate);
		for (int i = 1; i < 7; i++) {
			daysOfWeek.add(startDate.plusDays(i));
		}
	}
	
	public String toString() {
		String startMonth = daysOfWeek.get(0).getMonth().name().substring(0,1)+daysOfWeek.get(0).getMonth().name().substring(1).toLowerCase();
		String endMonth = daysOfWeek.get(6).getMonth().name().substring(0,1)+daysOfWeek.get(6).getMonth().name().substring(1).toLowerCase();
		return startMonth + " " + daysOfWeek.get(0).getDayOfMonth() + " - " + endMonth + " " + daysOfWeek.get(6).getDayOfMonth() + " (" + daysOfWeek.get(0).getYear() + ")";
	}

	public ArrayList<LocalDate> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(ArrayList<LocalDate> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
}

	
