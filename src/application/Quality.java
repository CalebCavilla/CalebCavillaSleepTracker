package application;

public class Quality {
	

	static final double toddlerSleep = 720.0;
	static final double childSleep = 600.0;
	static final double teenSleep = 540.0;
	static final double adultSleep = 480.0;
	static final double correctDeepSleep = 0.33;
	private static User user = Main.user;
	private int sleepDuration;
	private int quality;
	
	public Quality(Time sleepDuration) {
		this.sleepDuration = sleepDuration.getHours()*60 + sleepDuration.getMinutes();
		setQuality(calculateQuality());
	}



	public int calculateQuality(){
		// Deep sleep has a weight of 0.4
		double deepSleepAmount = calculateCorrectSpecialSleep(30, 0.33)* 0.4 * 100;
		System.out.println(deepSleepAmount);
		// REM sleep has a weight of 0.2
		double remSleepAmount = calculateCorrectSpecialSleep(30, 0.33) * 0.2 * 100;
		System.out.println(remSleepAmount);
		// actual amount of sleep has a weight of 0.4
		double sleepAmount = calculateCorrectDurration() * 0.4 * 100;
		System.out.println(sleepAmount);
		
		return (int) (deepSleepAmount + remSleepAmount + sleepAmount);
	}
	
	
	private int remainder(int remainder, int perCycle) {
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
	
	
	public double calculateCorrectSpecialSleep(int perCycle, double ratio){
		double sleep = 0;
		int numberCycles = sleepDuration / 90;
		int remainder = sleepDuration % 90;

		if (numberCycles == 0) {
			// if user slept less than 90 minutes all the deep sleep they got in in the remainder
			sleep = remainder(remainder, perCycle);
		} else {
			// if user slept more than 90 minutes, deep sleep is 30 for each cycle plus the remainder of the last cycle
			sleep = (numberCycles * perCycle) + remainder(remainder, perCycle);
		}

		
		double ratioOfSpecialSleep = sleep / sleepDuration;
		System.out.println("ratioOfSpeciaSLeep: " + ratioOfSpecialSleep);
		// ratio of deep sleep and REM should be 0.33, get percentage 
		return ratioOfSpecialSleep / ratio;
	}
	
	public double calculateCorrectDurration() {

		System.out.println("User age: " + user.getAge());
		
		if (user.getAge() < 6) {
			if (sleepDuration < toddlerSleep){
				System.out.println("here1");
				return sleepDuration / toddlerSleep;
			}
		} else if (user.getAge() < 13) {
			if (sleepDuration < childSleep){
				System.out.println("here2");
				return sleepDuration / childSleep;
			}
		} else if (user.getAge() < 19) {
			if (sleepDuration < teenSleep){
				System.out.println("here3");
				return sleepDuration / teenSleep;
			}
		} else {
			if (sleepDuration < adultSleep){
				System.out.println("here4");
				return sleepDuration / adultSleep;
			}
		}
		System.out.println("here5");
		return 1;
	}



	public int getQuality() {
		return quality;
	}



	public void setQuality(int quality) {
		this.quality = quality;
	}
	
		

}