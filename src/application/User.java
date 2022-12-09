package application;

import java.time.LocalDate;
import java.util.ArrayList;

/**
* User class holds all of the personal information and goals of the user. Responsible for acting as the ball of information to be passed around the controllers
* connecting everything together.
*/
public class User {

	private Time goalBedTime;
	private Time goalAwakeTime;
	private Time goalTotalSleep;
	private String goalMood;
	private String gender;
	private int age;
	private int weight;
	private int height;
	
	private ArrayList<Day> diary = new ArrayList<Day>();
	
	/**
	* Constructs a default user profile with most details set to null.
	*/
	public User() {
		diary.add(new Day(LocalDate.MIN));
	}

	/**
	* Prints out all of the instance variables, should only be used for debugging
	*/
	public void print() {
		System.out.println("Bedtime: " + goalBedTime);
		System.out.println("Awaketime: " + goalAwakeTime);
		System.out.println("Total Sleep Goal: " + goalTotalSleep);
		System.out.println("Mood: " + goalMood);
		System.out.println("Gender: " + gender);
		System.out.println("age: " + age);
		System.out.println("weight: " + weight);
		System.out.println("height: " + height);
	}

	/**
	* gets the goal bed time of the user
	* @return the Time goal bed time
	*/
	public Time getGoalBedTime() {
		return goalBedTime;
	}


	/**
	* sets the users goal time to fall asleep
	* @param the Time the user wants to sleep
	*/
	public void setGoalBedTime(Time goalBedTime) {
		this.goalBedTime = goalBedTime;
	}


	/**
	* gets the goal awake time of the user
	* @return the Time goal awake time
	*/
	public Time getGoalAwakeTime() {
		return goalAwakeTime;
	}


	/**
	* sets the users goal time to wake up
	* @param the Time the users wants to wake up
	*/
	public void setGoalAwakeTime(Time goalAwakeTime) {
		this.goalAwakeTime = goalAwakeTime;
	}


	/**
	* gets the goal total sleep per day
	* @return the Time goal total sleep
	*/
	public Time getGoalTotalSleep() {
		return goalTotalSleep;
	}


	/**
	* sets the goal total amount of sleep the user wants to get every day
	* @param the Time duration of sleep the user wants to get
	*/
	public void setGoalTotalSleep(Time goalTotalSleep) {
		this.goalTotalSleep = goalTotalSleep;
	}


	/**
	* gets the goal wake up mood of the user
	* @return the string representing the mood of the user
	*/
	public String getGoalMood() {
		return goalMood;
	}


	/**
	* sets the goal wake up mood of the user
	* @param the String mood to be set
	*/
	public void setGoalMood(String goalMood) {
		this.goalMood = goalMood;
	}


	/**
	* gets the gender of the user
	* @return the gender as a string
	*/
	public String getGender() {
		return gender;
	}


	/**
	* sets the gender of the user
	* @param the String gender to be set
	*/
	public void setGender(String gender) {
		this.gender = gender;
	}


	/**
	* gets the age of the user
	* @return the age as a integer
	*/
	public int getAge() {
		return age;
	}


	/**
	* sets the age of the user
	* @param the integer age to be set
	*/
	public void setAge(int age) {
		this.age = age;
	}


	/**
	* gets the weight of the user
	* @return the weight as an integer in kg
	*/
	public int getWeight() {
		return weight;
	}


	/**
	* sets the weight of the user
	* @param the integer weight to be set in kg
	*/
	public void setWeight(int weight) {
		this.weight = weight;
	}


	/**
	* gets the height of the user
	* @return the height of the user in cm
	*/
	public int getHeight() {
		return height;
	}


	/**
	* sets the height of the user
	* @param the integer height to be set in cm
	*/
	public void setHeight(int height) {
		this.height = height;
	}

	/**
	* gets the users diary of days
	* @return the arrayList of days in the users sleep diary
	*/
	public ArrayList<Day> getDiary() {
		return diary;
	}

	

}
