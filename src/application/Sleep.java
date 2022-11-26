package application;

public class Sleep {

	private String type;
	private Time startTime;
	private Time endTime;
	private Time totalSleep;
	private String mood;
	
	public Sleep(String type, Time startTime, Time endTime, String mood) {
		this.type = type;
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

	public Time getTotalSleep() {
		return totalSleep;
	}

	public void setTotalSleep(Time totalSleep) {
		this.totalSleep = totalSleep;
	}

	public String getMood() {
		return mood;
	}

	public void setMood(String mood) {
		this.mood = mood;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
