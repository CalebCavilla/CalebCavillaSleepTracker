package application;

public class Sleep {

	private Time startTime;
	private Time endTime;
	private String totalSleep;
	private String mood;
	
	public Sleep(Time startTime, Time endTime, String mood) {
		this.startTime = startTime;
		this.endTime = endTime;
		this.totalSleep = startTime.difference(endTime);
		this.mood = mood;
	}
	
	public Time getStartTime() {
		return startTime;
	}

	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	public Time getEndTime() {
		return endTime;
	}

	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	public String getTotalSleep() {
		return totalSleep;
	}

	public void setTotalSleep(String totalSleep) {
		this.totalSleep = totalSleep;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

}
