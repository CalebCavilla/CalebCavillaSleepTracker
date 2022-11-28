package application;

public class Sleep {

	private String type;
	private Time startTime;
	private Time endTime;
	private Time duration;
	private String mood;
	
	public Sleep(String type, Time startTime, Time endTime, String mood) {
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = startTime.difference(endTime);
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

	public Time getDuration() {
		return duration;
	}

	public void setDuration(Time duration) {
		this.duration = duration;
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
