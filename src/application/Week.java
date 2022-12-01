package application;

import java.time.LocalDate;

public class Week {

	private LocalDate startDate;
	private LocalDate endDate;
	
	
	public Week(LocalDate startDate, LocalDate endDate) {
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	public String toString() {
		String startMonth = startDate.getMonth().name().substring(0,1)+startDate.getMonth().name().substring(1).toLowerCase();
		String endMonth = endDate.getMonth().name().substring(0,1)+endDate.getMonth().name().substring(1).toLowerCase();
		return startMonth + " " + startDate.getDayOfMonth() + " - " + endMonth + " " + endDate.getDayOfMonth() + " (" + startDate.getYear() + ")";
	}

}
