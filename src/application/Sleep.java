package application;

/**
* Sleep class represents a sleep period. Sleep periods are independent of a day and are not tied to a specific date.
* Sleep periods can either be naps or 'main sleeps', have a start and end, along with a mood and quality of the sleep
*/
public class Sleep {

	private String type;
	private Time startTime;
	private Time endTime;
	private Time duration;
	private String mood;
	private int quality;
	
	/**
	* Creates a sleep period with details provided from the user through the GUI
	* @param type a string representing the type of sleep. Can be either a nap or a main sleep
	* @param startTime the time representing when the user went to bed
	* @param endTime the time representing when the user woke up
	* @param mood the string representing the emotional/physical state the user felt when they woke up
	*/
	public Sleep(String type, Time startTime, Time endTime, String mood) {
		this.type = type;
		this.startTime = startTime;
		this.endTime = endTime;
		this.duration = startTime.difference(endTime);
		this.mood = mood;
		this.setQuality(new Quality(duration).getQualityValue());
	}
	
	
	/**
	* gets the time the user started sleeping
	* @return the Time the user started sleeping
	*/
	public Time getStartTime() {
		return startTime;
	}

	/**
	* sets the time the user started sleeping
	* @param startTime the time representing the start of the sleep
	*/
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}

	/**
	* gets the time the user ended their sleep
	* @return the Time the user ended their sleep
	*/
	public Time getEndTime() {
		return endTime;
	}
	/**
	* sets the time the user ended their sleep
	* @param endTime the Time the user ended their sleep
	*/
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}

	/**
	* gets the total time the user spent asleep
	* @return the Time the user spent asleep
	*/
	public Time getDuration() {
		return duration;
	}
	/**
	* sets the total time the user spent asleep
	* @param duration the total time the user spent asleep
	*/
	public void setDuration(Time duration) {
		this.duration = duration;
	}

	/**
	* gets the mood the user felt when they woke up
	* @return the mood of the user when they woke up
	*/
	public String getMood() {
		return mood;
	}
	/**
	* sets the mood the user felt when they woke up
	* @param mood the emotional/physical state of the user when they woke up
	*/
	public void setMood(String mood) {
		this.mood = mood;
	}

	/**
	* gets the type of sleep
	* @return the type of sleep
	*/
	public String getType() {
		return type;
	}
	/**
	* sets the type of sleep
	* @param the type of sleep to be set
	*/
	public void setType(String type) {
		this.type = type;
	}

	/**
	* gets the percentage quality of sleep
	* @return the quality of the sleep as a percentage
	*/
	public int getQuality() {
		return quality;
	}
	/**
	* sets the quality of the sleep
	* @param quality the quality of sleep as a percentage
	*/
	public void setQuality(int quality) {
		this.quality = quality;
	}


}
