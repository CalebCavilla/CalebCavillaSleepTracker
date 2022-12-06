package application;

import java.time.LocalDate;
import java.util.ArrayList;


public class Week {

	private ArrayList<Day> daysOfWeek = new ArrayList<Day>();
	private Time totalSleepDebt;

	public Week(LocalDate date) {
		date = calculateStartOfWeek(date);
		this.daysOfWeek.add(new Day(date));
		for (int i = 1; i < 7; i++) {
			daysOfWeek.add(new Day(date.plusDays(i)));
		}
	}
	
	public LocalDate calculateStartOfWeek(LocalDate date) {
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
	
	
	public String toString() {
		String startMonth = daysOfWeek.get(0).getDate().getMonth().name().substring(0,1)+daysOfWeek.get(0).getDate().getMonth().name().substring(1).toLowerCase();
		String endMonth = daysOfWeek.get(6).getDate().getMonth().name().substring(0,1)+daysOfWeek.get(6).getDate().getMonth().name().substring(1).toLowerCase();
		return startMonth + " " + daysOfWeek.get(0).getDate().getDayOfMonth() + " - " + endMonth + " " + daysOfWeek.get(6).getDate().getDayOfMonth() + " (" + daysOfWeek.get(0).getDate().getYear() + ")";
	}
	
	public Time calculateSleepDebt(User user) {
    	int sleepDebtHours = 0;
    	int sleepDebtMinutes = 0;
    	for (Day i : user.getDiary()) {
    		for (Day j : this.getDaysOfWeek()) {
    			if (i.getDate().equals(j.getDate())){
        			if (i.getSleepDebt() != null) {
        				sleepDebtHours += i.getSleepDebt().getHours();
        				sleepDebtMinutes += i.getSleepDebt().getMinutes();
        			}
        		}
    		}
    	}
    	sleepDebtHours += sleepDebtMinutes / 60;
    	sleepDebtMinutes = sleepDebtMinutes % 60;
    	totalSleepDebt = new Time(sleepDebtHours, sleepDebtMinutes);
    	return totalSleepDebt;
    }
	

	public ArrayList<Day> getDaysOfWeek() {
		return daysOfWeek;
	}

	public void setDaysOfWeek(ArrayList<Day> daysOfWeek) {
		this.daysOfWeek = daysOfWeek;
	}
}

	
