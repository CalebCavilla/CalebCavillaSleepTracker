package application;

import java.time.LocalDate;
import java.util.ArrayList;

public class User {

	private Time goalBedTime;
	private Time goalAwakeTime;
	private Time goalTotalSleep;
	private Time sleepDebt;
	private String goalMood;
	private String gender;
	private int age;
	private int weight;
	private int height;
	
	private ArrayList<Day> diary = new ArrayList<Day>();
	
	
	public User() {
		diary.add(new Day(LocalDate.MIN));
	}

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


	public Time getGoalBedTime() {
		return goalBedTime;
	}



	public void setGoalBedTime(Time goalBedTime) {
		this.goalBedTime = goalBedTime;
	}



	public Time getGoalAwakeTime() {
		return goalAwakeTime;
	}



	public void setGoalAwakeTime(Time goalAwakeTime) {
		this.goalAwakeTime = goalAwakeTime;
	}



	public Time getGoalTotalSleep() {
		return goalTotalSleep;
	}



	public void setGoalTotalSleep(Time goalTotalSleep) {
		this.goalTotalSleep = goalTotalSleep;
	}



	public String getGoalMood() {
		return goalMood;
	}



	public void setGoalMood(String goalMood) {
		this.goalMood = goalMood;
	}



	public String getGender() {
		return gender;
	}



	public void setGender(String gender) {
		this.gender = gender;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	public int getWeight() {
		return weight;
	}



	public void setWeight(int weight) {
		this.weight = weight;
	}



	public int getHeight() {
		return height;
	}



	public void setHeight(int height) {
		this.height = height;
	}

	public ArrayList<Day> getDiary() {
		return diary;
	}

	public Time getSleepDebt() {
		return sleepDebt;
	}

	public void setSleepDebt(Time sleepDebt) {
		this.sleepDebt = sleepDebt;
	}

}
