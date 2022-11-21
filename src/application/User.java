package application;

public class User {

	private String goalBedTime;
	private String goalAwakeTime;
	private String goalTotalSleep;
	private String goalMood;
	private String gender;
	private int age;
	private int weight;
	private int height;
	
	
	
	public User() {
		// TODO Auto-generated constructor stub
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


	public String getGoalBedTime() {
		return goalBedTime;
	}



	public void setGoalBedTime(String goalBedTime) {
		this.goalBedTime = goalBedTime;
	}



	public String getGoalAwakeTime() {
		return goalAwakeTime;
	}



	public void setGoalAwakeTime(String goalAwakeTime) {
		this.goalAwakeTime = goalAwakeTime;
	}



	public String getGoalTotalSleep() {
		return goalTotalSleep;
	}



	public void setGoalTotalSleep(String goalTotalSleep) {
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

}
