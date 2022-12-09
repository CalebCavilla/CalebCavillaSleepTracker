package application;
/**
* Quality is responsible for holding/calculating the percentage quality of sleep for a given sleep period
*/
public class Quality {
	
	// all calculations are done in minutes to make things consistent and easy
	static final double toddlerSleep = 720.0;
	static final double childSleep = 600.0;
	static final double teenSleep = 540.0;
	static final double adultSleep = 480.0;
	static final double correctDeepSleep = 0.33;
	private static User user = Main.user;
	private int sleepDuration;
	private int qualityValue;
	
	/**
	* creates and calculates a quality measurement for a given length of sleep
	* @param sleepDuration the length of time for the given sleep period
	*/
	public Quality(Time sleepDuration) {
		// convert to minutes
		this.sleepDuration = sleepDuration.getHours()*60 + sleepDuration.getMinutes();
		setQualityValue(calculateQuality());
	}


	/**
	* calculates quality of sleep based on three categories, deep sleep, light sleep, and total sleep duration
	* @return the integer percentage for the quality of sleep
	*/
	public int calculateQuality(){
		// Deep sleep has a weight of 0.4
		double deepSleepAmount = calculateCorrectSpecialSleep(30, 0.33)* 0.4 * 100;
		// REM sleep has a weight of 0.2
		double remSleepAmount = calculateCorrectSpecialSleep(30, 0.33) * 0.2 * 100;
		// actual amount of sleep has a weight of 0.4
		double sleepAmount = calculateCorrectDurration() * 0.4 * 100;
		return (int) (deepSleepAmount + remSleepAmount + sleepAmount);
	}
	
	/**
	* Calculates the amount of special sleep that occurs when users sleep does not fit into clean 90 minute cycle
	* @param remainder the integer amount of standard sleep in the remainder
	* @param perCycle the amount of special sleep that would have occurred given a full 90 minutes sleep cycle
	* @return the integer amount of special sleep that the user would have received 
	*/
	private int remainderSpecialSleep(int remainder, int perCycle) {
		int sleep = 0;
		// If the user slept for at least 40-70 minutes then the deep sleep is the remainder after removing the first 40 minutes
		if (remainder >= 40 && remainder < 70) {
			sleep = remainder - 40;
		}else if (remainder < 40) {
			// if the user slept less than 40 minutes they got 0 additional deep sleep
			sleep = 0;
		}else {
			// if the user slept between 70-90 minutes the deepSleep is automatically 30.
			sleep = perCycle;
		}
		return sleep;
	}
	
	/**
	* Calculates the percentage amount of special sleep that occurs during a users sleep period
	* @param perCycle the amount of special sleep that would have occurred given a full 90 minutes sleep cycle
	* @param ratio the proper scientific ratio of special sleep - normal sleep
	* @return the percentage amount of special sleep the user received out of total normal sleep during a sleep period
	*/
	public double calculateCorrectSpecialSleep(int perCycle, double ratio){
		double sleep = 0;
		int numberCycles = sleepDuration / 90;
		int remainder = sleepDuration % 90;

		if (numberCycles == 0) {
			// if user slept less than 90 minutes all the deep sleep they got in in the remainder
			sleep = remainderSpecialSleep(remainder, perCycle);
		} else {
			// if user slept more than 90 minutes, deep sleep is 30 for each cycle plus the remainder of the last cycle
			sleep = (numberCycles * perCycle) + remainderSpecialSleep(remainder, perCycle);
		}

		
		double ratioOfSpecialSleep = sleep / sleepDuration;
		// ratio of deep sleep and REM should be 0.33, get percentage 
		return ratioOfSpecialSleep / ratio;
	}
	/**
	* Calculates the percentage of the amount of sleep the user did receive out of a total amount they should have received based on their age.
	* @return the percentage amount of sleep the user received out of the total recommended sleep for someone their age
	*/
	public double calculateCorrectDurration() {
		
		if (user.getAge() < 6) {
			if (sleepDuration < toddlerSleep){
				return sleepDuration / toddlerSleep;
			}
		} else if (user.getAge() < 13) {
			if (sleepDuration < childSleep){
				return sleepDuration / childSleep;
			}
		} else if (user.getAge() < 19) {
			if (sleepDuration < teenSleep){
				return sleepDuration / teenSleep;
			}
		} else {
			if (sleepDuration < adultSleep){
				return sleepDuration / adultSleep;
			}
		}
		return 1;
	}


	/**
	* gets the quality value of sleep as a percentage 
	* @return the quality of sleep
	*/
	public int getQualityValue() {
		return qualityValue;
	}


	/**
	* Sets the quality of sleep as a percentage
	* @param the percentage quality of sleep
	*/
	public void setQualityValue(int quality) {
		this.qualityValue = quality;
	}
	
		

}